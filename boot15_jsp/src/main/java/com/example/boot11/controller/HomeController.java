package com.example.boot11.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	//최상위 경로 요청에 대해서 응답할 컨트롤러 메소드
	@GetMapping("/")
	public String home(Model m) {//컨트롤러 메소드에서 필요한 객체가 있으면 매개변수에 선언한다.
		// 응답에 필요한 데이터는 HpptServletRequest 에 담거나 Model 객체에 담으면 view engine 에서 사용가능
		
		//DB에서 읽어온 공지 사항이라고 가정하다
		List<String> notice = Arrays.asList("동쪽으로 가면 귀인을 만나요", "어쩌구", "저쩌구");
		//어떤 key 값으로 어떤 type 데이터를 담았는지 기억을 하고 템플릿 페이지를 만들어야 한다.
		m.addAttribute("notice", notice);
		
		// /WEB-INF/views/home.jsp 페이지로 forword 이동해서 응답하겠다는 의미
		// prefix 로 "/WEB-INF/views/" 가 붙고 suffix 로 ".jsp" 가 붙는다.
		return "home";
	}
}
