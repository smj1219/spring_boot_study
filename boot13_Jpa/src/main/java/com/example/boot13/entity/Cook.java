package com.example.boot13.entity;

import com.example.boot13.dto.CookDto;
import com.example.boot13.dto.MemberDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter  //Entity 는 Getter 만 제공한다 (한번 만들어진 객체는 ReadOnly 로 사용되도록)
@Builder
@Entity(name="COOK_INFO")
public class Cook {
	@Id //primary key 로 사용하겠다.
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long num;
	private String name;
	private String content;
	
	public static Cook toEntity(CookDto dto) {
		//매게변수에 전달되는 Member entity 객체에 담긴 내용을 이용해서 MemberDto 객체를 만들어서 리턴해준다.
		return Cook.builder()
				.num(dto.getNum())
				.name(dto.getName())
				.content(dto.getContent())
				.build();
	}
}
