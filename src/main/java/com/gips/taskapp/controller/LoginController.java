package com.gips.taskapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.gips.nextapp.login.LoginForm;
import com.gips.nextapp.login.LoginService;

@Controller
public class LoginController {

	private final LoginService service;

	public LoginController(LoginService service) {
		this.service = service;

	}

	public String login(Model model) {
		// TODO 自動生成されたメソッド・スタブ

		// ログインフォームをインスタンス化して、ビューで表示できるようにする
		LoginForm loginForm = new LoginForm();

		model.addAttribute("loginForm", loginForm);

		return "login";

	}

	// ログイン認証サービスを呼び出し、IDとパスワードを渡し、結果を受け取る
	@PostMapping("/")
	public String loginForm(Model model, LoginForm loginForm) {
		
		UserDto result = service.loginService()
	}

	// エラーメッセージを受け取り、テキストボックスを初期化したログイン画面に表示する

	// セッションにログインIDを追加する

	// セッションに権限IDを追加する

	// タスク一覧画面へリダイレクトする

}
