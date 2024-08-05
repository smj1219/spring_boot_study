package com.example.boot13.dto;

import com.example.boot13.entity.Cook;
import com.example.boot13.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Data
public class CookDto {
	private long num;
	private String name;
	private String content;
	
	public static CookDto toDto(Cook entity) {
		//매게변수에 전달되는 Member entity 객체에 담긴 내용을 이용해서 MemberDto 객체를 만들어서 리턴해준다.
		return CookDto.builder()
				.num(entity.getNum())
				.name(entity.getName())
				.content(entity.getContent())
				.build();
	}
}
