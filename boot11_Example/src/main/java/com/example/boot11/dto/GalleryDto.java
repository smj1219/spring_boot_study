package com.example.boot11.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("galleryDto") 
@AllArgsConstructor
@NoArgsConstructor 
@Builder 
public class GalleryDto {
	private int num;
	private String writer;
	private String caption;
	private String saveFileName;
	private String regdate;
	private MultipartFile image;
	
	private int pageNum=1;
	private int startRowNum;
	private int endRowNum;
	//검색 키워드 관련
	private String keyword=""; //검색 조건이 없는 경우 null 이 출력되는걸 방지하기 위해 빈문자열을 기본값으로 설정
	private String condition="";
	private int prevNum, nextNum;
}
