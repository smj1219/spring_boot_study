package com.example.boot14.repository;

import java.util.List;

import com.example.boot14.dto.CafeDto;

public interface CafeDao {
	//pageNum 과 검색 조건에 해당하는 글목록을 리턴하는 메소드
	public List<CafeDto> getList(CafeDto dto);
	// 검색 조건에 해당하는 글의 갯수를 리턴하는 메소드 
	public int getCount(CafeDto dto);
	// 글 추가 메소드
	public void insert(CafeDto dto);
	// 글 번호에 해당하는 글정보를 리턴하는 메소드
	public CafeDto getData(int num);
	// 검색 조건과 글 번호를 CafeDto 에 담아서 전달하면 글하나의 정보를 리턴하는 메소드(이전글의 글번호, 다음글의 글번호 포함)
	public CafeDto getDetail(CafeDto dto);
	// 글 삭제 메소드
	public void delete(int num);
	// 글 수정 메소드
	public void update(CafeDto dto);
}
