package com.gips.taskapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gips.taskapp.dto.TaskListDto;

/**
 * マッパー(タスク一覧取得)
 *
 * タスク一覧取得マッパー
 *
 */
@Mapper
public interface TaskListMapper {

	/**
	 * メンバーのタスク一覧取得
	 *
	 * @param loginId		ユーザID
	 * @return タスク一覧
	 */
	List<TaskListDto> getMemberTask(@Param("loginId") String loginId);

	/**
	 * リーダーのタスク一覧取得
	 *
	 * @param loginId		ユーザID
	 * @return タスク一覧
	 */
	List<TaskListDto> getLeaderTask(@Param("loginId") String loginId);

	/**
	 * タスクを削除
	 *
	 * @param taskId		タスクID
	 */
	void deleteTask(@Param("taskId") Integer taskId);
}
