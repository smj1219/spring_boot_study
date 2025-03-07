package com.example.boot13.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.boot13.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

	/*
	 * findAll => 모든 목록
	 * ByOrder => 정렬
	 * ByEmpnoAsc => 사원번호에 대해서 오름차순
	 */
	public List<Emp> findAllByOrderByEmpnoAsc();
	
	/*
	 *  검색 키워드를 반영하는 매소드
	 *  ename 에 keyword 가 포함된 목록 select
	 *  
	 *  EnameContaining => 사원이름에 keyword 가 포함된 
	 */
	public List<Emp> findByEnameContaining(String keyword);
	
	//EnameContainingOrJobContaining => 사원 이름 혹은 직책에 keyword 가 포함된 
	public List<Emp> findByEnameContainingOrJobContaining(String keyword, String keyword2); 

	/*
	 * 페이징처리와 검색 키워드를 같이 처리 할 수도 있다
	 * 대신 return type 은 List 가 아닌 page 객체이다.
	 */
	public Page<Emp> findByEnameContaining(String keywrd, Pageable pageable);
	
	/*
	 * 실행할 쿼리문도 마음대로 지정하고 메소드명도 마음대로 지정할 수 있다.
	 * 
	 * - 칼럼에 별칭을 붙여야 한다.
	 */
	
	/*
	 * Java Persistence Query Language (JPQL)
	 * - JPQL 은 SQL 문과 유사하지만 엔티티 와 속성에 기반하여 작성되며, 데이터 베이스 종속적이지 않음
	 * - JPQL 만의 문법이 존재한다
	 * - 테이블명 대신에 = > Entity 명
	 * - Entity 의 별칭은 필수
	 * - select 된 row 의 정보를 Entity 또는 Dto 에 담을 수있다.
	 */
	@Query(value="select e from Emp e order by e.empno asc")
	public List<Emp> getListAll();
	
	//selection 인자를 전달 할 수 있다 :변수명
	@Query(value="select e from Emp e where sal > :sal order by e.sal asc")
	public List<Emp> getList(int sal);
	
	//selection 인자를 여러 개 전달 할 수 있다 :변수명, :변수명2
	@Query(value="select e from Emp e where sal > :sal and sal < :sal2 order by e.sal asc")
	public List<Emp> getListBetWeen(int sal, int sal2);
	
	//selection 인자를 순서대로 전달 할 수 있다 ?1, ?2 여기서 1, 2 는 순서
	@Query(value="select e from Emp e where sal > ?1 and sal < ?2 order by e.sal asc")
	public List<Emp> getListBetWeen2(int sal, int sal2);
}
