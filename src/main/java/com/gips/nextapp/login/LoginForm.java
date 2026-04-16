package com.gips.nextapp.login;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.thymeleaf.util.StringUtils;

import lombok.Data;

/**
 * フォーム(ログイン画面)
 *
 * ログイン画面のビューとサービスでやり取りするデータ
 *
 */
@Data
public class LoginForm {

	// ユーザID
	@NotBlank(message = "未入力エラー")
	private String userId;

	// パスワード
	@Size(min = 1, max = 4, message = "桁数エラー")
	private String password;

	@AssertTrue(message = "相関エラー")
	public boolean isTextEmpty() {
		if (StringUtils.isEmpty(userId) &&
				StringUtils.isEmpty(password)) {
			return false;
		}
		return true;
	}
}
