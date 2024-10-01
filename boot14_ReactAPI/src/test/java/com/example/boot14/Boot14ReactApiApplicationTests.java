package com.example.boot14;

import static org.assertj.core.api.Assertions.assertThat;
// 메소드를 static import 하면 이클래스에서 import 메소드를 마치 자기 꺼처럼 쓸 수 있다.
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.boot14.dto.MemberDto;
import com.example.boot14.repository.MemberDao;

// spring boot application 을 테스트하기 위한 어노테이션
@SpringBootTest
// DB 에 insert, update, delete 가 실제 반영되지않고 ROLLBACK 되도록 한다.
@Transactional
class Boot14ReactApiApplicationTests {
	
	
	@Autowired MemberDao dao;
	
	//@Test 어노테이션을 이용해서 테스트 case 메소드를 작성한다
	@Test
	public void hello() {
		// 어떤 메소드가 리턴해주는 값이라고 가정 
		int sum = 1+1;
		// 그 값은 반드시 2 여야 한다는 단언(assertion)
		// sum 이 2 이면 테스트는 pass 이고 2 가 아니면 테스트는 fail 이다.
		// Assertions.assertEquals(sum, 2); 아래와 같은 코드이나 클래스를 import 한 내용
		assertEquals(sum, 2);
	}

	//null 테스트
	@Test void testIsNull() {
		String str = null;
		// 반드시 null 이어야 하는 단언
		// null 이면 pass, null 이 아니면 fail
		assertNull(str);
	}
	
	//NotNull 테스트
	@Test void testNotNull(){
		String str = "kimgura";
		// 반드시 null 이 아니여야 한다는 단언
		// null 이 아니면 pass, null 이면 fail
		assertNotNull(str);
	}
	
	//boolean 테스트
	@Test void testIsTrue() {
		boolean isRun = true;
		// 반드시 true 여야 한다는 단언
		// true 면 pass. false 면 fail
		assertTrue(isRun);
		assertTrue(isRun, "달릴지 여부는 true 여야 한다.");
	}
	
	//dao 주입 여부 테스트
	@Test void testMemberDaoNotNull() {
		// 실제로 dao 가 null 이 아닌지 여부를 알수 있다
		assertNotNull(dao);
	}
	/*
	 * name : test_name
	 * addr : test_addr
	 * 인 회원 정보를 저장하고 작업의 성공 여부 테스트 하기
	 */
	@Test void testMemberDaoInsert() {
		int num = dao.getSequence();
		MemberDto dto = MemberDto.builder().num(num).name("test_name").addr("test_addr").build();
		// DB 에 저장한다.
		dao.insert(dto);
		// 저장된 결과를 다시 select 한다.
		MemberDto saveDto = dao.getData(num);
		// 제대로 잘 저장되었는지 비교해본다.
		assertEquals(saveDto.getName(), "test_name");
		assertEquals(saveDto.getAddr(), "test_addr");
		
	}
	
	//업데이트 성공 확인 테스트
	@Test void testMemberUpdate() {
		int num = 101;
		MemberDto dto = MemberDto.builder().num(num).name("test_name2").addr("test_addr2").build();
		dao.update(dto);
		MemberDto saveDto = dao.getData(num);
		assertEquals(saveDto.getName(), "test_name2");
		assertEquals(saveDto.getAddr(), "test_addr2");
	}
}
