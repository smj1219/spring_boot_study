package com.example.boot07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.example.boot07.dto.FileDto;
/*
 * @PropertySource(value="커스텀 properties 파일 위피")
 * classpath: 는 resources 폴더를 가르킨다.
 */
@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Boot07FileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot07FileUploadApplication.class, args);
		
		// lombok 의 기능 테스트
		FileDto dto=new FileDto();
		FileDto dto2=FileDto.builder().num(1).writer("작성자").title("제목").build();
		
	}

}
