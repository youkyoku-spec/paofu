package com.gips.taskapp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gips.taskapp.dto.UserDto;

@Mapper
public interface LoginMapper {
// findAllはメソッド名↓
	UserDto findUser(String loginId, String password);
}
