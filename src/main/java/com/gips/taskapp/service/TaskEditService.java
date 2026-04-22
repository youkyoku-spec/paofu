package com.gips.taskapp.service;

import java.util.List;

import com.gips.taskapp.dto.TaskEditForm;
import com.gips.taskapp.dto.UserDto;

/**
 * タスク編集サービスインターフェイス
 */
public interface TaskEditService {

	/**
	 * タスクIDに応じたタスク編集フォームを取得する
	 * 
	 * @param taskId タスクID
	 * @return タスク編集フォーム
	 */
	TaskEditForm getTask(Integer taskId);

	/**
	 * ユーザー一覧を取得する
	 * 
	 * @return ユーザー一覧
	 */
	List<UserDto> getUserList();

	void saveTask(String roleId, Integer taskId, TaskEditForm form);

}
