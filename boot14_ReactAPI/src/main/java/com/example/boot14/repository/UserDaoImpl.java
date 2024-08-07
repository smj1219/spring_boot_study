package com.example.boot14.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.boot14.dto.UserDto;



@Repository
public class UserDaoImpl implements UserDao {

	@Autowired private SqlSession session;
	
	@Override
	public UserDto getData(String userName) {
		/*
		 * mapper namespace => user
		 * sql id => getData
		 * parameperType => String
		 * resultType => UserDto
		 */
		return session.selectOne("user.getData",userName);
	}

	@Override
	public void insert(UserDto dto) {
		session.insert("user.insert", dto);
		
	}

	@Override
	public void updatePwd(UserDto dto) {
		session.update("user.updatePwd", dto);
	}

	@Override
	public void update(UserDto dto) {
		session.update("user.update", dto);
		
	}




}
