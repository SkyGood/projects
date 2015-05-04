package com.uccyou.ta.system.login.web;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.system.login.response.LoginResponse;
import com.uccyou.ta.system.login.service.LoginService;
import com.uccyou.ta.system.login.web.request.UserLoginForm;

@Controller
public class LoginController {
    
    private LoginService loginService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String intro() {
        return "intro";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "website/system/login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
        
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public boolean login(UserLoginForm form, HttpSession session, Map<String, Object> map) {
        LoginResponse response = loginService.login(form);
        if (response.getName() == null && response.getAdminId() == null) {
            return false;
            //return "website/system/login";
        } else {
            //find all system notices
            if ("A".equals(form.getIdentity())) {
                session.setAttribute(SessionConstants.ONLINE, true);
                session.setAttribute(SessionConstants.ADMIN_ID, response.getAdminId());
                session.setAttribute(SessionConstants.ADMIN_NAME, form.getIdentityCode());
                session.setAttribute(SessionConstants.IDENTITY, form.getIdentity());
                session.setAttribute(SessionConstants.ADMIN_IMPORT, response.getImportData());
                //return "redirect:/admin/admin";
            } else {
                session.setAttribute(SessionConstants.ONLINE, true);
                session.setAttribute(SessionConstants.USER_ID, response.getUserId());
                session.setAttribute(SessionConstants.USER_NAME, response.getName());
                session.setAttribute(SessionConstants.IDENTITY, form.getIdentity());
                session.setAttribute(SessionConstants.IDENTITY_CODE, form.getIdentityCode());
                session.setAttribute(SessionConstants.TABLE_NUM, response.getTableNum());
                //return "redirect:/system/homepage";
            }
            return true;
        }
    }
    
    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    
}
