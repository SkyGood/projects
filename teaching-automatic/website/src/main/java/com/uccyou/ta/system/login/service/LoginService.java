package com.uccyou.ta.system.login.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.uccyou.ta.system.login.repository.LoginRepository;
import com.uccyou.ta.system.login.response.LoginResponse;
import com.uccyou.ta.system.login.web.request.UserLoginForm;

@Service
public class LoginService {

    private LoginRepository loginRepository;

    public LoginResponse login(UserLoginForm form) {
        return loginRepository.login(form);
    }

    @Inject
    public void setLoginRepository(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
}
