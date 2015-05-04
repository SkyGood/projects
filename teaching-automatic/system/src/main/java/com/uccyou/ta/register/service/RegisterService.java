package com.uccyou.ta.register.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.ta.register.repository.RegisterRepository;
import com.uccyou.ta.system.register.request.UserRegisterRequest;

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class RegisterService {

    private RegisterRepository registerRepository;

    @Transactional
    public int register(UserRegisterRequest request) {
        return registerRepository.register(request);
    }
    
    public boolean checkUserName(String userName) {
        return registerRepository.checkUserName(userName);
    }
    
    @Inject
    public void setRegisterRepository(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

}
