package com.uccyou.ta.system.user.service;

import javax.inject.Inject;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.uccyou.ta.system.user.repository.UserRepository;
import com.uccyou.ta.system.user.response.UserInfoResponse;
import com.uccyou.ta.system.user.web.request.ResetPwdForm;
import com.uccyou.ta.system.user.web.request.UserInfoUpdateForm;

@Service
public class UserService {

    private UserRepository userRepository;

    @Cacheable(value = "defaultCache", key = "#userId + 'userInfo'")
    public UserInfoResponse center(int userId, int tableNum) {
        return userRepository.center(userId, tableNum);
    }
    
    @CacheEvict(value = "defaultCache", key = "#form.userId + 'userInfo'")
    public Boolean update(UserInfoUpdateForm form) {
        return userRepository.update(form);
    }
    
    public Boolean rePwd(ResetPwdForm form) {
        return userRepository.rePwd(form);
    }
    
    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
}
