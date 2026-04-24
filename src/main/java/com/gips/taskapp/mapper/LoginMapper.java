package com.gips.taskapp.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.gips.taskapp.dto.UserDto;

/**
 * ログイン画面マッパー
 */
@Mapper
public interface LoginMapper {
	// findUserはメソッド名
	/**
	 * // 一致するログインIDとパスワードをDBから受け取る、それ以外はnull
	 * @param loginId
	 * @param password
	 * @return
	 */
	UserDto findUser(String loginId, String password);
}
