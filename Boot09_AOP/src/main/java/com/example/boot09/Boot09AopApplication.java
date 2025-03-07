package com.example.boot09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.boot09.util.Messenger;
import com.example.boot09.util.WritingUtil;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Boot09AopApplication {
	//spring boot application 이 실행될 때 호출되는 메소드
	public static void main(String[] args) {
		//내부적으로 Boot09AopApplication 객체가 생성된다 (spring bean container 도 만들어진다)
		SpringApplication.run(Boot09AopApplication.class, args);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
	
	}
	
	//Spring Bean Container 로 부터 WritingUtil 객체 DI 받기
	@Autowired WritingUtil util;
	
	@Autowired Messenger messenger;
	
	//이 클래스 (Boot09AopApplication) 객체가 성공적으로 생성된 이후에 호출되는 메소드
	@PostConstruct
	public void test() {
		util.writeLetter();
		util.writeReport();
		util.writeDiary();
		
		System.out.println("-----------------");
		messenger.sendGreeting("안녕하세요");
		messenger.sendGreeting("안녕하세요 똥개님");
		String msg=messenger.getMessenger();
		System.out.println("Messenger 객체로 부터 받은 메세지 : "+msg);
	}

}
