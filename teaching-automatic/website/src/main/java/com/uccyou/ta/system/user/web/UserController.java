package com.uccyou.ta.system.user.web;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.system.user.response.UserInfoResponse;
import com.uccyou.ta.system.user.service.UserService;
import com.uccyou.ta.system.user.web.request.ResetPwdForm;
import com.uccyou.ta.system.user.web.request.UserInfoUpdateForm;

@Controller
public class UserController {

    private UserService userService;

    @RequestMapping(value = "/user/center", method = RequestMethod.GET)
    public String center(HttpSession session, Map<String, Object> map) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        Integer tableNum = (Integer) session.getAttribute(SessionConstants.TABLE_NUM);
        String identityCode = (String) session.getAttribute(SessionConstants.IDENTITY_CODE);
        String identity = (String) session.getAttribute(SessionConstants.IDENTITY);
        String name = (String) session.getAttribute(SessionConstants.USER_NAME);
        UserInfoResponse response = userService.center(userId, tableNum);
        response.setUserId(userId);
        response.setIdentity(identity);
        response.setIdentityCode(identityCode);
        response.setName(name);
        map.put("user", response);
        return "website/system/usercenter";
    }
    
    @RequestMapping(value = "/user/change", method = RequestMethod.POST)
    public String update(UserInfoUpdateForm form, HttpSession session, Map<String, Object> map) {
        Integer tableNum = (Integer) session.getAttribute(SessionConstants.TABLE_NUM);
        form.setTableNum(tableNum);
        Boolean flag = userService.update(form);
        if (!flag) {
            String tip = "修改失败！";
            map.put("tip", tip);
        }
        return "redirect:/user/center";
    }
    
    @RequestMapping(value = "/user/repwd", method = RequestMethod.POST)
    @ResponseBody
    public Boolean rePwd(ResetPwdForm form) {
        return userService.rePwd(form);
    }
    
    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
}
