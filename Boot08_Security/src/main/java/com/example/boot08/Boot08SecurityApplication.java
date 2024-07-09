package com.example.boot08;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Boot08SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot08SecurityApplication.class, args);
		
		//운영 체제의 실행 환경 얻어내기
		Runtime rt= Runtime.getRuntime();
		try {
			//크롬 브라우저  실행해서 원하는 url 로딩 시키기
			rt.exec("cmd /c start chrome.exe http://localhost:8888/boot08");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
