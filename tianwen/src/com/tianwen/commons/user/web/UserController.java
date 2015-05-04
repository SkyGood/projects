package com.tianwen.commons.user.web;

/*
 *接口状态：所有测试通过
 *时间：2014年9月28日 21:31:57 
 */

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tianwen.commons.user.request.PasswordModifyRequest;
import com.tianwen.commons.user.request.UserLoginRequest;
import com.tianwen.commons.user.request.UserModifyRequest;
import com.tianwen.commons.user.request.UserRegisterRequest;
import com.tianwen.commons.user.response.UserInfoResponse;
import com.tianwen.commons.user.service.UserService;

@RestController
public class UserController {

	private UserService userService;

	/* injections */
	@Inject
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// check whether username repeated
	@RequestMapping(value = "/checkuser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean checkUserName(@RequestBody String userName) {
		return userService.checkUserName(userName);
	}

	// user register; if register fail return -1
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserInfoResponse register(@RequestBody UserRegisterRequest request) {
		return userService.register(request);
	}

	// user login; if login fail return null
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserInfoResponse login(@RequestBody UserLoginRequest request) {
		return userService.login(request);
	}
	
	//check whether student code is ok, if return false: code is already exists or already register
	@RequestMapping(value = "/checkcode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean checkStudentCode(@RequestBody String studentCode) {
		return userService.checkStudentCode(studentCode);
	}
	
	//update user basic message
	@RequestMapping(value = "/updateuser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserInfoResponse updateUser(@RequestBody UserModifyRequest userModifyRequest) {
		return userService.updateUserMessage(userModifyRequest);
	}
	
	//update user password
	@RequestMapping(value = "/updatepassword", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean updatePassword(@RequestBody PasswordModifyRequest passwordModifyRequest) {
		return userService.updatePassword(passwordModifyRequest);
	}
	
}
