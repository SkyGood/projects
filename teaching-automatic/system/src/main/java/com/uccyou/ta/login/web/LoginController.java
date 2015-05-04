package com.uccyou.ta.login.web;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.ta.login.service.LoginService;
import com.uccyou.ta.system.login.request.UserLoginRequest;
import com.uccyou.ta.system.login.response.LoginResponse;

@RestController
public class LoginController {

    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody UserLoginRequest request) {
        return loginService.login(request);
    }
    
    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
