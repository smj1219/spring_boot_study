package com.example.boot13.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.boot13.dto.DeptDto;
import com.example.boot13.dto.EmpDeptDto;
import com.example.boot13.dto.EmpDto;
import com.example.boot13.entity.Dept;
import com.example.boot13.repository.DeptRepository;
import com.example.boot13.repository.EmpRepository;


@Controller
public class EmployController {
	//테스트의 편의를 위해 서비스 만들지 않고 바로 Repository 객체 활용하기
	@Autowired private EmpRepository empRepo;
	
	@Autowired private DeptRepository deptRepo;
	
	@GetMapping("/emp/list")
	public String list(Model model) {
		//List<EmpDto> list= empRepo.findAll().stream().map(EmpDto::toDto).toList();
		List<EmpDeptDto> list = empRepo.findAll().stream().map(EmpDeptDto::toDto).toList();
		
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
