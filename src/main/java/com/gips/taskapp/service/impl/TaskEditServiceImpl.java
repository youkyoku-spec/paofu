package com.gips.taskapp.service.impl;

import java.util.List;

import com.gips.taskapp.dto.TaskEditForm;
import com.gips.taskapp.dto.UserDto;

public interface TaskEditServiceImpl {

	TaskEditForm getTask(Integer taskId);

	List<UserDto> getAllUsers();

	void saveTask(String roleId, Integer taskId, TaskEditForm form);

}
