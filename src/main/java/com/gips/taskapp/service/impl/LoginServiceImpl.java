package com.gips.taskapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gips.taskapp.dto.UserDto;
import com.gips.taskapp.mapper.LoginMapper;
import com.gips.taskapp.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	//iloveyou
	@Autowired
	private LoginMapper mapper;

	public UserDto loginService(String loginId, String password) {

		// UserDtoをインスタンス化する
		UserDto userDto = new UserDto();

		// 入力値がエラーかどうか判定する
		// エラーの場合メッセージをUserContextにset
		if (loginId == null || loginId.trim().isEmpty() || password == null || password.trim().isEmpty()) {

			userDto.setMessage("ログインIDまたはパスワードが未入力です");
			return userDto;
		}

		// DBから結果一覧のインスタンスを受け取る
		userDto = mapper.findUser(loginId, password);

		// 取得したインスタンスがnullだったならメッセージをUserContextにset
		if (userDto == null) {

			userDto.setMessage("入力されたログインIDまたはパスワードが間違っています。");
			return userDto;

		}
		// UserContextを返す
		return userDto;

	}

}
