package com.uccyou.ta.system.register.web;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.ta.system.register.service.RegisterService;
import com.uccyou.ta.system.register.web.request.CheckUserNameRequest;

@RestController
public class RegisterAjaxController {

    private RegisterService registerService;

    //true for able, false for unable
    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean checkUserName(@RequestBody CheckUserNameRequest request) {
        return registerService.checkUserName(request);
    }
    
    @Inject
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }
}
