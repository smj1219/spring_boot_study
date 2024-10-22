package com.example.boot11.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("filedto") //Mapper xml 에서 FileDto type 을 간단히 줄여서 쓸 수 있도록 부여하기
@AllArgsConstructor // 생성자의 인자로 모든 필드의 값을 전달 받는 생성자(Builder 가 동작하기 위해서 필요)
@NoArgsConstructor // default 생성자
@Builder 
@Data // @Setter + @Getter + 몇가지 옵션을 포함한다.
public class FileDto {
	private int num;
	private String writer;
	private String title;
	//원본 파일명
	private String orgFileName;
	//파일 시스템에 저장된 파일명
	private String saveFileName;
	//파일의 크기 
	private long fileSize;
	private String regdate;
	//페이징 처리를 위한 필드
	private int pageNum=1;
	private int startRowNum;
	private int endRowNum;
	//검색 키워드 관련
	private String keyword=""; //검색 조건이 없는 경우 null 이 출력되는걸 방지하기 위해 빈문자열을 기본값으로 설정
	private String condition="";
	//파일 업로드 처리를 하기 위한 필드
	private MultipartFile myFile;
	
	
}