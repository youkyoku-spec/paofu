package com.gips.taskapp.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * タスク編集フォーム
 */
@Data
public class TaskEditForm {

	// タスクID
	private Integer taskId;

	// 作業者
	@NotBlank(message = "作業者を選択してください", groups = LeaderGroup.class)
	private String assigneeId;

	// タスク名
	@NotBlank(message = "タスク名を入力してください", groups = LeaderGroup.class)
	@Size(max = 10, message = "タスク名は10文字以内で入力してください")
	private String taskName;

	// タスク内容
	@NotBlank(message = "タスク内容を入力してください", groups = LeaderGroup.class)
	@Size(max = 100, message = "タスク内容は100文字以内で入力してください")
	private String taskDetail;

	// 開始日
	@NotNull(message = "開始日を入力してください", groups = LeaderGroup.class)
	private LocalDate startDate;

	// 完了予定日
	@NotNull(message = "完了予定日を入力してください", groups = LeaderGroup.class)
	private LocalDate dueDate;

	// 完了日
	private LocalDate completedDate;

	//進捗率
	@NotNull(message = "進捗率を入力してください")
	@Min(value = 0, message = "進捗率が無効な値です")
	@Max(value = 100, message = "進捗率が無効な値です")
	private Integer progress = 0;

	// メモ
	@Size(max = 100, message = "メモは100文字以内で入力してください")
	private String memo;

}
