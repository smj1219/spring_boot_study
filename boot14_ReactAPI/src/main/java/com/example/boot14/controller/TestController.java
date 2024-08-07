package com.example.boot14.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
public class TestController {
	
	@Value("${file.location}")
	private String fileLocation;
	
	//요청 파라미터를 한 번에 추출하기 위한 클래스 정의하기(dto 의 역활을 한다)
	@Setter
	@Getter
	class FileDownRequest{
		String orgFileName;
		String saveFileName; 
		long fileSize;
	}
	
	@Data
	class uploadRequest{
		MultipartFile image;
		String title;
	}
	
	//이미지 업로드 테스트
	@PostMapping("/image/upload")
	public Map<String, Object> imageUpload (MultipartFile image) {
		
		String orgFileName=image.getOriginalFilename();
		String saveFileName=UUID.randomUUID().toString();
		String filePath=fileLocation + File.separator + saveFileName;
		File dest=new File(filePath);
		try {
			image.transferTo(dest);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> map=new HashMap<>();
		map.put("orgFileName", orgFileName);
		map.put("saveFileName", saveFileName);
		return map;
	}
	
	@PostMapping("/image/upload2")
	public Map<String, Object> imageUpload2 (uploadRequest request) {
		MultipartFile image = request.getImage();
		String title=request.getTitle();
		String orgFileName=image.getOriginalFilename();
		String saveFileName=UUID.randomUUID().toString();
		String filePath=fileLocation + File.separator + saveFileName;
		File dest=new File(filePath);
		try {
			image.transferTo(dest);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> map=new HashMap<>();
		map.put("orgFileName", orgFileName);
		map.put("saveFileName", saveFileName);
		map.put("title", title);
		return map;
	}
	
	@PostMapping("/file/upload")
	public Map<String, Object> fileUpload(MultipartFile myFile) {
		//원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//파일의 크기
		long fileSize=myFile.getSize();
		//저장할 파일의 이름을 Universal Unique 한 ID 로 저장하기 위해
		String saveFileName=UUID.randomUUID().toString();
		//저장할 파일의 전체 경로 구성
		String filePath=fileLocation + File.separator + saveFileName;
		File dest=new File(filePath);
		try {
			myFile.transferTo(dest);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> map=new HashMap<>();
		map.put("orgFileName", orgFileName);
		map.put("saveFileName", saveFileName);
		map.put("fileSize", fileSize);
		return map;
	}
	
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(FileDownRequest dto) 
					throws UnsupportedEncodingException, FileNotFoundException{
		//요청 파라미터가 FileDownRequest 에 담겨서 전달된다.
		//다운로드 시켜줄 원본 파일명
		String encodedName=URLEncoder.encode(dto.getOrgFileName(), "utf-8");
		//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
		encodedName=encodedName.replaceAll("\\+"," ");
		//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
		HttpHeaders headers=new HttpHeaders();
		//파일을 다운로드 시켜 주겠다는 정보
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
		//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
		//파일의 크기 정보도 담아준다.
		headers.setContentLength(dto.getFileSize());
		
		//읽어들일 파일의 경로 구성
		String filePath=fileLocation + File.separator + dto.getSaveFileName();
		//파일에서 읽어들일 스트림 객체
		InputStream is=new FileInputStream(filePath);
		//InputStreamResource 객체의 참조값 얻어내기
		InputStreamResource isr=new InputStreamResource(is);
		
		//ResponseEntity 객체의 참조값 얻어내기 
		ResponseEntity<InputStreamResource> resEn=ResponseEntity.ok()
			.headers(headers)
			.body(isr);
		
		return resEn;
	}
	
	
}
