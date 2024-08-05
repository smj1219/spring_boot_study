package com.example.boot13.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.boot13.dto.CookDto;
import com.example.boot13.entity.Cook;
import com.example.boot13.repository.CookRepository;

@Service
public class CookServiceImpl implements CookService {

	@Autowired CookRepository repo;
	
	@Override
	public void getList(Model model) {
		List<CookDto> list = repo.findAll().stream().map(CookDto::toDto).toList();
		model.addAttribute("list",list);
		
	}

	@Override
	public void insert(CookDto dto) {
		repo.save(Cook.toEntity(dto));
		
	}

	@Override
	public void delete(Long num) {
		repo.deleteById(num);
		
	}

	@Override
	public void getData(Long num, Model model) {
		CookDto dto = CookDto.toDto(repo.findById(num).get());
		model.addAttribute("dto", dto);
		
	}

	@Override
	public void update(CookDto dto) {
		repo.save(Cook.toEntity(dto));
		
	}

}
