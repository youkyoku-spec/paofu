package com.gips.taskapp.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gips.taskapp.common.Constants;
import com.gips.taskapp.dto.TaskListDto;
import com.gips.taskapp.service.TaskListService;

/**
 * コントローラ(タスク一覧)
 *
 * タスク一覧のビューとサービスを制御する
 *
 */
@Controller
public class TaskListController {

	@Autowired
	private TaskListService taskListService;

	/**
	 * タスク一覧画面初期表示
	 * ルートにGETでアクセスされた際に呼び出されるメソッド
	 *
	 * @param session	セッション
	 * @return 呼出すビュー
	 */
	@GetMapping("/taskList")
	public String init(@RequestParam(required = false) String status, Model model, HttpSession session) {
		// セッションからログインユーザー情報（login_id、role_name）を取得する
		String loginId = (String) session.getAttribute("loginId");
		String roleName = (String) session.getAttribute("roleName");

		// タスク一覧を初期化
		List<TaskListDto> taskList = null;

		if (Constants.MEMBER.equals(roleName)) {
			// メンバーの場合
			// メンバーのタスク一覧取得サービスを呼び出して、ログインユーザーのタスクのみ取得する
			taskList = taskListService.getMemberTask(loginId, status);
		} else if (Constants.LEADER.equals(roleName)) {
			// リーダーの場合
			// リーダーのタスク一覧取得サービスを呼び出して、全てのタスクを取得する
			taskList = taskListService.getLeaderTask(loginId, status);
		}

		// 取得したタスク一覧をモデルに設定する
		model.addAttribute("taskList", taskList);

		// 権限名をモデルに設定する
		model.addAttribute("roleName", roleName);

		// 選択状態保持用
		model.addAttribute("status", status);

		// タスク一覧画面のビューを返却する
		return "task/taskList";

	}

	/**
	 * タスク編集
	 * 編集を押すとタスク編集画面へ遷移する
	 * @param session	セッション
	 * @param taskId	タスクID
	 * @param roleName	権限名
	 * 
	 * @return 呼出すビュー
	 */
	@GetMapping("/submitTask")
	public String taskEdit(@RequestParam Integer taskId, @RequestParam String roleName, HttpSession session) {
		// タスクID、権限名をセッションに格納
		session.setAttribute("taskId", taskId);
		session.setAttribute("roleName", roleName);

		// タスク編集画面へ遷移する
		return "redirect:/taskEdit";

	}

	/**
	 * タスク削除
	 * 削除を押すと確認ダイアログ後データを削除する
	 * @param taskId	タスクID
	 * 
	 * @return 呼出すビュー
	 */
	@PostMapping("/removeTask")
	public String taskDelete(@RequestParam Integer taskId) {
		// タスク削除サービスを呼び出して、対象データをDBから削除する
		taskListService.deleteTask(taskId);

		// 一覧画面へリダイレクトする
		return "redirect:/taskList";
	}

	/**
	 * ログアウト
	 * ログアウトを押すと確認ダイアログ後ログイン画面へ遷移する
	 * @param session	セッション
	 * 
	 * @return 呼出すビュー
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

}
