package com.suse.login.service;

import com.suse.login.dao.LoginDao;

public class LoginService {

	public Boolean login(String username, String password) {
		return new LoginDao().Login(username, password);
	}
}
