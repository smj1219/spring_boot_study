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
import com.example.boot10.service.PostService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController //@ResponseBody 의 기능이 모든 메소드에 포함된다.
public class PostController {
	//필요한 서비스 객체를 DI받는다
	@Autowired private PostService service;
	
	@GetMapping("/post")
	public List<PostDto> getList(){
		//글 전체 목록을 서비스 객체를 이용해서 얻어온다음
		List<PostDto> list=service.getAll();
		return list;
	}
	
	@GetMapping(value="/post/{id}")
	public PostDto getdata(@PathVariable("id") int id) {
		//서비스를 이용해서 글을 저장하고 리턴해주는 PosrDto 를 컨트롤러에서 리턴해준다
		return service.getContent(id);
	}
 
	@PostMapping("/post")
	public PostDto insert(PostDto dto) {
		return service.addContent(dto);
	}
	
	// 삭제한 글 정보를 리턴하는 delete 메소드
	@DeleteMapping(value="/post/{id}")
	public PostDto delete(@PathVariable("id") int id) {
		return service.removeContent(id);
	}
	
	@PutMapping(value="/post/{id}")
	public PostDto update(@PathVariable("id")int id, PostDto dto) {
		//PostDto 에 경로 변수로 넘어오는 수정할 글번호도 담아서
		dto.setId(id);
		//서비스를 이용해서 수정한다
		service.updateContent(dto);
		return dto;
	}
}
