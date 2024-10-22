package com.example.boot14.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot14.dto.UserDto;
import com.example.boot14.exception.PasswordException;
import com.example.boot14.service.UserService;
import com.example.boot14.util.JwtUtil;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {
	//Jwt 토큰 유틸
	@Autowired 
	private JwtUtil jwtUtil;
	
	// react js 를 테스트 하기 위한 코딩
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserService userService;
	

	@PostMapping("/auth")
	public String auth(@RequestBody UserDto dto ) throws Exception {
		try {
			//입력한 username 과 password 를 인증토큰 객체에 담아서 
			UsernamePasswordAuthenticationToken authToken=
					new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());	
			//인증 메니저 객체를 이용해서 인증을 진행한다 
			authManager.authenticate(authToken);
		}catch(Exception e) {
			//예외가 발생하면 인증실패(아이디 혹은 비밀번호 틀림 등등...)
			e.printStackTrace();
			throw new PasswordException("아이디 혹은 비밀번호가 틀려요!");
		}
		//예외가 발생하지 않고 여기까지 실행 된다면 인증을 통과 한 것이다. 토큰을 발급해서 응답한다.
		String token=jwtUtil.generateToken(dto.getUserName());
		return "Bearer+"+token;
	}
	//경로 변수에 전달되는 입력한 userName 이 사용가능한지 여부를 json 으로 응답하는 메소드 
	@GetMapping("/user/check_username/{userName}")
	public Map<String, Object> checkUserName(@PathVariable("userName") String userName){
		
		return Map.of("canUse", userService.canUse(userName));
	}
	
	@PostMapping("/user")
	public Map<String, Object> addUser(@RequestBody UserDto dto){
		userService.addUser(dto);
		return Map.of("isSuccess", true);
	}	
	
	@GetMapping("/user")
	public UserDto getInfo() {
		
		return userService.getInfo();
	}
	
	@PatchMapping("/user")
	public Map<String, Object> updateUser(UserDto dto) {
		userService.updateUser(dto);
		return Map.of("isSuccess", true);
	}
	
	@PatchMapping("/user/password")
	public Map<String, Object> updatePassword(@RequestBody UserDto dto){
		userService.updatePassword(dto);
		return Map.of("isSuccess", true);
	}
	
}

















