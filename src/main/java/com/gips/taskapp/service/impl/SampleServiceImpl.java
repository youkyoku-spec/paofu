package com.gips.taskapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gips.taskapp.dto.SampleDto;
import com.gips.taskapp.mapper.SampleMapper;
import com.gips.taskapp.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleMapper sampleMapper;

	public List<SampleDto> getSampleList() {
		List<SampleDto> sampleDtoList = sampleMapper.getSampleList();
		return sampleDtoList;
	}

}
