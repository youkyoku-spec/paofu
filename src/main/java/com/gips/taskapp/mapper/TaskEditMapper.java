package com.gips.taskapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gips.taskapp.dto.TaskEditDto;
import com.gips.taskapp.dto.UserDto;

/**
 * タスク編集マッパー
 */
@Mapper
public interface TaskEditMapper {

	/**
	 * DBからタスクIDに応じたタスク情報を取得する
	 * 
	 * @param taskId
	 * @return タスク情報
	 */
	TaskEditDto findTaskById(@Param("taskId") Integer taskId);

	/**
	 * DBからユーザー一覧を取得する
	 * 
	 * @return ユーザー一覧
	 */
	List<UserDto> findAllUsers();

	/**
	 * タスク情報をDBに登録する
	 * 
	 * @param dto タスク情報
	 */
	void insertTask(TaskEditDto dto);

	/**
	 * DBのタスク情報を更新する(リーダー時)
	 * 
	 * @param dto タスク情報
	 */
	void updateTaskAsLeader(TaskEditDto dto);

	/**
	 * DBのタスク情報を更新する(メンバー時)
	 * 
	 * @param dto タスク情報
	 */
	void updateTaskAsMember(TaskEditDto dto);

}
