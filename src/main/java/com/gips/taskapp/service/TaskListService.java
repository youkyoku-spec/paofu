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

	// メンバーのタスク一覧取得サービス
	List<TaskListDto> getMemberTask(String loginId);

	// リーダーのタスク一覧取得サービス
	List<TaskListDto> getLeaderTask(String loginId);

	// タスク一覧削除サービス
	void deleteTask(Integer taskId);
}
