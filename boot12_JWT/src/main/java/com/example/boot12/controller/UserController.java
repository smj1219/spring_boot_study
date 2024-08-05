package com.example.boot12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.boot12.dto.UserDto;
import com.example.boot12.service.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class UserController {
	@Autowired private UserService service;
	
	@PostMapping("/user/update")
	public String update(UserDto dto) {
		service.updateUser(dto);
		//개인정보보기로 다시 리다일렉트 시킨다
		return "redirect:/user/info";
	}
	
	
	@GetMapping("/user/updateform")
	public String updateForm(Model model) {
		//Model 에 UserDto 가 담기도록 서비스 메소드에 전달한다.
		service.getInfo(model);
		
		return "user/updateform";
	}
	
	
	@PostMapping("/user/pwd_update")
	public String pwdUdate(UserDto dto, HttpSession session) {
		//비밀번호 수정 이후
		service.updatePassword(dto);
		//강제 로그아웃 처리
		session.invalidate();
		return "user/pwd_update";
		
	}
	
	@GetMapping("/user/pwd_updateform")
	public String pwdUpdateForm() {
		return "user/pwd_updateform";
	}
	
	//개인정보 보기 요청 처리
	@GetMapping("/user/info")
	public String info(Model model) {
		//service 객체에 Model 을 전달해서 Model 에 UserDto 가 담기도록 한다.
		service.getInfo(model);
		return "user/info";
	}
	
	//회원가입 요청처리
	@PostMapping("/user/signup")
	public String signup(UserDto dto) {
		//dto 에는 username, password, email 이 들어있다. role 은 서비스에서 넣어주기로...
		service.addUser(dto);
		return "user/signup";
	};
	
	//회원가입 폼 요청처리
	@GetMapping("/user/signup_form")
	public String signupForm() {
		return "user/signup_form";
	}
	
	
	//세션 허용 갯수 초과시
	@GetMapping("/user/expired")
	public String userExpired() {
		return "user/expired";
	}
	
	//권한 부족시 or 403 인 경우
	@RequestMapping("/user/denied") // get, post 매핑 둘 다 가능
	public String userDenied() {
		return "user/denied";
	}
	
	@RequestMapping("/user/loginform")
	public String loginform() {
		// templates/user/loginform.html 페이지로 forward 이동해서 응답 
		return "user/loginform";
	}
	
	//로그인이 필요한 요청경로를 로그인 하지 않은 상태로 요청하면 리다일렉트 되는 요청경로 
	@GetMapping("/user/required_loginform")
	public String required_loginform() {
		return "user/required_loginform";
	}
	// POST 방식 /user/login 요청후 로그인 성공인경우 forward 이동될 url 
	@PostMapping("/user/login_success")
	public String loginSuccess() {
		return "user/login_success";
	}
	
	//로그인 폼을 제촐(post) 한 로그인 프로세스 중에 forward 되는 경로이기떄문에 @PostMapping 임에 주의
	@PostMapping("/user/login_fail")
	public String loginFail() {
		//로그인 실패임을 알릴 페이지
		return "user/login_fail";
	}
}


