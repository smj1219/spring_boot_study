package com.example.boot14.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot14.dto.PostDto;

@Repository
public class PostDaoImpl implements PostDao {
	//mybatis 기반으로 select, insert, update, delete 작업을 하기 위한 핵심 의존객체
	@Autowired
	private SqlSession session;
	/*
	 *  mapper 의 namespace : post
	 *  sql id : getlist
	 *  resultType : PostDto
	 *  parameterType : 없음
	 */
	@Override
	public List<PostDto> getList() {
		return session.selectList("post.getList");
	}
	
	@Override
	public PostDto getdata(int id) {
		PostDto dto= session.selectOne("post.getdata", id);
		return dto;
	}
	
	@Override
	public void insert(PostDto dto) {
		session.insert("post.insert", dto);
	}

	

	@Override
	public void update(PostDto dto) {
		session.update("post.update", dto);
	}

	@Override
	public void delete(int id) {
		session.delete("post.delete", id);
	}

	@Override
	public int getSequence() {
		
		return session.selectOne("post.getSequence");
	}
	
	
}
