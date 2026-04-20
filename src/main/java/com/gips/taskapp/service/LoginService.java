package com.gips.taskapp.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gips.taskapp.service.impl.Form;
import com.gips.taskapp.service.impl.LoginServiceImpl;

@Service
public class LoginService implements LoginServiceImpl {

	@Override
	public UserDto loginService(String loginId,String password) {
		// TODO 自動生成されたメソッド・スタブ

		// UseContextをインスタンス化する
		UserDto userContext = new UserDto();
		
		// 入力値がエラーかどうか判定する
		// エラーの場合メッセージをUserContextにset
		if()
			
			// DBから結果一覧のインスタンスを受け取る
			
			// 取得したインスタンスがnullならメッセージをUserContextにset
			
			// UserContextを返す
	}

	@Override
	public String login(Model model) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String loginForm(Model model, Form form) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
