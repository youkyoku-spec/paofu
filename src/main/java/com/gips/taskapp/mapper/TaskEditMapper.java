package com.gips.taskapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gips.taskapp.dto.TaskEditDto;
import com.gips.taskapp.dto.UserDto;

@Mapper
public interface TaskEditMapper {

	TaskEditDto findTaskById(@Param("taskId") Integer taskId);

	List<UserDto> findAllUsers();

	void insertTask(TaskEditDto dto);

	void updateTaskAsLeader(TaskEditDto dto);

	void updateTaskAsMember(TaskEditDto dto);

}
