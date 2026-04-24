package com.gips.taskapp.dto;

import lombok.Data;

/**
 * ログイン画面DTO
 */
@Data
public class LoginDto {

	// ログインID
	private String loginId;

	// パスワード
	private String password;

}
