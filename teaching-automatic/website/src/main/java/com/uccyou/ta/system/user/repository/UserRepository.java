package com.uccyou.ta.system.user.repository;

import org.springframework.stereotype.Repository;

import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.system.user.request.ResetPwdRequest;
import com.uccyou.ta.system.user.request.UserInfoUpdateRequest;
import com.uccyou.ta.system.user.response.UserInfoResponse;
import com.uccyou.ta.system.user.web.request.ResetPwdForm;
import com.uccyou.ta.system.user.web.request.UserInfoUpdateForm;

@Repository
public class UserRepository extends AbstractRepository {

    public UserInfoResponse center(int userId, int tableNum) {
        UserInfoResponse response =  uccyouClientApi.get(EndPointBuilder.create(UserInfoResponse.class).endpoint(EndPoint.SYSTEM).action("/user/center/%s/%s").appKey(appKey).arguments(userId, tableNum));
        response.setUserId(userId);
        return response;
    }

    public Boolean update(UserInfoUpdateForm form) {
        UserInfoUpdateRequest request = new UserInfoUpdateRequest();
        request.setUserId(form.getUserId());
        request.setEmail(form.getEmail());
        request.setPhone(form.getPhone());
        request.setQq(form.getQq());
        request.setTableNum(form.getTableNum());
        String bodyContent = JSONBinder.binder(UserInfoUpdateRequest.class).toJSON(request);
        return uccyouClientApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/user/change").appKey(appKey).body(bodyContent));
    }

    public Boolean rePwd(ResetPwdForm form) {
        if (!form.getNewPwd().equals(form.getReNewPwd())) {
            return false;
        }
        if (form.getOldPwd().equals(form.getNewPwd())) {
            return false;
        }
        ResetPwdRequest request = new ResetPwdRequest();
        request.setUserId(form.getUserId());
        request.setOldPwd(form.getOldPwd());
        request.setNewPwd(form.getNewPwd());
        String bodyContent = JSONBinder.binder(ResetPwdRequest.class).toJSON(request);
        return uccyouClientApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/user/repwd").appKey(appKey).body(bodyContent));
    }
    
}
