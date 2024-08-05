package com.example.boot13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot13.dto.CookDto;
import com.example.boot13.dto.MemberDto;
import com.example.boot13.service.CookService;

@Controller
public class CookController {

	@Autowired private CookService service;
	
	@PostMapping("/cook/update")
	public String update(CookDto dto) {
		service.update(dto);
		return "redirect:/cook/list";
	}
	
	@GetMapping("/cook/updateform")
	public String updateform(Long num, Model model) {
		service.getData(num, model);
		return "cook/updateform";
	}
	
	@GetMapping("/cook/delete")
	public String delete(Long num) {
		service.delete(num);
		return "redirect:/cook/list";
	}
	
	@PostMapping("/cook/insert")
	public String insert(CookDto dto) {
		//MemberService 를 이용해서 회원 정보 추가 
		service.insert(dto);
		return "redirect:/cook/list";
	}
	
	@GetMapping("/cook/insertform")
	public String insertform() {
		
		return "cook/insertform";
	}
	
	@GetMapping("/cook/list")
	public String list(Model model) {
		service.getList(model);
		return "cook/list";
	}
}
