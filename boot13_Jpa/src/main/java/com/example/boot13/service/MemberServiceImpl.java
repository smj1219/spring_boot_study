package com.example.boot13.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot13.dto.MemberDto;
import com.example.boot13.entity.Member;
import com.example.boot13.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

	//jpaRepository 를 상속받아서 만든 MemberRepository 주입받기
	@Autowired MemberRepository repo;
	
	@Override
	public void getList(Model model) {
		/*
		// Entity 가 여러개 들어있는 List 를 이용해서 List<MemverDto> 를 만들어서 Model 객체에 담아야한다.                                                       
		List<Member> entityList= repo.findAll();
		List<MemberDto> list=new ArrayList<MemberDto>();
		for(Member tmp:entityList) {
			list.add(MemberDto.toDto(tmp));
		}
		*/
		//List<MemberDto> list=repo.findAll().stream().map(item->MemberDto.toDto(item)).toList();
		
		/*
		 * - 메소드 참조 표현식
		 * 클래스명 :: 메소드명
		 */
		//List<MemberDto> list = repo.findAll().stream().map(MemberDto::toDto).toList();
		repo.findAllByOrderByNumDesc();
		List<MemberDto> list = repo.findAll().stream().map(MemberDto::toDto).toList();
		
		model.addAttribute("list",list);
		
	}

	@Override
	public void insert(MemberDto dto) {
		//dto 를 entity 로 변경을 해서
		//Member m=Member.toEntity(dto);
		//저장한다.
		//repo.save(m);
		repo.save(Member.toEntity(dto));
		
	}

	@Override
	public void delete(Long num) {
		//메소드 이용해서 삭제
		repo.deleteById(num);
		
	}

	@Override
	public void getData(Long num, Model model) {
		//회원정보를 이용해서 Member entity 객체를 얻어낸다
		Member m= repo.findById(num).get();
		MemberDto dto =MemberDto.toDto(m);
		model.addAttribute("dto", dto);
		
	}

	@Override
	public void update(MemberDto dto) {
		// save() 메소드는 insert 와 update 겸용이다
		repo.save(Member.toEntity(dto));
		
	}

	/*
	 * Member m1=repo.findById(1).get();
		Member m2=repo.findById(1).get();
		
		m1과 m2 는 동일한 객체이다 m1 == m2 는 true 
		
		만일 @Transactional 이라는 어노테이션이 서비스 메소드에 붇어 있으면
		해당 메소드 안에소 Entity 를 수정하면 수정된 내용을 DB 에 실제로 반영된다.
		(참고로 알아두고 수정할 때에는 Repository 의 메소드를 이용해서 수정하는 것이 좋다)
		m1.setName(수정할 이름);
		m1.setAddr(수정할 주소);
		
		를 호출하명 실제로 Db 에도 반영된다
		
		서비스의 특정 메소드를 하나의 Transaction 상에서 실행하려면 @Transactional 이라는 어노테이션을 붙이면 된다.
		
		
	 */
	@Transactional
	@Override
	public void update2(MemberDto dto) {
		// 수정할 회원의 번로를 이용해서 회원정보 entity 객체 얻어내기
		Member m1=repo.findById(dto.getNum()).get();
		Member m2=repo.findById(dto.getNum()).get();
		boolean isEqual=m1==m2;
		System.out.println("m1 과 m2 같은지 여부: "+ isEqual);
		//setter 메소드를 이용해서 이름과 주소 수정하기
		m1.setName(dto.getName());
		m1.setAddr(dto.getAddr());
		
	}

}
