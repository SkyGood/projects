package com.uccyou.ta.system.login.repository;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.uccyou.core.crypto.MD5;
import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.core.util.StopWatch;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.system.login.request.UserLoginRequest;
import com.uccyou.ta.system.login.response.LoginResponse;
import com.uccyou.ta.system.login.web.request.UserLoginForm;

@Repository
public class LoginRepository extends AbstractRepository {

    private static Logger logger = LoggerFactory.getLogger(LoginRepository.class);
    
    private MD5 md5;
    
    public LoginResponse login(UserLoginForm form) {
        StopWatch sw = new StopWatch();
        UserLoginRequest request = new UserLoginRequest();
        request.setIdentityCode(form.getIdentityCode());
        request.setPassWord(md5.encrypt(form.getPassWord()));
        request.setIdentity(form.getIdentity());
        String bodyContent = JSONBinder.binder(UserLoginRequest.class).toJSON(request);
        logger.info("transfer data cost time = {}", new Object[]{sw.elapsedTime()});
        return uccyouClientApi.post(EndPointBuilder.create(LoginResponse.class).endpoint(EndPoint.SYSTEM).action("/login").appKey(appKey).body(bodyContent));
    }

    @Inject
    public void setMd5(MD5 md5) {
        this.md5 = md5;
    }
}
