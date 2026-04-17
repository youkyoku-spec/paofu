package com.gips.taskapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gips.taskapp.dao.Sample;
import com.gips.taskapp.mapper.SampleMapper;
import com.gips.taskapp.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {

	@Autowired
	private SampleMapper sampleMapper;

	public List<Sample> getSampleList() {
		List<Sample> sample = sampleMapper.getSampleList();
		return sample;
	}

}
