package com.gips.taskapp.service;

import java.util.List;

import com.gips.taskapp.dto.TaskEditForm;
import com.gips.taskapp.dto.UserDto;

/**
 * タスク編集サービスインターフェイス
 */
public interface TaskEditService {

	// タスクIDに応じたタスク情報を取得する
	TaskEditForm getTask(Integer taskId);

	// ユーザー一覧を取得する
	List<UserDto> getUserList();

	// タスクの編集、登録を行う
	void saveTask(String roleId, Integer taskId, TaskEditForm form);

}
