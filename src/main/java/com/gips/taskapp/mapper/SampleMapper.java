package com.gips.taskapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gips.taskapp.dao.Sample;

@Mapper
public interface SampleMapper {

	List<Sample> getSampleList();
}
