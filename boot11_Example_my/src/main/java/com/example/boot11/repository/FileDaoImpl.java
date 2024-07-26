package com.example.boot11.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.boot11.dto.FileDto;

@Repository
public class FileDaoImpl implements FileDao {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public List<FileDto> getFileList(FileDto dto) {
		
		return session.selectList("file.getFileList", dto );
	}


	@Override
	public void insertFile(FileDto dto) {
		session.insert("file.insert", dto);
	}


	@Override
	public void delete(int num) {
		session.delete("file.deleteFile", num);
		
	}


	@Override
	public FileDto getDate(int num) {
		
		return session.selectOne("file.getData", num);
	}




	@Override
	public int getCount(FileDto dto) {
		// Dto 에 condition 과 key 는  null 일수도 있고 아닐 수도 있디.
		return session.selectOne("file.getCount", dto);
	}

	

}
