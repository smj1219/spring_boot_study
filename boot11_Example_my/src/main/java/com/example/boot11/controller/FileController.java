package com.example.boot11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.boot11.dto.FileDto;
import com.example.boot11.service.FileService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class FileController {

	@Autowired private FileService service;
	/*
	 * FileDto 에는 pageNum, condition, keyword 값이 담길 수도 있다.
	 * (GET 방식 파라미터 값이 넘어오면 담긴다.)
	 */
	@GetMapping("/file/list")
	public String getList(Model model, FileDto dto) {
		service.getAll(model, dto);
		return "file/list";
	}
	
	@GetMapping("/file/upload_form")
	public String fileUploadForm() {
		return "file/upload_form";
	}
	
	@PostMapping("/file/upload")
	public String fileUpload(FileDto dto) {
		service.upload(dto);
		return "file/upload";
	}
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(int num) {
		return service.getFileData(num);
	}
	
	
	@GetMapping("/file/delete")
	public String delete(int num) {
		service.removeFile(num);
		return "redirect:/file/list";	
	}
	
	@GetMapping("/file/search")
	public String search(FileDto dto, Model model) {
		
		return new String();
	}
	
	
}
