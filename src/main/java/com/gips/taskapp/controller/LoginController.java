package com.gips.taskapp.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gips.taskapp.dto.LoginDto;
import com.gips.taskapp.dto.UserDto;
import com.gips.taskapp.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService service;

	@GetMapping("/login")
	public String login(Model model) {

		// ログインフォームをインスタンス化して、ビューで表示できるようにする
		LoginDto loginForm = new LoginDto();

		model.addAttribute("loginForm", loginForm);

		return "login";

	}

	// ログイン認証サービスを呼び出し、IDとパスワードを渡し、結果を受け取る
	@PostMapping("/")
	public String loginForm(Model model, @ModelAttribute UserDto loginForm, HttpSession session) {

		UserDto result = service.loginService(loginForm.getLoginId(), loginForm.getPassword());

		// エラーメッセージを受け取り、テキストボックスを初期化したログイン画面に表示する
		if (result.getMessage() != null) {

			model.addAttribute("message", result.getMessage());
			return "redirect:/login";

		}

		// セッションにログインIDを追加する
		session.setAttribute("loginId", result.getLoginId());
		// セッションに権限名を追加する
		session.setAttribute("roleName", result.getRoleName());
		// タスク一覧画面へリダイレクトする
		return "redirect:/taskList";
	}
}
