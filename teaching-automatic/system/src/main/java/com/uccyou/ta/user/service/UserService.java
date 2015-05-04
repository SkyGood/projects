package com.uccyou.ta.user.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.ta.system.user.request.ResetPwdRequest;
import com.uccyou.ta.system.user.request.UserInfoUpdateRequest;
import com.uccyou.ta.system.user.response.UserInfoResponse;
import com.uccyou.ta.user.repository.UserRepository;

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class UserService {

    private UserRepository userRepository;

    public UserInfoResponse center(Integer userId, Integer tableNum) {
        return userRepository.center(userId, tableNum);
    }
    
    @Transactional
    public boolean update(UserInfoUpdateRequest request) {
        return userRepository.update(request);
    }
    
    @Transactional
    public Boolean rePwd(ResetPwdRequest request) {
        return userRepository.rePwd(request);
    }
    
    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
