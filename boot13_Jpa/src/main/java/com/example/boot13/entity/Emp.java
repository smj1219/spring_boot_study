package com.example.boot13.entity;

import java.util.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Emp {
	@Id
	private Integer empno;
	private String ename;
	private String job;
	private Integer mgr;//필드 선언시에 값을 넣어주면 기본값이 된다.
	private Date hiredate;
	private Double sal;
	private Double comm;
	//private Integer deptno;
	
	/*
	 * Emp 객체 하나는 사원 한 명의 정보를 가지고 있다
	 * Dept 객체 하나는 부서 하나의 정보를 가지고 있다
	 * Emp 객체 안에 있는 Dept 객체는 Emp 객체가 가지고 있는 해당 사원의 부서 정보를 가지게 하고 싶다.
	 */
	
	/*
	 * ManyToOne 또는 OneToOne 관꼐는 다른 Entity 를 필드로 가지고 있는다
	 * 
	 * ManyToOne 또는 ManyToMany 관꼐는 다른 Entity 를 List 의 Generic type 으로 가지고 있다.
	 */
	
	@ManyToOne // 많은 Emp 가 하나의 Dept 에 속해 있다.
	//이 칼럼의 이름은 deptno 이고 Dept Entity 테이블의 deptno 를 참조하고 있다.
	//@JoinColumn(name="deptno", referencedColumnName = "deptno") 
	@JoinColumn(name="deptno") //위 내용을 줄인 내용
	private Dept dept;
	
}
