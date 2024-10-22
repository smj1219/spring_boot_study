package com.example.boot11.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot11.dto.GalleryCommentDto;

@Repository
public class GalleryCommentDaoImpl implements GalleryCommentDao {
	@Autowired private SqlSession session;

	@Override
	public int getSequence() {
		return session.selectOne("galleryComment.getSequence");
	}

	@Override
	public void insert(GalleryCommentDto dto) {
		session.insert("galleryComment.insert", dto);
	}

	@Override
	public List<GalleryCommentDto> getList(GalleryCommentDto dto) {
		return session.selectList("galleryComment.getList", dto);
	}

	@Override
	public void delete(int num) {
		session.delete("galleryComment.delete",num);
	}

	@Override
	public GalleryCommentDto getDate(int num) {
		return session.selectOne("galleryComment.getData", num);
	}

	@Override
	public void update(GalleryCommentDto dto) {
		session.update("galleryComment.update", dto);
	}

	@Override
	public int getCount(int ref_group) {
		return session.selectOne("galleryComment.getCount", ref_group);
	}
	
	
}
