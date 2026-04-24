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
	 * @param loginId		ログインID
	 * @param limit			表示する件数
	 * @param offset		ページ移動のためのスキップ数
	 * @return タスク一覧
	 */
	List<TaskListDto> getMemberTask(@Param("loginId") String loginId, @Param("limit") int limit,
			@Param("offset") int offset);

	/**
	 * リーダーのタスク一覧取得
	 *
	 * @param loginId		ログインID
	 * @param limit		    表示する件数
	 * @param offset		ページ移動のためのスキップ数
	 * @return タスク一覧
	 */
	List<TaskListDto> getLeaderTask(@Param("loginId") String loginId, @Param("limit") int limit,
			@Param("offset") int offset);

	/**
	 * タスクを削除
	 *
	 * @param taskId		タスクID
	 */
	void deleteTask(@Param("taskId") Integer taskId);

	/**
	 * メンバーのタスク一覧総数
	 *
	 * @param loginId		ログインID
	 * 
	 * @return タスク一覧総数
	 */
	int countMemberTask(@Param("loginId") String loginId);

	
	/**
	 * リーダーのタスク一覧総数
	 *
	 * @return タスク一覧総数
	 */
	int countLeaderTask();
}
