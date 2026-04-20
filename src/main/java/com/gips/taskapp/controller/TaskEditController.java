package com.gips.taskapp.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gips.taskapp.form.TaskEditForm;
import com.gips.taskapp.service.TaskEditService;

@Controller
public class TaskEditController {

	private final TaskEditService service;

	TaskEditController(TaskEditService service) {
		this.service = service;
	}

	@GetMapping("/taskRegister")
	String showRegisterView(Model model, HttpSession session) {

		// セッションのタスクIDを取り除く
		session.removeAttribute("taskId");
		// フォームをモデルに追加する
		model.addAttribute("taskEditForm", new TaskEditForm());

		// タスク編集画面のビューを返却する
		return "taskEdit";
	}

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

		// タスクIDに応じたタスクの情報を取得する
		TaskEditForm form = service.getTask(taskId);

		// 権限名をモデルに追加する
		model.addAttribute("roleName", roleName);
		// タスクの情報をモデルに追加する
		model.addAttribute("taskEditForm", form);

		// タスク編集画面のビューを返却する
		return "taskEdit";
	}

	@PostMapping("/submit")
	String submitTask(
			@ModelAttribute @Validated TaskEditForm form,
			BindingResult result,
			Model model,
			HttpSession session) {

		// セッションから権限名を取得する
		String roleName = (String) session.getAttribute("roleName");

		// バリデーションエラーがあった場合
		if (result.hasErrors()) {
			// 権限名をモデルに追加する
			model.addAttribute("roleName", roleName);
			// フォームをモデルに追加する
			model.addAttribute("taskEditForm", form);

			// タスク編集画面のビューを返却する
			return "taskEdit";
		}

		// セッションからタスクIDを取得する
		Integer taskId = (Integer) session.getAttribute("taskId");

		// タスクの情報を登録する
		service.saveTask(roleName, taskId, form);

		// タスク一覧画面へリダイレクトする
		return "redirect:/taskList";
	}

}
