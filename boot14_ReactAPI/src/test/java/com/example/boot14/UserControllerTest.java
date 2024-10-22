package com.example.boot14;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.boot14.dto.UserDto;
import com.example.boot14.service.UserService;
import com.example.boot14.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

//spring boot application 을 테스트하기 위한 어노테이션
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	// 컨트롤러를 테스트 할 수 있는 MockMvc 주입받기
    @Autowired
    private MockMvc mockMvc;

    //UserService 객체 역활을 할 가짜 객체
    @MockBean
    private UserService userService;

    //JwtUtil 객체 역활을 할 가짜 객체
    @MockBean
    private JwtUtil jwtUtil;

    //인증을 처리할 실체 객체
    @MockBean
    private AuthenticationManager authManager;

    private ObjectMapper objectMapper = new ObjectMapper();

    private UserDto mockUserDto;

    @BeforeEach
    void setup() {
        // 테스트용 UserDto 세팅
        mockUserDto = new UserDto();
        mockUserDto.setUserName("testuser");
        mockUserDto.setPassword("password");
    }

    @Test
    public void testAuthSuccess() throws Exception {
        // Mocking - 인증이 성공할 때
        Mockito.when(jwtUtil.generateToken(mockUserDto.getUserName())).thenReturn("mocked-token");

        // JWT 발급 성공 테스트
        mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Bearer+mocked-token"));
    }

    @Test
    public void testAuthFailure() throws Exception {
        // Mocking - 인증이 실패할 때
        Mockito.doThrow(new BadCredentialsException("Invalid credentials")).when(authManager)
                .authenticate(Mockito.any());

        // 인증 실패 테스트
        mockMvc.perform(MockMvcRequestBuilders.post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUserDto)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testCheckUserNameAvailable() throws Exception {
        // Mocking - 사용 가능한 아이디
        Mockito.when(userService.canUse("testuser")).thenReturn(true);
       
        // 아이디 사용 가능 확인 테스트
        mockMvc.perform(MockMvcRequestBuilders.get("/user/check_username/testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.canUse", is(true)));
    }

    @Test
    public void testCheckUserNameUnavailable() throws Exception {
        // Mocking - 사용 불가능한 아이디
        Mockito.when(userService.canUse("testuser")).thenReturn(false);

        // 아이디 사용 불가 확인 테스트
        mockMvc.perform(MockMvcRequestBuilders.get("/user/check_username/testuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.canUse", is(false)));
    }

    @Test
    public void testAddUser() throws Exception {
        // 회원가입 성공 테스트
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess", is(true)));
    }

    @Test
    public void testUpdateUser() throws Exception {
        // 사용자 정보 업데이트 성공 테스트
        mockMvc.perform(MockMvcRequestBuilders.patch("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUserDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccess", is(true)));
    }
}