package com.example.boot13.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name="PHONE_INFO")//테이블명
// entity 는 테이블 관련 설정을 하기 위한 클래스
public class Phone {
	// id 라는 칼럼은 primary key 값으로 설정되도록하겠다
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //시퀀스를 자동으로 만들어준다
	private long id; //필드명이 칼럼명이 된다
	private String company;
	private String name;
	// null 값이 가능한 Entity 필드는 반드시 참도 data type 이어야한다.
	@Column(nullable = true)
	private Integer price;
	//등록일을 저장하고 싶다면?
	@Column(nullable = false)
	private Date regdate;
	
	//Entity 를 영속화 하기 직전에 뭔가 작업할 게 있으면 @Prepersist 어노테이션을 활용하면 된다.
	@PrePersist
	public void onPersist() {
		//오라클에서는 데이터를 넣을 때 SYSDATE 함수를 이용해서 넣는 효과를 낸다.
		regdate=new Date();
	}
}
