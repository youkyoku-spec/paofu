package com.gips.nextapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gips.nextapp.SessionBean;

/**
 * コントローラ(ログイン)
 *
 * ログインのビューとサービスを制御する
 *
 */
@Controller
public class LoginController {

	// サービスクラスのインスタンス化
	private final LoginService service;

	// セッションスコープBeanのインスタンス化
	private final SessionBean sessionBean;

	// コンストラクタ インジェクション
	public LoginController(LoginService service, SessionBean sessionBean) {
		this.service = service;
		this.sessionBean = sessionBean;
	}

	/**
	 * ログイン画面初期表示
	 * ルートにGETでアクセスされた際に呼び出されるメソッド
	 *
	 * @param model	モデル
	 * @return 呼出すビュー
	 */
	@GetMapping("/")
	String init(Model model) {

		// ログイン画面のFormをインスタンス化し、Modelに追加する。
		LoginForm loginForm = new LoginForm();
		model.addAttribute("loginForm", loginForm);

		// セッションからシステム名を取得して、Modelに追加する。
		model.addAttribute("systemName", sessionBean.getSystemName());

		// ログイン画面のViewを返却する。
		return "login";
	}

	/**
	 * 認証
	 * ルートにPOSTでアクセスされた際に呼び出されるメソッド
	 *
	 * @param loginForm	ビューから受け取るフォーム
	 * @param result バリデーションの処理結果
	 * @param model	モデル
	 * @return 呼出すビュー
	 */
	@PostMapping("/")
	String login(@ModelAttribute @Validated LoginForm loginForm, BindingResult result, Model model) {

		System.out.println(result.getFieldErrors());

		// バリデーションの処理結果
		// 結果が正常ではなかった場合にはログイン画面のViewを返却して処理を終了する。
		if (result.hasErrors()) {
			model.addAttribute("loginForm", loginForm);
			return "login";
		}

		// 認証サービスを呼び出す。
		// 結果が正常ではなかった場合にはログイン画面のViewを返却して処理を終了する。
		if (!service.login(loginForm.getUserId(), loginForm.getPassword())) {
			model.addAttribute("form", loginForm);
			return "login";
		}

		// /listをリダイレクトし、ユーザ一覧表示処理を呼び出す。
		return "redirect:list";
	}

	/**
	 * ユーザ一覧表示
	 *
	 * @return 呼出すビュー
	 */
	@GetMapping("/list")
	String list() {

		// ユーザ一覧画面のViewを返却する。
		return "userList";
	}
}
