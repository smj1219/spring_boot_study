package com.example.boot11.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.boot11.dto.CafeCommentDto;
import com.example.boot11.dto.CafeDto;
import com.example.boot11.service.CafeService;

import jakarta.websocket.Session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CafeController {
	
	@Autowired private CafeService service;
	
	@GetMapping("/cafe/comment_list")
	public String commentList(Model model,  CafeCommentDto dto) {
		//CafeCommentDto 에는 pageNum, ref_group 이 들어있다(GET 방식 파라미터)
		service.getCommentList(model, dto);
		
		try {
			//테스트를 위해 임의로 스레드를 3초 정도 지연시키기
			Thread.sleep(3000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		return "cafe/comment_list";
	}
	
	
	@ResponseBody//Map 객체를 리턴하면 json 문자열이 응답되도록 추가된 어노테이션
	@GetMapping("/cafe/comment_delete")
	public Map<String, Object> commentDelete(int num){
		//num 은 GET 방식 파라미터로 전달되는 삭제할 댓글의 번호
		service.deleteComment(num);
		
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		return map;
	}
	
	@ResponseBody
	@PostMapping("/cafe/comment_update")
	public Map<String, Object> commentUpdate(CafeCommentDto dto){
		service.updateComment(dto);
		//수정된 글의 정보를 json 으로 응답
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		map.put("num", dto.getNum());
		map.put("content", dto.getContent());
		return map;
	}
	
	
	/*
	 *  CafeCommentDto 에는 ref_group, target_id, content 3개의 정보가 들어 있다. 
	 *  
	 *  ( 대댓글인 경우에는 comment_group 번호도 같이 넘어온다 )
	 */
	@PostMapping("/cafe/comment_insert")
	public String commentInsert(CafeCommentDto dto) {
		//댓글 저장 처리를 하고 
		service.saveComment(dto);
		//해당글 자세히 보기로 다시 리다일렉트 시킨다 
		return "redirect:/cafe/detail?num="+dto.getRef_group();
	}	
	
	@PostMapping("/cafe/update")
	public String update(CafeDto dto) {
		//서비스객체를 이용해서 수정 반영하고 
		service.updateContent(dto);
		//해당글 자세히 보기로 리다일렉트 이동 (GET 방식 parameter 로  글번호도 전달해야 한다)
		return "redirect:/cafe/detail?num="+dto.getNum();
	}
	
	@GetMapping("/cafe/updateform")
	public String updateForm(Model model, int num) {
		//수정할 글정보를 Model 객체에 담아주는 서비스 메소드 
		service.getData(model, num);
		return "cafe/updateform";
	}
	
	@GetMapping("/cafe/delete")
	public String delete(int num) { // /cafe/delete?num=x
		//서비스 메소드를 이용해서 삭제하기 
		service.delete(num);
		return "redirect:/cafe/list";
	}
	
	
	@GetMapping("/cafe/detail")
	public String detail(Model model, CafeDto dto) {
		service.getDetail(model, dto);
		return "cafe/detail";
	}
	
	@PostMapping("/cafe/insert")
	public String insert(CafeDto dto) {
		service.saveContent(dto);
		return "cafe/insert";
	}
	
	@GetMapping("/cafe/insertform")
	public String insertForm() {
		
		return "cafe/insertform";
	}
	
	@GetMapping("/cafe/list")
	public String list(Model model, CafeDto dto) {
		//pageNum 또는 keyword 가 파라미터로 전달된다면 dto 에 들어 있다. 
		service.getList(model, dto);
		
		// templates/cafe/list.html 타임리프 페이지에서 응답 
		return "cafe/list";
	}
	
	
}
