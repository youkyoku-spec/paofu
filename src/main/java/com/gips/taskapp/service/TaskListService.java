package com.gips.taskapp.service;

import java.util.List;

import com.gips.taskapp.dto.TaskListDto;

/**
 * サービス(タスク一覧)
 *
 * タスク一覧のビジネスロジックのインターフェース
 *
 */
public interface TaskListService {

	// タスク一覧取得サービス
	List<TaskListDto> getTaskList(String loginId, String roleName, String status);

	// タスク一覧削除サービス
	void deleteTask(Integer taskId);

}
