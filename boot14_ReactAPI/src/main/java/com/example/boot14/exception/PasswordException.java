package com.example.boot14.exception;

public class PasswordException extends RuntimeException{

	//생성자로 전달되는 예외 메세지를 모두 부모 생성자에 전달한다
	public PasswordException(String msg) {
		super(msg);
	}
}
