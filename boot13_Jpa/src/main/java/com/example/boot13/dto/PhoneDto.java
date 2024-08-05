package com.example.boot13.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.boot13.entity.Phone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
// Dto 는 객체를 설정하기 위한 클래스
public class PhoneDto {
	private long id; 
	private String company;
	private String name;
	private Integer price;
	private String regdate;
	
	public static PhoneDto toDto(Phone phone) {
		
		//DAte type 을 이용해서 원하는 문자열 형식을 얻어내기 위한 객체
		SimpleDateFormat sdf = new SimpleDateFormat("yyy.MM.dd E a hh:mm:ss", Locale.KOREA);
		//객체를 이용해서 원하는 문자열을 얻어낸다
		// 2024.08.01 목 오후 06:08:00 의 형식의 문자열
		String result = sdf.format(phone.getRegdate());
		
		return PhoneDto.builder()
				.id(phone.getId())
				.name(phone.getName())
				.company(phone.getCompany())
				.price(phone.getPrice())
				.regdate(result)
				.build();
	}
}
