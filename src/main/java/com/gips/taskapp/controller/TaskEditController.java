package com.gips.taskapp.controller;

import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gips.taskapp.common.Constants;
import com.gips.taskapp.dto.LeaderGroup;
import com.gips.taskapp.dto.MemberGroup;
import com.gips.taskapp.dto.TaskEditForm;
import com.gips.taskapp.service.TaskEditService;

/**
 * タスク編集コントローラー
 */
@Controller
public class TaskEditController {

	private final TaskEditService service;
	private final SmartValidator validator;

	TaskEditController(TaskEditService service, SmartValidator validator) {
		this.service = service;
		this.validator = validator;
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

		// ビューに渡す情報をモデルに追加する
		setupModel(model, session.getAttribute("roleName"), new TaskEditForm());

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

		// セッションからタスクIDを取得する
		Integer taskId = (Integer) session.getAttribute("taskId");

		// タスクIDのnullチェック
		if (taskId == null) {
			// タスク一覧画面へリダイレクトする
			return "redirect:/taskList";
		}

		// タスクIDに応じたタスク情報を取得する
		TaskEditForm form = service.getTask(taskId);

		// ビューに渡す情報をモデルに追加する
		setupModel(model, session.getAttribute("roleName"), form);

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
			@ModelAttribute TaskEditForm form,
			BindingResult result,
			Model model,
			HttpSession session) {

		// セッションから権限名を取得する
		String roleName = (String) session.getAttribute("roleName");

		// 権限名によってバリデーションを切り替える
		if (roleName.equals(Constants.LEADER)) {
			validator.validate(form, result, LeaderGroup.class);
		} else {
			validator.validate(form, result, MemberGroup.class);
		}

		// 日付の整合性を検証し、結果を取得する
		Map<String, String> errors = service.checkDate(
				form.getStartDate(), form.getDueDate(), form.getCompletedDate());

		// バリデーションエラーがある場合
		if (result.hasErrors() || !errors.isEmpty()) {

			// 日付の整合性エラーがある場合
			if (!errors.isEmpty()) {
				for (Map.Entry<String, String> entry : errors.entrySet()) {
					// バリデーションエラーのメッセージを追加する
					result.rejectValue(entry.getKey(), "", entry.getValue());
				}
			}

			// ビューに渡す情報をモデルに追加する
			setupModel(model, roleName, form);

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

	/**
	 * ビューに渡す情報をモデルに追加する
	 * 
	 * @param model モデル
	 * @param roleName 権限ID
	 * @param form タスク情報
	 */
	private void setupModel(Model model, Object roleName, TaskEditForm form) {

		// 権限名をモデルに追加する
		model.addAttribute("roleName", roleName);
		// フォームをモデルに追加する
		model.addAttribute("taskEditForm", form);
		// ユーザー一覧をモデルに追加する
		model.addAttribute("userList", service.getUserList());
	}

}
