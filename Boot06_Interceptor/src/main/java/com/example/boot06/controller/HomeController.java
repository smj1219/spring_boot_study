package com.example.boot06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model) {
		//응답에 필요한 데이터는 model 객체에 담아주면 된다
		model.addAttribute("fortuneToday", "동쪽으로 가면 귀인을 만나요!");
		
		// /templates/home.html 타임리프 페이지로 응답하기
		return "home";
	}
	
	@GetMapping("/sub/play")
	public String play() {
		return "sub/play";
	}
	
	@GetMapping("/sub/study")
	public String study() {
		return "sub/study";
	}
	
	
	
}
