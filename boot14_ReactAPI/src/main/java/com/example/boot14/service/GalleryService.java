package com.example.boot14.service;

import java.util.Map;

import com.example.boot14.dto.GalleryDto;

public interface GalleryService {
	//pageNum 에 해당 하는 gallery 페이징 정보를 Map 에 담아 리턴하는 메소드
	public Map<String, Object> selectPage(int pageNum);
	public GalleryDto addGallery(GalleryDto dto);
}
