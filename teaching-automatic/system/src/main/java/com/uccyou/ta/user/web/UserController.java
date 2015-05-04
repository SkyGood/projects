package com.uccyou.ta.user.web;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.ta.system.user.request.ResetPwdRequest;
import com.uccyou.ta.system.user.request.UserInfoUpdateRequest;
import com.uccyou.ta.system.user.response.UserInfoResponse;
import com.uccyou.ta.user.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    @RequestMapping(value = "/user/center/{userId}/{tableNum}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfoResponse center(@PathVariable("userId") Integer userId, @PathVariable("tableNum") Integer tableNum) {
        return userService.center(userId, tableNum);
    }
    
    @RequestMapping(value = "/user/change", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean update(@RequestBody UserInfoUpdateRequest request) {
        return userService.update(request);
    }
    
    @RequestMapping(value = "/user/repwd", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean rePwd(@RequestBody ResetPwdRequest request) {
        return userService.rePwd(request);
    }
    
    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
 