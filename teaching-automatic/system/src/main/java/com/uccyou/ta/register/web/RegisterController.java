package com.uccyou.ta.register.web;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.ta.register.service.RegisterService;
import com.uccyou.ta.system.register.request.UserRegisterRequest;

@RestController
public class RegisterController {

    private RegisterService registerService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int register(@RequestBody UserRegisterRequest request) {
        return registerService.register(request);
    }
    
    @RequestMapping(value = "/check/{userName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean checkUserName(@PathVariable("userName") String userName) {
        return registerService.checkUserName(userName);
    }
    
    @Inject
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }
    
}
