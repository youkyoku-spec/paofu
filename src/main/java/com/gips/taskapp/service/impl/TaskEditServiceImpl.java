package com.gips.taskapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gips.taskapp.common.Constants;
import com.gips.taskapp.dto.TaskEditDto;
import com.gips.taskapp.dto.TaskEditForm;
import com.gips.taskapp.dto.UserDto;
import com.gips.taskapp.mapper.TaskEditMapper;
import com.gips.taskapp.service.TaskEditService;

@Service
public class TaskEditServiceImpl implements TaskEditService {

	private final TaskEditMapper mapper;

	public TaskEditServiceImpl(TaskEditMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public TaskEditForm getTask(Integer taskId) {

		// DBからタスクIDに応じたDTOを取得する
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
		// DBからユーザー一覧を取得する
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
		if (roleId == Constants.LEADER) {
			mapper.updateTaskAsLeader(dto);

			return;
		}

		// メンバー時のタスク更新処理
		mapper.updateTaskAsMember(dto);
	}

}
