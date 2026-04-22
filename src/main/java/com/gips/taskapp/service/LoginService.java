package com.gips.taskapp.service;

import com.gips.taskapp.dto.UserDto;

public interface LoginService {

	public UserDto loginService(String loginId, String password);

}
