package com.gips.taskapp.dto;

import lombok.Data;

/**
 * ユーザーDTO
 */
@Data
public class UserDto {

	// エラーメッセージ
	private String message;

	// 権限ID
	private Integer roleId;

	// ログインID
	private String loginId;

	// パスワード
	private String password;

	// ユーザー名
	private String userName;

	// 権限名
	private String roleName;
}
