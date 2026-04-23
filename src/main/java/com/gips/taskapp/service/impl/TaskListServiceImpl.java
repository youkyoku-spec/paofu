package com.gips.taskapp.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	private TaskListMapper taskListMapper;

	/**
	 * メンバーのタスク一覧取得サービス
	 *
	 * @param loginId		ユーザID
	 * @return タスク一覧
	 */
	public List<TaskListDto> getMemberTask(String loginId, String status) {

		List<TaskListDto> taskList = taskListMapper.getMemberTask(loginId);

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
	 * リーダーのタスク一覧取得サービス
	 *
	 * @param loginId		ユーザID
	 * @return タスク一覧
	 */
	public List<TaskListDto> getLeaderTask(String loginId, String status) {
		// リーダーのタスク一覧を取得
		List<TaskListDto> taskList = taskListMapper.getLeaderTask(loginId);

		// 状態判定を行う
		for (TaskListDto t : taskList) {
			t.setStatus(calcStatus(t));
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
	 * @param loginId		ユーザID
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

}
