package com.example.boot05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	//최상의 경로 요청이 왔을 때
	@GetMapping("/")
	public String Home() {
		// templates/home.html thymeleaf 페이지 응답하기
		return "home";
	}
	
}
