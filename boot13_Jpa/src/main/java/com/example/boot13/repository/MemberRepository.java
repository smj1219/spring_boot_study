package com.example.boot13.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.boot13.entity.Member;

/*
 * extends JpaRepository<Entity 클래스, Entity 클래스 안에서 id 역활을 하는 칼럼(primary key)의 data type
 * 
 *  아래의 형식으로 만들게 되면 자동으로 dao (구현클래스) 역활을 하게 된다.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
	//필요하다면 정해진 형식에 따라 메소드를 추가할 수 있다.
	
	/*
	 *  정렬된 결과를 select 하는 메소드를 custom 으로 추가하기
	 *  - 정해진 형식이 있다
	 *  
	 *  findAllByOrderBy칼럼명Desc()
	 *  findAllByOrderBy칼럼명Asc()
	 * 
	 * 	칼럼명은 카멜 케이스로 작성
	 */
	public List<Member> findAllByOrderByNumDesc();

}
