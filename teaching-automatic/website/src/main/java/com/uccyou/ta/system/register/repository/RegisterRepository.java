package com.uccyou.ta.system.register.repository;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.uccyou.core.crypto.MD5;
import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.system.register.request.UserRegisterRequest;
import com.uccyou.ta.system.register.web.request.CheckUserNameRequest;
import com.uccyou.ta.system.register.web.request.RegisterForm;

@Repository
public class RegisterRepository extends AbstractRepository {

    private MD5 md5;

    public int register(RegisterForm form) {
        UserRegisterRequest request = new UserRegisterRequest();
        request.setUserName(form.getUserName());
        request.setIdentityCode(form.getIdentityCode());
        request.setPassWord(md5.encrypt(form.getPassWord()));
        request.setIdentity(form.getIdentity());
        String bodyContent = JSONBinder.binder(UserRegisterRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(Integer.class).endpoint(EndPoint.SYSTEM).appKey(appKey).action("/register").body(bodyContent));
    }
    
    public boolean checkUserName(CheckUserNameRequest request) {
        return uccyouClientApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/check/%s").appKey(appKey).arguments(request.getUserName()));
    }

    @Inject
    public void setMd5(MD5 md5) {
        this.md5 = md5;
    }
}
