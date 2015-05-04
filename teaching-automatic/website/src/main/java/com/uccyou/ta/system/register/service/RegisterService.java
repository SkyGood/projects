package com.uccyou.ta.system.register.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.uccyou.ta.system.register.repository.RegisterRepository;
import com.uccyou.ta.system.register.web.request.CheckUserNameRequest;
import com.uccyou.ta.system.register.web.request.RegisterForm;

@Service
public class RegisterService {

    private RegisterRepository registerRepository;

    public int register(RegisterForm form) {
        return registerRepository.register(form);
    }
    
    public boolean checkUserName(CheckUserNameRequest request) {
        return registerRepository.checkUserName(request);
    }

    @Inject
    public void setRegisterRepository(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }
}
