package com.example.boot11.repository;

import java.util.List;

import com.example.boot11.dto.FileDto;

public interface FileDao {
	public List<FileDto> getFileList();
	public void insertFile(FileDto dto);
	public void delete(int num);
	public int getSequence();
}
