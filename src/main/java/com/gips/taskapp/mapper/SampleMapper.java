package com.gips.taskapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gips.taskapp.dto.SampleDto;

@Mapper
public interface SampleMapper {

	List<SampleDto> getSampleList();
}
