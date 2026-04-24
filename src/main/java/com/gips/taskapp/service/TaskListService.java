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
	List<TaskListDto> getTaskList(String loginId, String roleName, String status, int page);

	// タスク一覧削除サービス
	void deleteTask(Integer taskId);

	// タスク一覧総数取得
	int countAfterFilter(String loginId, String roleName, String status);
}
