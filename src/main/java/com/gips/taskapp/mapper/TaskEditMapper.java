package com.gips.taskapp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gips.taskapp.dto.TaskEditDto;

@Mapper
public interface TaskEditMapper {

	TaskEditDto findTaskById(@Param("taskId") Integer taskId);

	void insertTask(TaskEditDto dto);

	void updateTaskAsLeader(TaskEditDto dto);

	void updateTaskAsMember(TaskEditDto dto);

}
