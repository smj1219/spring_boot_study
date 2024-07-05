package com.example.boot07.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FormController {
	
	@GetMapping("/")
	public String home () {
		return "home";
	}
	
}
