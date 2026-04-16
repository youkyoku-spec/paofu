package com.gips.nextapp.login;

import org.springframework.stereotype.Service;

/**
 * サービス(ログイン)
 *
 * ログインのビジネスロジックを実装する
 *
 */
@Service
public class LoginService {

	/**
	 * 認証
	 * ユーザIDとパスワードによる認証処理のメソッド
	 *
	 * @param id		ユーザID
	 * @param password	パスワード
	 * @return ログインの成否
	 */
	boolean login(String id, String password) {

		// 引数の入力チェックを行う。
		// １つでもエラーがあった場合には、処理を異常終了する。

		// ユーザIDとパスワードの必須チェック
		if (id.equals("") || password.equals("")) {
			return false;
		}

		// 処理を正常終了する。
		return true;
	}

}
