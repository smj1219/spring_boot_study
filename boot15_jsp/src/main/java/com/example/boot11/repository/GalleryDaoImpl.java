package com.example.boot11.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot11.dto.CafeDto;
import com.example.boot11.dto.GalleryDto;

@Repository
public class GalleryDaoImpl implements GalleryDao {

	@Autowired private SqlSession session;

	@Override
	public List<GalleryDto> getGalleryList(GalleryDto dto) {
		return session.selectList("gallery.getList", dto);
	}

	@Override
	public void insertGallery(GalleryDto dto) {
		session.insert("gallery.insert", dto);
	}

	@Override
	public void delete(int num) {
		session.delete("gallery.delete", num);
	}

	@Override
	public GalleryDto getData(int num) {
		return session.selectOne("gallery.getdata", num);
	}

	@Override
	public GalleryDto getDetail(GalleryDto dto) {
		return session.selectOne("gallery.getDetail", dto);
	}

	@Override
	public int getCount(GalleryDto dto) {
		return session.selectOne("gallery.getCount", dto);
	}
}
