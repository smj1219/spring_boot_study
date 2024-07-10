package com.example.boot10.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot10.dao.PostDao;
import com.example.boot10.dto.PostDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController //@ResponseBody 의 기능이 모든 메소드에 포함된다.
public class PostController {
	@Autowired
	private PostDao dao;
	
	@GetMapping("/post")
	public List<PostDto> getList(){
		return dao.getList();
	}
	
	@GetMapping(value="/post/{id}")
	public PostDto getdata(@PathVariable("id") int id) {
		PostDto dto = dao.getdata(id);
		return dto;
	}
	/*
	@PostMapping("/post")
	public PostDto insert(String title, String author) {
		// @Bulider 의 기능을 이용해서 PostDto 객체에 데이터를 담으면서 객체의 참조값 얻어내기
		PostDto dto=PostDto.builder().title(title).author(author).build();
		//dao 를 이용해서 dto 에 저장된 정보를 DB에 저장하기
		dao.insert(dto);
		return dto;
	}
	*/
	
	//위 메소드를 간략하게 줄인 내용 
	@PostMapping("/post")
	public PostDto insert(PostDto dto) {
		//글의 번호를 미리 얻어낸다
		int id=dao.getSequence();
		//dao 에 글번호를 담는다
		dto.setId(id);
		dao.insert(dto);
		return dto;
	}
	
	@DeleteMapping(value="/post/{id}")
	public String delete(@PathVariable("id") int id) {
		dao.delete(id);
		
		return " delete";
	}
	
	@PutMapping(value="/post/{id}")
	public PostDto update(@PathVariable("id")int id, PostDto dto) {
		dto.setId(id);
		dao.update(dto);
		
		return dto;
	}

}
