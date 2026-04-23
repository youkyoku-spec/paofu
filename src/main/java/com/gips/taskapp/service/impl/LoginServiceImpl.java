package com.gips.taskapp.service.impl;

import org.springframework.stereotype.Service;

import com.gips.taskapp.dto.UserDto;
import com.gips.taskapp.mapper.LoginMapper;
import com.gips.taskapp.service.LoginService;

/**
 * ログイン認証サービス
 */
@Service
public class LoginServiceImpl implements LoginService {

	private final LoginMapper mapper;

	public LoginServiceImpl(LoginMapper mapper) {
		this.mapper = mapper;
	}

	//ログインIDとパスワードで認証を行う　結果をUserDtoで返す
	public UserDto loginService(String loginId, String password) {

		// UserDtoをインスタンス化する
		UserDto userDto = new UserDto();

		// 入力値がエラーかどうか判定する
		// エラーの場合メッセージをUserDtoにset
		if (loginId == null || loginId.trim().isEmpty() || password == null || password.trim().isEmpty()) {

			userDto.setMessage("ログインIDまたはパスワードが未入力です");
			//UserDtoを返す
			return userDto;
		}

		// DBから結果一覧のインスタンスを受け取る
		userDto = mapper.findUser(loginId, password);

		// 取得したインスタンスがnullだったならメッセージをUserDtoにset
		if (userDto == null) {
			//UserDtoにメッセージを追加するためにインスタンス化
			UserDto errorDto = new UserDto();
			//UserDtoにエラーメッセージを追加
			errorDto.setMessage("入力されたログインIDまたはパスワードが間違っています。");
			//errorDtoを返す
			return errorDto;

		}
		// UserDtoを返す
		return userDto;

	}

}
