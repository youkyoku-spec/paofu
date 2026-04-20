package com.gips.taskapp.service.impl;

import org.springframework.ui.Model;

import com.gips.taskapp.dto.UserDto;

public interface LoginServiceImpl {

	public String login(Model model);

	public String loginForm(Model model, Form form);

	public UserDto loginService(String loginId, String password);
}
