package com.example.boot14.repository;

import java.util.List;

import com.example.boot14.dto.MemberDto;

public interface MemberDao {
	public List<MemberDto> getList();
	public void insert(MemberDto dto);
	public void delete(int num);
	public MemberDto getData(int num);
	public void update(MemberDto dto);
	public int getSequence();
}
