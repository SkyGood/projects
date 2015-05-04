package com.tianwen.commons.user.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianwen.commons.user.repository.UserRepository;
import com.tianwen.commons.user.request.PasswordModifyRequest;
import com.tianwen.commons.user.request.UserLoginRequest;
import com.tianwen.commons.user.request.UserModifyRequest;
import com.tianwen.commons.user.request.UserRegisterRequest;
import com.tianwen.commons.user.response.UserInfoResponse;

@Service
@Transactional(readOnly = true)
public class UserService {

    private UserRepository userRepository;

    
    /* injections */
    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


	public boolean checkUserName(String userName) {
		return userRepository.checkUserName(userName);
	}

	@Transactional
	public UserInfoResponse register(UserRegisterRequest request) {
		// TODO Auto-generated method stub
		return userRepository.register(request);
	}


	public UserInfoResponse login(UserLoginRequest request) {
		// TODO Auto-generated method stub
		return userRepository.login(request);
	}


	public boolean checkStudentCode(String studentCode) {
		return userRepository.checkStudentCode(studentCode);
	}


	@Transactional
	public UserInfoResponse updateUserMessage(
			UserModifyRequest userModifyRequest) {
		return userRepository.updateUserMessage(userModifyRequest);
	}


	@Transactional
	public boolean updatePassword(PasswordModifyRequest passwordModifyRequest) {
		return userRepository.updatePassword(passwordModifyRequest);
	}
}
