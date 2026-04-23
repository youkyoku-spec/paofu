package com.gips.taskapp.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gips.taskapp.dto.LoginDto;
import com.gips.taskapp.dto.UserDto;
import com.gips.taskapp.service.LoginService;

/**
 * ログイン画面コントローラー
 */
@Controller
public class LoginController {

	private final LoginService service;

	public LoginController(LoginService service) {
		this.service = service;
	}

	/**
	 * ログイン画面の初期表示
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String login(Model model) {

		// ログインフォームをインスタンス化する
		LoginDto loginForm = new LoginDto();
		//モデルにログイン画面を追加する
		model.addAttribute("loginForm", loginForm);
		//ログイン画面を表示
		return "task/login";

	}

	/**
	 * ログイン認証サービスを呼び出し、IDとパスワードを渡し、結果を受け取る
	 * @param model
	 * @param loginForm
	 * @param session
	 * @return
	 */
	@PostMapping("/login")
	public String loginForm(Model model, @ModelAttribute LoginDto loginForm, HttpSession session) {

		UserDto result = service.loginService(loginForm.getLoginId(), loginForm.getPassword());

		// エラーメッセージを受け取る
		if (result.getMessage() != null) {
			//モデルにメッセージとログイン画面を追加
			model.addAttribute("message", result.getMessage());
			model.addAttribute("loginForm", loginForm);
			//ログイン画面を表示
			return "task/login";

		}

		// セッションにログインIDを追加する
		session.setAttribute("loginId", result.getLoginId());
		// セッションに権限名を追加する
		session.setAttribute("roleName", result.getRoleName());
		// タスク一覧画面へリダイレクトする
		return "redirect:/taskList";
	}
}
