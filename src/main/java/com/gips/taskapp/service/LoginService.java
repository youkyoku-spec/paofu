package com.gips.taskapp.service;

import com.gips.taskapp.dto.UserDto;

/**
 * 認証サービスインターフェース
 */
public interface LoginService {
	//一致したログインIDとパスワードがあった場合はユーザー情報を、無かった場合はエラーメッセージを返す
	public UserDto loginService(String loginId, String password);

}
