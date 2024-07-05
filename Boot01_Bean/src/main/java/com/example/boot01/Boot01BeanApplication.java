package com.example.boot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.boot01.service.HomeService;

@SpringBootApplication
public class Boot01BeanApplication {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(Boot01BeanApplication.class, args);
		
		HomeService service=ctx.getBean(HomeService.class);
		//원숭이의 집을 청소하려면?
		service.clean("원숭이");
		//주뎅이의 빨래를 하려면?
		service.wash("주뎅이");
		//바닥에 구멍을 뚫으려면?
		service.hole("바닥");
	}

}
