package com.gips.taskapp.dto;

import java.time.LocalDate;

import com.gips.taskapp.common.Status;

import lombok.Data;

/**
 * タスク一覧DTO
 *
 */
@Data
public class TaskListDto {

	// ユーザー名
	private String userName;

	// タスクID
	private String taskId;

	// 作業者
	private String assigneeId;

	// タスク名
	private String taskName;

	// タスク内容
	private String taskDetail;

	// 開始日
	private LocalDate startDate;

	// 完了予定日
	private LocalDate dueDate;

	// 完了日
	private LocalDate completedDate;

	//進捗率
	private int progress;

	// メモ
	private String memo;

	// 状態
	private Status status;
}
