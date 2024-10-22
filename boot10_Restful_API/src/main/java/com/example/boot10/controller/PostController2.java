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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController //@ResponseBody 의 기능이 모든 메소드에 포함된다.
@RequestMapping("/v2")
public class PostController2 {
	//필요한 서비스 객체를 DI받는다
	@Autowired private PostService service;
	
	
	
	@GetMapping("/posts")
	public List<PostDto> getList(){
		//글 전체 목록을 서비스 객체를 이용해서 얻어온다음
		List<PostDto> list=service.getAll();
		return list;
	}
	
	@GetMapping(value="/posts/{id}")
	public PostDto getdata(@PathVariable("id") int id) {
		//서비스를 이용해서 글을 저장하고 리턴해주는 PosrDto 를 컨트롤러에서 리턴해준다
		return service.getContent(id);
	}
 
	/*
	@PostMapping("/post")
	public PostDto insert(String title, String author) {
		// @Builder 의 기능을 이용해서 PostDto 객체에 데이터를 담으면서 객체의 참조값 얻어내기 
		PostDto dto=PostDto.builder().title(title).author(author).build();
		//dao 를 이용해서 dto 에 저장된 정보를 DB 에 저장하기 
		dao.insert(dto);
		//현재 추가된 정보를 리턴하기 
		return dto;
	}
	*/
	
	/*
	 * 요청의 body 에 json 문자열이 전송되면 요청 파라미터를 추출하는 방법이 다르다
	 * 
	 * spring 프레임 워크에서는 @RequestBody 라는 어노테이션을 붙여주면 된다.
	 * 
	 * 그러면 json 문자열의 key 값과 dto 의 필드명이 일치하면 데이터가 추출되어서 필드에 저장된다.
	 * 
	 */
	@PostMapping("/posts")
	public PostDto insert(@RequestBody PostDto dto) {
		return service.addContent(dto);
	}
	
	// 삭제한 글 정보를 리턴하는 delete 메소드
	@DeleteMapping(value="/posts/{id}")
	public PostDto delete(@PathVariable("id") int id) {
		return service.removeContent(id);
	}
	
	@PutMapping(value="/posts/{id}")
	public PostDto update(@PathVariable("id")int id,@RequestBody PostDto dto) {
		//PostDto 에 경로 변수로 넘어오는 수정할 글번호도 담아서
		dto.setId(id);
		//서비스를 이용해서 수정한다
		service.updateContent(dto);
		return dto;
	}
}
