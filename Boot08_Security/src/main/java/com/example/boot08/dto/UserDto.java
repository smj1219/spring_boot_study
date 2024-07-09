package com.example.boot08.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // .action(), .action2(),... 형태로 객체를 만들 수 있게 해준다.
@AllArgsConstructor // 모든 인자를 전달 받는 생성자
@NoArgsConstructor //디폴트 생성자
@Data //setter getter 메소드 등을 만들어준다
public class UserDto {
	//숫자로 된 아이디는 PK
	private int id;
	//사용자명은 중복된 데이ㅓ가 들어가지 않도록 UNIQUE KEY 를 설정해야한다.
	private String userName;
	private String password;
	private String email;
	//Authority 정보를 저장할 칼럼 ROLE_XXX 형식이다.
	private String role;
}
