package com.uccyou.ta.admin.web;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.admin.service.AdminService;
import com.uccyou.ta.system.admin.request.AddUserRequest;
import com.uccyou.ta.system.admin.request.AdminStatusRequest;
import com.uccyou.ta.system.admin.request.ChangeUserRequest;
import com.uccyou.ta.system.admin.request.ImportDataRequest;
import com.uccyou.ta.system.admin.request.NewAdminRequest;
import com.uccyou.ta.system.admin.request.SearchAllUserRequest;
import com.uccyou.ta.system.admin.response.AdminInfoResponse;
import com.uccyou.ta.system.admin.response.ChangeUserResponse;
import com.uccyou.ta.system.admin.response.SearchAllUserResponse;
import com.uccyou.ta.system.admin.response.UserRemoveResponse;

@RestController
public class AdminController {

    private AdminService adminService;

    @Inject
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/importdata", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int importData(@RequestBody ImportDataRequest request,
            HttpServletRequest req) {
        return adminService.importData(request, req);
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer add(@RequestBody NewAdminRequest admin) {
        return adminService.add(admin);
    }

    @RequestMapping(value = "/admin/admin", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AdminInfoResponse> admin() {
        return adminService.admin();
    }

    @RequestMapping(value = "/admin/change", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean change(@RequestBody AdminStatusRequest request) {
        return adminService.change(request);
    }

    @RequestMapping(value = "/admin/remove/{adminId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean remove(@PathVariable("adminId") Integer adminId) {
        return adminService.remove(adminId);
    }

    @RequestMapping(value = "/admin/reset/{adminId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean reset(@PathVariable("adminId") Integer adminId) {
        return adminService.reset(adminId);
    }
    
    @RequestMapping(value = "/admin/user/search/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<SearchAllUserResponse> allUser(@RequestBody SearchAllUserRequest request, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return adminService.allUser(request, pageNo, pageSize);
    }
    
    @RequestMapping(value = "/admin/user/alive/{userId}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean userAlive(@PathVariable("userId") Integer userId, @PathVariable("status") String status) {
        return adminService.userAlive(userId, status);
    }
    
    @RequestMapping(value = "/admin/user/reset/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean userReset(@PathVariable("userId") Integer userId) {
        return adminService.userReset(userId);
    }
    
    @RequestMapping(value = "/admin/user/change/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ChangeUserResponse userChange(@PathVariable("userId") Integer userId) {
        return adminService.userChange(userId);
    }
    
    @RequestMapping(value = "/admin/user/change", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean userChange(@RequestBody ChangeUserRequest request) {
        return adminService.userChange(request);
    }
    
    @RequestMapping(value = "/admin/user/remove/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRemoveResponse userRemove(@PathVariable("userId") Integer userId) {
        return adminService.userRemove(userId);
    }
    
    @RequestMapping(value = "/admin/user/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer userAdd(@RequestBody AddUserRequest request) {
        return adminService.userAdd(request);
    }
    
}