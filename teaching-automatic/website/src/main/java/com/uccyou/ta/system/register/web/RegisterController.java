package com.uccyou.ta.system.register.web;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.system.register.service.RegisterService;
import com.uccyou.ta.system.register.web.request.RegisterForm;

@Controller
public class RegisterController {

    private RegisterService registerService;
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "website/system/register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(RegisterForm form, Map<String, Object> map, HttpSession session) {
        int userId = registerService.register(form);
        map.put("userId", userId);
        if (userId != 0) {
            session.setAttribute(SessionConstants.ONLINE, true);
            session.setAttribute(SessionConstants.USER_ID, userId);
            session.setAttribute(SessionConstants.USER_NAME, form.getUserName());
            session.setAttribute(SessionConstants.IDENTITY, form.getIdentity());
            session.setAttribute(SessionConstants.IDENTITY_CODE, form.getIdentityCode());
            return "redirect:/system/homepage";
        } else {
            map.put("form", form);
            map.put("tip", "注册失败");
            return "website/system/register";
        }
    }

    @Inject
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }
}
