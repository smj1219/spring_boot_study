package com.example.boot14.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("galleryDto") // mybatis 에서 사용하는 type alias 
@Builder  // .num().writer().caption()... 형태로 값을 넣어주는 것을 가능하게 한다.
@AllArgsConstructor //모든값을 한번에 넣는 생성자(@Builder 가 동작하려면 필요함)
@NoArgsConstructor //디폴트 생성자 
@Data  // setter, getter 만들기
public class GalleryDto {
	private int num;
	private String writer;
	private String caption;
	private String saveFileName; //파일시스템에 저장된 이미지 파일명을 담을 필드 
	private String regdate;
	private int startRowNum;
	private int endRowNum;
	private MultipartFile image;	//이미지 파일 업로드 처리를 위한 필드
	private int prevNum; //이전글의 글번호와
	private int nextNum; //다음글의 글번호를 담을 필드 추가
}






