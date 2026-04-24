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
	 * タスク一覧取得サービス
	 *
	 * @param loginId		ユーザーID
	 * @param roleName		権限名
	 * @param status		状態
	 * @param limit		    表示する件数
	 * @param offset		ページ移動のためのスキップ数
	 * @return タスク一覧
	 */
	public List<TaskListDto> getTaskList(String loginId, String roleName,String status,int limit,int offset) {

		List<TaskListDto> taskList = null;
		
		if (Constants.MEMBER.equals(roleName)) {

			// メンバーの場合
			// メンバーのタスク一覧取得マッパーを呼び出して、ログインユーザーのタスクのみ取得する
			taskList = taskListMapper.getMemberTask(loginId, limit, offset);

		} else if (Constants.LEADER.equals(roleName)) {

			// リーダーの場合
			// リーダーのタスク一覧取得マッパーを呼び出して、全てのタスクを取得する
			taskList = taskListMapper.getLeaderTask(loginId, limit, offset);

		}

		// 状態判定を行う
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
	 * タスク削除サービス
	 *
	 * @param loginId		ユーザーID
	 * @return タスク一覧
	 */
	public void deleteTask(Integer taskId) {
		// タスクIDを渡して、対象データをDBから削除する
		taskListMapper.deleteTask(taskId);

	}

	/**
	 * 状態判定
	 *
	 * @param task		タスク一覧
	 * @return 状態
	 */
	private Status calcStatus(TaskListDto task) {
		LocalDate today = LocalDate.now();

		if (task.getProgress() == 100 || task.getCompletedDate() != null) {
			return Status.完了;
		}

		if (task.getDueDate() != null) {
			if (today.isAfter(task.getDueDate()))
				return Status.遅延;
			if (today.isEqual(task.getDueDate()) && task.getProgress() < 80)
				return Status.注意;
			if (today.isBefore(task.getDueDate()) && task.getProgress() == 0)
				return Status.未着手;
		}

		return Status.着手中;
	}

	/**
	 * メンバーのタスク一覧総数
	 *
	 * @param loginId		ユーザーID
	 * 
	 * @return 一覧総数
	 */
	public int countMemberTask(String loginId) {
		
		return taskListMapper.countMemberTask(loginId);
	}

	/**
	 * リーダーのタスク一覧総数
	 *
	 * @return 一覧数
	 */
	public int countLeaderTask() {
		
		return taskListMapper.countLeaderTask();
	}

}
