package com.example.boot01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomeServiceImpl implements HomeService {
	/*
	 * 필요한 type 을 필드로 선언하고 @Autowired 어노테이션을 붙여 놓으면 해당 객체가 자동 주입된다.
	 */
	@Autowired
	private Drill drill;
	
	@Override
	public void clean(String name) {
		System.out.println(name+" 의 집을 청소해요");
	}

	@Override
	public void wash(String name) {
		System.out.println(name+" 의 빨래를 빨아요~");
	}

	@Override
	public void hole(String name) {
		System.out.println(name+" 에 구멍을 뚫어요!");
		drill.hole();
	}
	
}
