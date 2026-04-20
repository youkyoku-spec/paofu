package com.gips.taskapp.service;

import org.springframework.stereotype.Service;

import com.gips.taskapp.form.TaskEditForm;
import com.gips.taskapp.service.impl.TaskEditServiceImpl;

@Service
public class TaskEditService implements TaskEditServiceImpl {

	@Override
	public TaskEditForm getTask(int taskId) {
		return null;
	}

	@Override
	public void saveTask(String roleId, Integer taskId, TaskEditForm form) {
	}

}
