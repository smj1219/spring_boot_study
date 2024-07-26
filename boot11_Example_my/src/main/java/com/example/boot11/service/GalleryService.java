package com.example.boot11.service;

import java.util.List;

import org.springframework.ui.Model;

import com.example.boot11.dto.CafeCommentDto;
import com.example.boot11.dto.CafeDto;
import com.example.boot11.dto.GalleryCommentDto;
import com.example.boot11.dto.GalleryDto;

public interface GalleryService {
	public void getList(Model model, GalleryDto dto);
	public GalleryDto getData(int num);
	public void insert(GalleryDto dto);
	public void delete(int num);
	public void getDetail(Model model, GalleryDto dto);
	
	//댓글 저장 서비스
	public void saveComment(GalleryCommentDto dto);
	//댓글 삭제 서비스
	public void deleteComment(int num);
	//댓글 수정 서비스
	public void updateComment(GalleryCommentDto dto);
	//댓글 목록을 model 에 담아주는 서비스
	public void getCommentList(Model model, GalleryCommentDto dto);
}
