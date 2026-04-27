package com.gips.taskapp.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

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

	private final TaskListService taskListService;

	public TaskListController(TaskListService taskListService) {
		this.taskListService = taskListService;
	}

	/**
	 * タスク一覧画面初期表示
	 * ルートにGETでアクセスされた際に呼び出されるメソッド
	 * @param status	状態
	 * @param page	    ページ
	 * @param session	セッション
	 * 
	 * @return 呼び出すビュー
	 */
	@GetMapping("/taskList")
	public String init(@RequestParam(required = false) String status, @RequestParam(defaultValue = "0") int page,
			Model model, HttpSession session) {

		// セッションからログインユーザー情報（login_id、role_name）を取得する
		String loginId = (String) session.getAttribute("loginId");
		String roleName = (String) session.getAttribute("roleName");

		// タスク一覧を初期化
		List<TaskListDto> taskList = null;

		// タスク一覧取得サービスを呼び出して、タスクを取得する
		taskList = taskListService.getTaskList(loginId, roleName, status);

		// タスク一覧総数を取得
		int totalCount = taskList.size();

		// 1ページの件数を決める
		int limit = Constants.PAGE;
		// 取得開始位置を計算
		int fromIndex = page * limit;
		// 取得終了位置を計算
		int toIndex = Math.min(fromIndex + limit, taskList.size());
		// 範囲外チェックして、指定範囲だけ返す
		taskList = (fromIndex > taskList.size()) ? List.of() : taskList.subList(fromIndex, toIndex);

		int totalPages = (int) Math.ceil((double) totalCount / Constants.PAGE);
		// 10件不足する場合、1件に設定
		if (totalPages == 0) {
			totalPages = 1;
		}

		// 取得したタスク一覧をモデルに追加する
		model.addAttribute("taskList", taskList);

		// 権限名をモデルに追加する
		model.addAttribute("roleName", roleName);

		// 選択状態保持用
		model.addAttribute("status", status);

		// ページ設定
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);

		// タスク一覧画面のビューを返却する
		return "task/taskList";

	}

	/**
	 * タスク編集
	 * 編集を押すとタスク編集画面へリダイレクトする
	 * @param taskId	タスクID
	 * @param roleName	権限名
	 * @param session	セッション
	 * 
	 * @return 呼び出すビュー
	 */
	@GetMapping("/submitTask")
	public String taskEdit(@RequestParam Integer taskId, @RequestParam String roleName, HttpSession session) {
		// タスクID、権限名をセッションに追加する
		session.setAttribute("taskId", taskId);
		session.setAttribute("roleName", roleName);

		// タスク編集画面へリダイレクトする
		return "redirect:/taskEdit";

	}

	/**
	 * タスク削除
	 * 削除を押すと確認ダイアログで"はい"を入力後データを削除する
	 * @param taskId	タスクID
	 * 
	 * @return 呼び出すビュー
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
	 * ログアウトを押すと確認ダイアログで"はい"を入力後ログイン画面へリダイレクトする
	 * @param session	セッション
	 * 
	 * @return 呼び出すビュー
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		// セッションデータを全部消す
		session.invalidate();
		// ログイン画面へリダイレクトする
		return "redirect:/login";
	}

}
