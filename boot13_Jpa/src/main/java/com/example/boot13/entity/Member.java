package com.example.boot13.entity;

import com.example.boot13.dto.MemberDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter  //Entity 는 Getter 만 제공한다 (한번 만들어진 객체는 ReadOnly 로 사용되도록)
@Setter
@Builder
@Entity(name="MEMBER_INFO")
public class Member { 
	@Id //primary key 로 사용하겠다.
	@GeneratedValue(strategy = GenerationType.AUTO) //번호는 자동으로 들어가게한다 (시퀀스 사용)
	private Long num;
	private String name;
	private String addr;
	
	//dto 를 entity 로 변환하는 static 메소드
	public static Member toEntity(MemberDto dto) {
		return Member.builder()
				.num(dto.getNum())
				.name(dto.getName())
				.addr(dto.getAddr())
				.build();
	}
}
