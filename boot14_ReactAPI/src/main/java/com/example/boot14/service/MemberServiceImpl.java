package com.example.boot14.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot14.dto.MemberDto;
import com.example.boot14.repository.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired private MemberDao dao;
	
	@Override
	public MemberDto addMember(MemberDto dto) {
		int num= dao.getSequence();
		dto.setNum(num);
		dao.insert(dto);
		return dto;
	}

	@Override
	public void updateMember(MemberDto dto) {
		dao.update(dto);
	}

	@Override
	public void deleteMember(int num) {
		dao.delete(num);
	}

	@Override
	public MemberDto seleteOne(int num) {
		
		return dao.getData(num);
	}

	@Override
	public List<MemberDto> selectList() {

		return dao.getList();
	}

}
