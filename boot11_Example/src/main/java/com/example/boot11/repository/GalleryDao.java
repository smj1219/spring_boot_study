package com.example.boot11.repository;

import java.util.List;

import com.example.boot11.dto.CafeDto;
import com.example.boot11.dto.GalleryDto;

public interface GalleryDao {
	public List<GalleryDto> getGalleryList(GalleryDto dto);
	public void insertGallery(GalleryDto dto);
	public void delete(int num);
	public GalleryDto getData(int num);
	public GalleryDto getDetail(GalleryDto dto);
	public int getCount(GalleryDto dto);
}
