package com.gips.taskapp.service.impl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gips.taskapp.common.Constants;
import com.gips.taskapp.dto.TaskEditDto;
import com.gips.taskapp.dto.TaskEditForm;
import com.gips.taskapp.dto.UserDto;
import com.gips.taskapp.mapper.TaskEditMapper;
import com.gips.taskapp.service.TaskEditService;

/**
 * タスク編集サービス
 */
@Service
@Transactional
public class TaskEditServiceImpl implements TaskEditService {

	private final TaskEditMapper mapper;

	public TaskEditServiceImpl(TaskEditMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public TaskEditForm getTask(Integer taskId) {

		// タスクIDに応じたDTOを取得する
		TaskEditDto dto = mapper.findTaskById(taskId);

		// フォームをインスタンス化し、DTOの情報を渡す
		TaskEditForm form = new TaskEditForm();
		form.setTaskId(dto.getTaskId());
		form.setAssigneeId(dto.getAssigneeId());
		form.setTaskName(dto.getTaskName());
		form.setTaskDetail(dto.getTaskDetail());
		form.setStartDate(dto.getStartDate());
		form.setDueDate(dto.getDueDate());
		form.setCompletedDate(dto.getCompletedDate());
		form.setProgress(dto.getProgress());
		form.setMemo(dto.getMemo());

		// フォームを返却する
		return form;
	}

	@Override
	public List<UserDto> getUserList() {
		return mapper.findAllUsers();
	}

	@Override
	public void saveTask(String roleId, Integer taskId, TaskEditForm form) {

		// DTOをインスタンス化し、フォームの情報を渡す
		TaskEditDto dto = new TaskEditDto();
		dto.setTaskId(taskId);
		dto.setAssigneeId(form.getAssigneeId());
		dto.setTaskName(form.getTaskName());
		dto.setTaskDetail(form.getTaskDetail());
		dto.setStartDate(form.getStartDate());
		dto.setDueDate(form.getDueDate());
		dto.setCompletedDate(form.getCompletedDate());
		dto.setProgress(form.getProgress());
		dto.setMemo(form.getMemo());

		// タスク登録処理
		if (taskId == null) {
			mapper.insertTask(dto);

			return;
		}

		// リーダー時のタスク更新処理
		if (roleId.equals(Constants.LEADER)) {
			mapper.updateTaskAsLeader(dto);

			return;
		}

		// メンバー時のタスク更新処理
		mapper.updateTaskAsMember(dto);
	}

	@Override
	public Map<String, String> checkDate(
			LocalDate startDate, LocalDate dueDate, LocalDate completedDate) {

		// 結果を格納するマップ
		Map<String, String> result = new HashMap<>();

		// 開始日 <= 完了予定日の整合性チェック
		if (startDate != null && dueDate != null
				&& startDate.isAfter(dueDate)) {

			// 完了予定日にエラーメッセージを紐づける
			result.put("dueDate",
					"完了予定日は開始日以降の日付を入力してください");
		}

		// 開始日 <= 完了日の整合性チェック
		if (startDate != null && completedDate != null
				&& startDate.isAfter(completedDate)) {

			// 完了日にエラーメッセージを紐づける
			result.put("completedDate",
					"完了日は開始日以降の日付を入力してください");
		}

		// 結果を返却する
		return result;
	}

}
