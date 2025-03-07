package com.example.boot05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.boot05.dao.MemberDao;
import com.example.boot05.dao.TodoDao;
import com.example.boot05.dto.MemberDto;
import com.example.boot05.dto.TodoDto;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TodoController {
	//spring bean container 로 부터 MemberDao type 주입(DI) 받기
	@Autowired
	private TodoDao dao;
	
	@GetMapping("/todo/list")
	public String List(Model model) {
		List<TodoDto> list=dao.getList();
		model.addAttribute("list",list);
		return "todo/list";
	}
	
}
