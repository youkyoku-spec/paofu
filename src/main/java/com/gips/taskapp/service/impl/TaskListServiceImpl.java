package com.gips.taskapp.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gips.taskapp.common.Constants;
import com.gips.taskapp.common.Status;
import com.gips.taskapp.dto.TaskListDto;
import com.gips.taskapp.mapper.TaskListMapper;
import com.gips.taskapp.service.TaskListService;

/**
 * サービス(タスク一覧)
 *
 * タスク一覧のビジネスロジックを実装する
 *
 */
@Service
public class TaskListServiceImpl implements TaskListService {

	private final TaskListMapper taskListMapper;

	public TaskListServiceImpl(TaskListMapper taskListMapper) {
		this.taskListMapper = taskListMapper;
	}

	/**
	 * タスク一覧取得(ページ数入れ)
	 *
	 * @param loginId		ユーザーID
	 * @param roleName		権限名
	 * @param status		状態
	 * @param page		    ページ数
	 * 
	 * @return タスク一覧
	 */
	public List<TaskListDto> getTaskList(String loginId, String roleName, String status, int page) {

		// タスク一覧を取得
		List<TaskListDto> taskList = getTaskList(loginId, roleName, status);

		// 1ページの件数を決める
		int limit = Constants.PAGE;
		// 取得開始位置を計算
		int fromIndex = page * limit;
		// 取得終了位置を計算
		int toIndex = Math.min(fromIndex + limit, taskList.size());

		// 範囲外チェック
		if (fromIndex > taskList.size()) {
			return List.of();
		}

		// 指定範囲だけ返す
		return taskList.subList(fromIndex, toIndex);
	}

	/**
	 * タスク削除
	 *
	 * @param loginId		ユーザーID
	 * @return タスク一覧
	 */
	public void deleteTask(Integer taskId) {

		// タスクIDを渡して、対象データをDBから削除する
		taskListMapper.deleteTask(taskId);

	}

	/**
	 * タスク一覧総数取得
	 * @param loginId		ユーザーID
	 * @param roleName		権限名
	 * @param status		状態
	 * 
	 * @return タスク一覧数
	 */
	public int countAfterFilter(String loginId, String roleName, String status) {

		// タスク一覧の総数を取得
		return getTaskList(loginId, roleName, status).size();
	}

	/**
	 * タスク一覧を取得
	 * @param loginId		ユーザーID
	 * @param roleName		権限名
	 * @param status		状態
	 * 
	 * @return タスク一覧数
	 */
	public List<TaskListDto> getTaskList(String loginId, String roleName, String status) {

		List<TaskListDto> taskList = null;

		if (Constants.MEMBER.equals(roleName)) {

			// メンバーの場合
			// メンバーのタスク一覧取得マッパーを呼び出して、ログインユーザーのタスクのみ取得する
			taskList = taskListMapper.getMemberTask(loginId);

		} else if (Constants.LEADER.equals(roleName)) {

			// リーダーの場合
			// リーダーのタスク一覧取得マッパーを呼び出して、全てのタスクを取得する
			taskList = taskListMapper.getLeaderTask();

		}

		// 状態計算
		for (TaskListDto task : taskList) {
			task.setStatus(calcStatus(task));
		}

		// 状態フィルタ
		if (status != null && !status.isEmpty()) {
			taskList = taskList.stream()
					.filter(t -> t.getStatus().name().equals(status))
					.toList();
		}
		return taskList;
	}

	/**
	 * 状態判定
	 *
	 * @param task		タスク一覧
	 * @return 状態
	 */
	private Status calcStatus(TaskListDto task) {
		LocalDate today = LocalDate.now();

		// 進捗率 = 100％ の場合、「完了」
		if (task.getProgress() == 100 || task.getCompletedDate() != null) {
			return Status.完了;
		}

		if (task.getDueDate() != null) {
			// 現在日時 > 完了予定日 かつ 進捗率 < 100％ の場合、「遅延」と表示
			if (today.isAfter(task.getDueDate()))
				return Status.遅延;
			if (today.isEqual(task.getDueDate()) && task.getProgress() < 80)
				//現在日時 = 完了予定日 かつ 進捗率 < 80％ の場合、「注意」と表示する
				return Status.注意;
			if (today.isBefore(task.getDueDate()) && task.getProgress() == 0)
				// 現在日時 > 完了予定日 かつ 進捗率 < 100％ の場合
				return Status.未着手;
		}

		// それ以外の場合は「着手中」と表示
		return Status.着手中;
	}
}
