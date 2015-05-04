package com.uccyou.ta.login.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.core.util.StopWatch;
import com.uccyou.ta.login.repository.LoginRepository;
import com.uccyou.ta.system.login.request.UserLoginRequest;
import com.uccyou.ta.system.login.response.LoginResponse;

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class LoginService {

    private static Logger logger = LoggerFactory.getLogger(LoginService.class);
    
    private LoginRepository loginRepository;

    public LoginResponse login(UserLoginRequest request) {
        StopWatch sw = new StopWatch();
        LoginResponse res =  loginRepository.login(request);
        logger.info("whole ws respository cost time = {}", new Object[]{sw.elapsedTime()});
        return res;
    }
    
    @Inject
    public void setLoginRepository(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
}
