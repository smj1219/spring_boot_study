package com.example.boot11.service;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.example.boot11.dto.FileDto;

public interface FileService {
	public void getAll(Model model, FileDto dto );
	//새로운 글을 저장하고 저장된 글정보를 리턴하는 메소드
	public void upload(FileDto dto);
	public void removeFile(int num);
	
	public ResponseEntity<InputStreamResource> getFileData(int num);
	
}
