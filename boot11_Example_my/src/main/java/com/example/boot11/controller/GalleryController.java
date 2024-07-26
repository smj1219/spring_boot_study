package com.example.boot11.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import com.example.boot11.dto.GalleryCommentDto;
import com.example.boot11.dto.GalleryDto;
import com.example.boot11.service.GalleryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GalleryController {
	
	@Autowired private GalleryService service;
	
	@GetMapping("/gallery/list")
	public String getList(Model model, GalleryDto dto) {
		service.getList(model, dto);
		
		return "gallery/list";
	}
	
	@GetMapping("/gallery/uploadform")
	public String uploadform() {
		return "gallery/uploadform";
	}
	
	@PostMapping("/gallery/upload")
	public String upload(GalleryDto dto) {
		service.insert(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("/gallery/delete")
	public String delete(int num) {
		service.delete(num);
		return "redirect:list";
	}
	
	@GetMapping("/gallery/detail")
	public String getdata(GalleryDto dto, Model model) {
		service.getDetail(model, dto);
		return "gallery/detail";
	}
	
	@GetMapping("/gallery/comment_list")
	public String commentList(Model model,  GalleryCommentDto dto) {
		//CafeCommentDto 에는 pageNum, ref_group 이 들어있다(GET 방식 파라미터)
		service.getCommentList(model, dto);
		
		try {
			//테스트를 위해 임의로 스레드를 3초 정도 지연시키기
			Thread.sleep(3000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		return "gallery/comment_list";
	}
	
	
	@ResponseBody//Map 객체를 리턴하면 json 문자열이 응답되도록 추가된 어노테이션
	@GetMapping("/gallery/comment_delete")
	public Map<String, Object> commentDelete(int num){
		//num 은 GET 방식 파라미터로 전달되는 삭제할 댓글의 번호
		service.deleteComment(num);
		
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		return map;
	}
	
	@ResponseBody
	@PostMapping("/gallery/comment_update")
	public Map<String, Object> commentUpdate(GalleryCommentDto dto){
		service.updateComment(dto);
		//수정된 글의 정보를 json 으로 응답
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		map.put("num", dto.getNum());
		map.put("content", dto.getContent());
		return map;
	}
	
	
	@PostMapping("/gallery/comment_insert")
	public String commentInsert(GalleryCommentDto dto) {
		//댓글 저장 처리를 하고 
		service.saveComment(dto);
		//해당글 자세히 보기로 다시 리다일렉트 시킨다 
		return "redirect:/gallery/detail?num="+dto.getRef_group();
	}	
	
}
