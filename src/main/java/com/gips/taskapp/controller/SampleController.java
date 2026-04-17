package com.gips.taskapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gips.taskapp.dao.Sample;
import com.gips.taskapp.service.SampleService;

@Controller
public class SampleController {

	@Autowired
	private SampleService sampleService;

	/**
	 * 初期表示
	 * mybatisのテストcontroller
	 *
	 * @param model	モデル
	 * @return 呼出すビュー
	 */
	@GetMapping("/Sample")
	String init(Model model) {

		List<Sample> sample = sampleService.getSampleList();
		model.addAttribute("sample", sample);

		return "task/sample";
	}
}
