package com.example.boot14.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("CafeCommentDto") // mapper xml 문서에서 CafeDto type 을 별칭을 이용해서 사용할수 있다. 
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CafeCommentDto {
	private int num;
	private String writer;
	private String content;
	private String target_id;
	private int ref_group;
	private int comment_group;
	private String deleted;
	private String regdate;
	private String profile; //댓글 작성자의 profile 이미지를 출력하기 위한 필드(user_info 테이블과 join 필요)
	
	//페이징 처리를 위한 필드
	private int pageNum;
	private int startRowNum;
	private int endRowNum;
}
