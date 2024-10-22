package com.example.boot11.repository;

import java.util.List;

import com.example.boot11.dto.GalleryCommentDto;

public interface GalleryCommentDao {
	//추가할 댓글의 글번호를 리턴하는 메소드
	public int getSequence();
	//댓글 추가
	public void insert(GalleryCommentDto dto);
	//댓글 목록
	public List<GalleryCommentDto> getList(GalleryCommentDto dto);
	//댓글 삭제
	public void delete(int num);
	//댓글 하나의 정보리턴
	public GalleryCommentDto getDate(int num);
	//댓글 수정
	public void update(GalleryCommentDto dto);
	//원글에 달린 댓글 갯수 얻어내기
	public int getCount(int ref_group);
	
}
