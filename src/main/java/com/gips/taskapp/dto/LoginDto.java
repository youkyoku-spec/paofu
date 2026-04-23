package com.gips.taskapp.dto;

import lombok.Data;

/**
 * ログイン画面DTO
 */
@Data
public class LoginDto {
	//入力された文字列を格納する
	private String loginId;
	private String password;

	//getter,setter
}
