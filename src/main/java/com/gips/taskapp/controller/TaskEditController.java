package com.gips.taskapp.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gips.taskapp.dto.TaskEditForm;
import com.gips.taskapp.service.impl.TaskEditServiceImpl;

/**
 * タスク編集コントローラー
 */
@Controller
public class TaskEditController {

	private final TaskEditServiceImpl service;

	TaskEditController(TaskEditServiceImpl service) {
		this.service = service;
	}

	/**
	 * タスク登録時の初期表示
	 * 
	 * @param model モデル
	 * @param session セッション
	 * @return ビューの名前
	 */
	@GetMapping("/taskRegister")
	String showRegisterView(Model model, HttpSession session) {

		// セッションのタスクIDを取り除く
		session.removeAttribute("taskId");
		// フォームをモデルに追加する
		model.addAttribute("taskEditForm", new TaskEditForm());
		// ユーザー一覧をモデルに追加する
		model.addAttribute("userList", service.getUserList());

		// タスク編集画面のビューを返却する
		return "task/taskEdit";
	}

	/**
	 * タスク編集時の初期表示
	 * 
	 * @param model モデル
	 * @param session セッション
	 * @return ビューの名前
	 */
	@GetMapping("/taskEdit")
	String showEditView(Model model, HttpSession session) {

		// セッションから権限名を取得する
		String roleName = (String) session.getAttribute("roleName");
		// セッションからタスクIDを取得する
		Integer taskId = (Integer) session.getAttribute("taskId");

		// タスクIDのnullチェック
		if (taskId == null) {
			// タスク一覧画面へリダイレクトする
			return "redirect:/taskList";
		}

		// タスクIDに応じたタスク情報を取得する
		TaskEditForm form = service.getTask(taskId);

		// 権限名をモデルに追加する
		model.addAttribute("roleName", roleName);
		// タスク情報をモデルに追加する
		model.addAttribute("taskEditForm", form);
		// ユーザー一覧をモデルに追加する
		model.addAttribute("userList", service.getUserList());

		// タスク編集画面のビューを返却する
		return "task/taskEdit";
	}

	/**
	 * タスクの登録、編集を行う
	 * 
	 * @param form タスク情報
	 * @param result バリデーション結果
	 * @param model モデル
	 * @param session セッション
	 * @return ビューの名前
	 */
	@PostMapping("/submit")
	String submitTask(
			@ModelAttribute @Validated TaskEditForm form,
			BindingResult result,
			Model model,
			HttpSession session) {

		// セッションから権限名を取得する
		String roleName = (String) session.getAttribute("roleName");

		// バリデーションエラーのチェック
		if (result.hasErrors()) {
			// 権限名をモデルに追加する
			model.addAttribute("roleName", roleName);
			// フォームをモデルに追加する
			model.addAttribute("taskEditForm", form);
			// ユーザー一覧をモデルに追加する
			model.addAttribute("userList", service.getUserList());

			// タスク編集画面のビューを返却する
			return "task/taskEdit";
		}

		// 開始日<=完了予定日の整合性チェック
		if (form.getStartDate() != null && form.getDueDate() != null
				&& form.getStartDate().isAfter(form.getDueDate())) {

			// 完了予定日にエラーメッセージを紐づける
			result.rejectValue("dueDate", "",
					"完了予定日は開始日以降の日付を入力してください");

			// 権限名をモデルに追加する
			model.addAttribute("roleName", roleName);
			// フォームをモデルに追加する
			model.addAttribute("taskEditForm", form);
			// ユーザー一覧をモデルに追加する
			model.addAttribute("userList", service.getUserList());

			// タスク編集画面のビューを返却する
			return "task/taskEdit";
		}

		// 開始日<=完了日の整合性チェック
		if (form.getStartDate() != null && form.getCompletedDate() != null
				&& form.getStartDate().isAfter(form.getCompletedDate())) {

			// 完了日にエラーメッセージを紐づける
			result.rejectValue("completedDate", "",
					"完了日は開始日以降の日付を入力してください");

			// 権限名をモデルに追加する
			model.addAttribute("roleName", roleName);
			// フォームをモデルに追加する
			model.addAttribute("taskEditForm", form);
			// ユーザー一覧をモデルに追加する
			model.addAttribute("userList", service.getUserList());

			// タスク編集画面のビューを返却する
			return "task/taskEdit";
		}

		// セッションからタスクIDを取得する
		Integer taskId = (Integer) session.getAttribute("taskId");

		// タスク情報を登録する
		service.saveTask(roleName, taskId, form);

		// タスク一覧画面へリダイレクトする
		return "redirect:/taskList";
	}

}
