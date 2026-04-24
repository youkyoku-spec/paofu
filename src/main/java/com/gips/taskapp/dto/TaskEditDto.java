package com.gips.taskapp.dto;

import java.time.LocalDate;

import lombok.Data;

/**
 * タスク編集DTO
 */
@Data
public class TaskEditDto {

	// タスクID
	private Integer taskId;

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
	private Integer progress;

	// メモ
	private String memo;

}
