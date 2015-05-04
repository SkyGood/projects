package com.tianwen.commons.intranet.user.web;

/*
 * 接口状态：全部测试通过
 * 时间：2014年9月28日 22:29:49
 */

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tianwen.commons.intranet.user.request.UserSearchRequest;
import com.tianwen.commons.intranet.user.response.UserResponse;
import com.tianwen.commons.intranet.user.service.IntranetUserService;
import com.uccyou.core.page.PageModel;

@RestController
public class IntranetUserController {

    private IntranetUserService userService;

  /*  //条件分页搜索用户
    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<UserResponse> users(@RequestBody UserSearchRequest search, HttpServletRequest request) {
        request.getSession().setAttribute("userSearch", search);
        return userService.users(search, 1, 10);
    }*/
    
    //分页标签搜索用户
    @RequestMapping(value = "/users/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<UserResponse> users(@RequestBody UserSearchRequest search, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
//        UserSearchRequest search = (UserSearchRequest) request.getSession().getAttribute("userSearch");
        return userService.users(search, pageNo, pageSize);
    }
    
    //用户列表
    @RequestMapping(value = "/users/{pageNo}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<UserResponse> users(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return userService.users(null, pageNo, pageSize);
    }
    
    //冻结或解冻用户
    @RequestMapping(value = "/alive/{userId}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean alive(@PathVariable("userId") Integer userId, @PathVariable("status") String status) {
    	return userService.alive(userId, status);
    }
    
    //查询已经注册的用户数量
    @RequestMapping(value = "/usernumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer userNumber() {
    	return userService.userNumber();
    }
    
    
    @Inject
    public void setUserService(IntranetUserService userService) {
        this.userService = userService;
    }
}
