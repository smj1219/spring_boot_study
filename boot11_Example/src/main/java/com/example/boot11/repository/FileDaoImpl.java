package com.example.boot11.repository;

import java.util.List;

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
	public List<FileDto> getFileList() {
		return session.selectList("file.getFileList");
	}


	@Override
	public void insertFile(FileDto dto) {
		session.insert("file.insert", dto);
	}


	@Override
	public void delete(int num) {
		session.delete("file.delete", num);
		
	}

	@Override
	public int getSequence() {
		return session.selectOne("post.getSequence");
	}

}
