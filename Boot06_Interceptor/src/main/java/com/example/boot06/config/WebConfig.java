package com.example.boot06.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.boot06.interceptor.LoginInterceptor;


// 설정과 관련된 bean 이 된다
@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	//bean 들 끼리 디펜던시를 주입을 받을 수 있다
	@Autowired
	private LoginInterceptor logininter;//로그인 여부를 검사할 인터셉터
	
	// 인터셉터를 레지스트리에 등록할 때 호출되는 메소드를 오버라이드 한다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 로그인 인터셉터를 거치지 않을 요청 목록
		String[] whiteList= {"/sub/play","/cafe/detail", "/cafe/list"};
		
		// 동작을 원하는 인터셉터를 registry 객체를 이용해서 등록한다.어떤 요청경로에서 동작을 할지를 구성하는 곳 
		registry.addInterceptor(logininter)
			// addPathPatterns(인터셉터가 동작할 요청 pattern_로그인 여부를 확인) 
			//excludePathPatterns(인터셉터가 동작하지 않는(예외) pattern _로그인 여부 비확인)
			.addPathPatterns("/sub/*","/cafe/*").excludePathPatterns(whiteList);
		
	}
}
