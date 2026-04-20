package com.gips.taskapp.service.impl;

import com.gips.taskapp.dto.TaskEditForm;

public interface TaskEditServiceImpl {

	TaskEditForm getTask(int taskId);

	void saveTask(String roleId, Integer taskId, TaskEditForm form);

}
