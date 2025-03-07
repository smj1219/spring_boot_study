package com.example.boot11.service;

import java.awt.Dialog.ModalExclusionType;

import org.springframework.ui.Model;

import com.example.boot11.dto.CafeCommentDto;
import com.example.boot11.dto.CafeDto;

public interface CafeService {
	public void getList(Model model, CafeDto dto);
	public void saveContent(CafeDto dto);
	public void getDetail(Model model, CafeDto dto);
	public void delete(int num);
	public void getData(Model model, int dto);
	public void updateContent(CafeDto dto);
	
	//댓글 저장 서비스
	public void saveComment(CafeCommentDto dto);
	//댓글 삭제 서비스
	public void deleteComment(int num);
	//댓글 수정 서비스
	public void updateComment(CafeCommentDto dto);
	//댓글 목록을 model 에 담아주는 서비스
	public void getCommentList(Model model, CafeCommentDto dto);
}
