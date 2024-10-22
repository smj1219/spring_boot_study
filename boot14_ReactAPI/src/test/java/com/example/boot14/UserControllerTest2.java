package com.example.boot14;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

import com.example.boot14.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;


@SpringBootTest //spring boot application 을 테스트하기 위한 어노테이션
@AutoConfigureMockMvc  // MockMvc 객체를 사용하기 위해 필요한 어노테이션
public class UserControllerTest2 {
	
    @Autowired
    private MockMvc mockMvc;
    
	//객체(dto 등)에 있는 정보를 이용해서 json 문자열을 만들어 주는 객체
	private ObjectMapper oMapper = new ObjectMapper();
    
    @Test void ahthTest() throws Exception{
    	//실제 DB 에 존재하는 정보
        UserDto dto = UserDto.builder()
        		.userName("song")
        		.password("1111")
        		.build();
    	
    	mockMvc.perform(post("/auth")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(oMapper.writeValueAsString(dto)))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$").isNotEmpty())
    		.andDo(result ->{
    			String jwt = result.getResponse().getContentAsString();
    			//문자열이 Bearer 로 시작하는지 확인
    			assertTrue(jwt.startsWith("Bearer"));
    			System.out.println(jwt);    			
    		});
    }
    @Test void ahthFailTest() throws Exception{
    	//실제 DB 에 존재하지 않는 정보
        UserDto dto = UserDto.builder()
        		.userName("xxx")
        		.password("yyy")
        		.build();
    	
    	mockMvc.perform(post("/auth")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(oMapper.writeValueAsString(dto)))
    		.andExpect(status().is4xxClientError());
    		
    }
    
    //아이디 사용가능 여부 테스트 ( 존재하는 아이디 => false, 존재하지 않는 아이디 => true)
    @Test void checkUserNameTest() throws Exception {
    	//사용 불가능한 아이디
    	String userName1 = "kimgura";
    	mockMvc.perform(get("/user/check_username/"+userName1))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.canUse", is(false)));
    	
    	//사용 가능한 아이디
    	String userName2 = UUID.randomUUID().toString();
    	mockMvc.perform(get("/user/check_username/"+userName2))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.canUse", is(true)));
    }

    @WithUserDetails("song") //실제 존재하는 테스트 아이디를 이용한다
    @Test void userInfoTest() throws Exception {
    	//GET 방식의 /user 요청을 하기 위해서는 token 이 있어야 하는데 어떻게 해야하나
    	String result = mockMvc.perform(get("/user"))
    		.andExpect(status().isOk())
    		.andDo(print())
    		.andReturn()
    		.getResponse()
    		.getContentAsString();
    	
    	System.out.println(result);
    	
    	/*
    	 * 응답된 json 문자열에서 userName 이라는 키값으로 저장된 문자열을 읽어온다.
    	 */
    	String userName = JsonPath.read(result, "$.userName");
    	
    	assertEquals(userName, "song");
    }
    
    // 회원가입 테스트
    @Transactional // 실행된 뒤 자동으로 ROLLBACK 된다.
    @Test void testAddUser() throws Exception{
    	//회원가입 정보를 UserDto 에 담는다
    	UserDto dto = UserDto.builder()
    			.userName("testuser1")
    			.password("testpassword")
    			.email("testemail@")
    			.build();
    	
    	mockMvc.perform(post("/user") //post 방식의 /user 요청을 하겠다
    			.contentType(MediaType.APPLICATION_JSON) // 요청 헤더에 json 문자열을 보내겠다.
    			.content(oMapper.writeValueAsString(dto))) // dto 에 저장된 내용을 json 문자열로 변경해서 요청할때 json 문자열을 보내는 설정
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.isSuccess", is(true)));
    }
    
    //회원 정보 수정 테스트
    @Test
    @Transactional 
    @WithUserDetails("kim")
    void testUpdateUser() throws Exception{
    	//수정 정보를 UserDto 에 담는다
    	// json 문자열이 아닌 param 으로 전달할때는 생략 가능하다
    	UserDto dto = UserDto.builder()
    			.userName("kim")
    			.email("testemail@")
    			.build();
    	mockMvc.perform(patch("/user") //patch 방식의 /user 요청을 하겠다
    			.param("userName", "kim")
    			.param("email", "testemail@"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.isSuccess", is(true)));
    }
}
