package com.example.boot11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.boot11.dto.FileDto;
import com.example.boot11.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class FileController {

	@Autowired private FileService service;
	
	@GetMapping("/file/list")
	public String getList(Model model) {
		List<FileDto> list=service.getAll();
		model.addAttribute("list", list);
		return "file/list";
	}
	
	@GetMapping("/file/upload_form")
	public String fileUploadForm() {
		return "file/upload_form";
	}
	
	@PostMapping("/file/upload")
	public String fileUpload(FileDto dto) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(userName);
		service.upload(dto);
		
		return "file/upload";
	}
	
}
