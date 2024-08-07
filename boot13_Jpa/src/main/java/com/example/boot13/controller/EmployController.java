package com.example.boot13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boot13.dto.DeptDto;
import com.example.boot13.dto.EmpDeptDto;
import com.example.boot13.entity.Dept;
import com.example.boot13.entity.Emp;
import com.example.boot13.repository.DeptRepository;
import com.example.boot13.repository.EmpRepository;




@Controller
public class EmployController {
	//테스트의 편의를 위해 서비스 만들지 않고 바로 Repository 객체 활용하기
	@Autowired private EmpRepository empRepo;
	
	@Autowired private DeptRepository deptRepo;
	
	@GetMapping("/emp/list4")
	public String list4(Model model, String keyword) {
		//검색 keyword 가 이름(ename) 검색 이라고 가정하자
		keyword="E";
		
		
//		List<EmpDeptDto> list = empRepo.findByEnameContaining(keyword)
//				.stream().map(EmpDeptDto::toDto).toList();
		
//		List<EmpDeptDto> list = empRepo.findByEnameContainingOrJobContaining(keyword, keyword)
//		.stream().map(EmpDeptDto::toDto).toList();
		
		Sort sort=Sort.by(Order.asc("dept.deptno"), Order.desc("sal"));
		
		Pageable pageable = PageRequest.of(1-1, 5, sort);
		
		List<EmpDeptDto> list =  empRepo.findByEnameContaining(keyword, pageable)
		.stream().map(EmpDeptDto::toDto).toList();
		
		model.addAttribute("list",list);
		
		return "emp/list";
	}
	
	
	@GetMapping("/emp/list3")
	public String list3(Model m, @RequestParam(defaultValue = "1") int pageNum) {
		//한 페이지에 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=5;
		//하단 페이지를 몇개씩 표시할 것인지
		final int PAGE_DISPLAY_COUNT=5;
		//empno 에 대해서 오름차순 정렬하겠다는 정보를 담고 있는 Sort 객체 만들어내기
		Sort sort= Sort.by(Sort.Direction.ASC, "empno");
		Pageable pageable = PageRequest.of(pageNum-1, PAGE_ROW_COUNT, sort);
		//pageable 객체를 이용해서 해당 페이지에 맞는 정보를 얻어낸다
		Page<Emp> page=empRepo.findAll(pageable);
		//Page<Emp> 를 stream 으로 만들어서 map() 함수를 이용해서 List<EmpDeptDto> 로 변경하기
		List<EmpDeptDto> list=page.stream().map(EmpDeptDto::toDto).toList();
			 
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1; 
		//전체 페이지의 갯수 구하기
		int totalPageCount = page.getTotalPages();
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum = totalPageCount; //보정해 준다. 
		}
				
		//view page 에서 필요한 값을 Model 객체에 담기 
		m.addAttribute("list", list);
		m.addAttribute("startPageNum", startPageNum);
		m.addAttribute("endPageNum", endPageNum);
		m.addAttribute("totalPageCount", totalPageCount);
		m.addAttribute("pageNum", pageNum);
		return "emp/list3";
		
	}
	
	@GetMapping("/emp/list2")
	public String list2(Model m, int pageNum) {
		final int PAGE_ROW_COUNT=8;
		Sort sort= Sort.by(Sort.Direction.ASC, "empno"); //사원번호에 대해서 오름차순 정렬
		/*
		 * .of (페에지 인덱스, 한페이지에 내타낼 갯수, 정렬객체)
		 * 
		 * pageRequest 객체가 리턴되는데 pageRequest는 pageable 인터페이스를 구현한 객체이다
		 * 
		 */
		Pageable pageable = PageRequest.of(pageNum-1, PAGE_ROW_COUNT, sort);
		//pageable 객체를 이용해서 해당 페이지에 맞는 정보를 얻어낸다
		Page<Emp> page=empRepo.findAll(pageable);
		//Page<Emp> 를 stream 으로 만들어서 map() 함수를 이용해서 List<EmpDeptDto> 로 변경하기
		List<EmpDeptDto> list=page.stream().map(EmpDeptDto::toDto).toList();
		
		m.addAttribute("list",list);
		return "emp/list2";
		
	}
	
	
	@GetMapping("/emp/list")
	public String list(Model model) {
		//List<EmpDto> list= empRepo.findAll().stream().map(EmpDto::toDto).toList();
		//List<EmpDeptDto> list = empRepo.findAll().stream().map(EmpDeptDto::toDto).toList();
		
		//JpaRepository 에 존재하는 메소드를 이용해서 목록 얻어오기
		List<Emp> empList = empRepo.findAll();
		
		//empRepository 에 직접 추가한 메소드 이용해서 목록 얻어오기
		//List<Emp> empList=empRepo.findAllByOrderByEmpnoAsc();
		
		//List<Emp> empList=empRepo.getList(2000);
		
		//List<Emp> 를 stream 으로 만들어서 map 함수를 이용해서 List<EmpDeptDto> 로 변경
		List<EmpDeptDto> list = empList.stream().map(EmpDeptDto::toDto).toList();
		
		//Model 객체에 담고
		model.addAttribute("list", list);
		//템플릿 페이지에서 사원목록 응답
		return "emp/list";
	}
	
	@GetMapping("/emp/dept_detail")
	public String deptList(int deptno, Model model) {
		//부서 번호를 이용해서 Dept 객체 얻어내기
		Dept dept=deptRepo.findById(deptno).get();
		//Entity 를 dto 로 변형해서
		DeptDto dto = DeptDto.toDto(dept);
		//Model 객체에 담는다
		model.addAttribute("dto", dto);
		
		return "emp/dept_detail";
	}
	
	
}
