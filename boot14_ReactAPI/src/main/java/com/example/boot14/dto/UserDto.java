package com.example.boot14.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
@Data 
public class UserDto {
	private int id;
	private String userName;
	private String newPassword;
	private String password;
	private String email;
	// authority 정보를 저장할 칼럼 ADMIN | STAFF | USER 형태이다
	private String role;
	private String profile;
	private String regdate;
	
	//프로필 이미지 파일 업로드 처리를 하기 위한 필드
	private MultipartFile image; //<input type="file" name="image"/> 임으로 필드명이 image 이다
}
