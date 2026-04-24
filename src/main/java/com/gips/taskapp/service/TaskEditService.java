package com.gips.taskapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.gips.taskapp.dto.TaskEditForm;
import com.gips.taskapp.dto.UserDto;

/**
 * タスク編集サービスインターフェイス
 */
public interface TaskEditService {

	/**
	 * タスクIDに応じたタスク情報を取得する
	 * 
	 * @param taskId タスクID
	 * @return タスク情報
	 */
	TaskEditForm getTask(Integer taskId);

	/**
	 * ユーザー一覧を取得する
	 * 
	 * @return ユーザー一覧
	 */
	List<UserDto> getUserList();

	/**
	 * タスクの編集、登録を行う
	 * 
	 * @param roleId 権限ID
	 * @param taskId タスクID
	 * @param form タスク情報
	 */
	void saveTask(String roleId, Integer taskId, TaskEditForm form);

	/**
	 * 日付の整合性を検証する
	 * 
	 * @param startDate 開始日
	 * @param dueDate 完了予定日
	 * @param completedDate 完了日
	 * @return 検証結果
	 */
	Map<String, String> checkDate(LocalDate startDate, LocalDate dueDate, LocalDate completedDate);

}
