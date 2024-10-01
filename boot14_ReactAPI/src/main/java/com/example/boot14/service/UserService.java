package com.example.boot14.service;

import com.example.boot14.dto.UserDto;

public interface UserService {
	public void addUser(UserDto dto);
	public UserDto getInfo();
	public void updateUser(UserDto dto);
	public void updatePassword(UserDto dto);
	public boolean canUse(String userName);
}
