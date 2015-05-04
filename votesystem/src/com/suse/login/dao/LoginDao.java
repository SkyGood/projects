package com.suse.login.dao;

import org.apache.commons.dbutils.QueryRunner;
import com.suse.utils.C3P0Utils;

public class LoginDao {

	public Boolean Login(String username, String password) {
		return ("jack".equals(username) && "123".equals(password));
	}
	
}
