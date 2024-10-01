package com.example.boot14.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//예외 처리를 하는 컨트롤러는 @ControllerAdvice 어노테이션을 붙여서 만든다.
@ControllerAdvice
public class ExceptionController {

	/*
	 * spring framework 가 동작하는 도중에 passwordException type 의 예외가 발생하면 이 메소드가 호출된다
	 */
	@ExceptionHandler(PasswordException.class)
	public ResponseEntity<Object> passwordException(PasswordException pe){
		//예외 메세지
		String msg = pe.getMessage();
		//에러 응답객체(이 객체에 담긴 내용이 에러가 응답될 때 같이 응답된다.)
		SimpleErrorResponse response = SimpleErrorResponse.builder()
										.code(401).message(msg).build();
		
		//return new ResponseEntity<>(response, HttpStatus.OK); //200번 정상적인 응답
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); //401번 에러 응답
	}
}
