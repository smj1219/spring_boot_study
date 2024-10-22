package com.example.boot14;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient.MockMvcServerSpec;
import org.springframework.transaction.annotation.Transactional;

import com.example.boot14.dto.MemberDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

//spring boot application 을 테스트하기 위한 어노테이션
@SpringBootTest
@Transactional // DB 에 insert, update, delete 가 실제 반영되지않고 ROLLBACK 되도록 한다.
@AutoConfigureMockMvc  // MockMvc 객체를 사용하기 위해 필요한 어노테이션
public class MemberControllerTest {
	
	//컨트롤러에 요청을 하고 결과를 확인해 볼 수 있는 객체
	@Autowired
	private MockMvc mockMvc;
	
	//객체에 있는 정보를 이용해서 json 문자열을 만들어 주는 객체
	private ObjectMapper oMapper = new ObjectMapper();
	
	/*
	 * 서버 내부의 요청이기 때문에 security 와는 상관이 없다
	 * but service 로직은 관련 내용 필요
	 */
	@Test
	void testInsert() throws Exception {
		// insert 할 회원의 정보
		MemberDto dto = MemberDto.builder()
				.name("test_name")
				.addr("test_addr")
				.build();
		//dto 에 있는 정보를 이용해서 json 문자열 얻어내기
		// {"num":0, "name":"test_name", "addr":"test_addr"}
		String json = oMapper.writeValueAsString(dto);
		/*
		 * post("/members") => post 방식 /members 요청
		 * .contentType(MediaType.APPLICATION_JSON) => json 문자열을 전송하겠다는 요청 header 정보
		 * .content(전송할 문자열 json))
		 * .andExpect( )  => 결과 응답으로 기대하는 내용
		 * .andExpect(status().isOk()) => 200번의 정상 응답
		 * jsonPath("$.name", is("teat_name") => 응답된 json 문자열에서 name 이라는 키값으로 저장된 값이 
		 * "test_name" 과 같아야 하는 조건
		 * 
		 */
		mockMvc.perform(post("/members") //post 방식 /members 요청
				.contentType(MediaType.APPLICATION_JSON) //json 문자열을 전송하겠다는 요청 header 정보
				.content(json)) // 전송할 문자열 _ String json = oMapper.writeValueAsString(dto)
				.andDo(print()) // 요청의 결과를 콘솔창에 출력
				.andExpect(status().isOk()) //200번의 정상 응답
				.andExpect(jsonPath("$.name", is("test_name"))) //결과 응답으로 기대하는 내용
				.andExpect(jsonPath("$.addr", is("test_addr"))); 
	}
	
	/*
	 * MockHttpServletResponse 객체를 이용하면 파일 또한 읽어들일 수 있다.
	 */
	@Test
	void testInsert2() throws Exception {
		// insert 할 회원 정보
		MemberDto dto=MemberDto.builder()
				.name("test_name")
				.addr("test_addr")
				.build();
		String json = oMapper.writeValueAsString(dto);
		
		// .andReturn() , .getResponse() 를 호출하면 MockHttpServletResponse 객체를 얻어낼수 있다.
		MockHttpServletResponse response=mockMvc.perform(post("/members")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andReturn()
				.getResponse();
		//결과로 응답된 정보를 얻어낼수 있다.
		String contentType = response.getContentType();
		long length = response.getContentLengthLong();
		// json 문자열 
		String body = response.getContentAsString();
		
		System.out.println("contentType:"+contentType);
		System.out.println("length:"+length);
		System.out.println("body:"+body);
		
		//응답된 contentType 이 반드시 "application/json" 이여야 한다면
		assertEquals(contentType, "application/json");
		
		//응답된 json 문자열을 직접 파싱해서 결과를 확인 할수도 있다.
		// JsonPath dependency 추가 한후에 사용할수 있음
		assertEquals(JsonPath.read(body, "$.name"), "test_name");
		assertEquals(JsonPath.read(body, "$.addr"), "test_addr");
	}
	
	
	
	
	
}
