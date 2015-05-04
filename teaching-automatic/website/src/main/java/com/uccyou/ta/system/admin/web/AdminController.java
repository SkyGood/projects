package com.uccyou.ta.system.admin.web;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.system.admin.response.SearchAllUserResponse;
import com.uccyou.ta.system.admin.service.AdminService;
import com.uccyou.ta.system.admin.web.request.AddUserForm;
import com.uccyou.ta.system.admin.web.request.ChangeUserForm;
import com.uccyou.ta.system.admin.web.request.ImportDataForm;
import com.uccyou.ta.system.admin.web.request.NewAdminForm;
import com.uccyou.ta.system.admin.web.request.SearchAllUserForm;
import com.uccyou.ta.system.admin.web.response.AdminInfoForm;

@Controller
public class AdminController {

    private AdminService adminService;

    @Inject
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/admin/import", method = RequestMethod.GET)
    public String importData() {
        return "website/system/import";
    }

    @RequestMapping(value = "/admin/import", method = RequestMethod.POST)
    public String importData(ImportDataForm form, @RequestParam("excel") MultipartFile file, HttpServletRequest req, Map<String, Object> map) {
        Integer adminId = (Integer) req.getSession().getAttribute(SessionConstants.ADMIN_ID);
        form.setAdminId(adminId);
        int importCount = adminService.importData(form, file, req);
        if (importCount != -1) {
            map.put("importCount", importCount);
        } else {
            map.put("tip", "数据导入失败");
        }
        return "website/system/import";
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String login() {
        return "website/system/adminlogin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
    public String add() {
        return "website/system/addadmin";
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    @ResponseBody
    public Integer add(NewAdminForm admin) {
        return adminService.add(admin);
    }

    @RequestMapping(value = "/admin/admin", method = RequestMethod.GET)
    public String admin(HttpSession session, Map<String, Object> map) {
        List<AdminInfoForm> adminList = adminService.admin();
        String identity = (String) session.getAttribute(SessionConstants.IDENTITY);
        map.put("admins", adminList);
        map.put("identity", identity);
        return "website/system/alladmin";
    }

    @RequestMapping(value = "/admin/alive/{adminId}/{status}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean change(@PathVariable("adminId") Integer adminId, @PathVariable("status") String status) {
        return adminService.change(adminId, status);
    }

    @RequestMapping(value = "/admin/remove/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean remove(@PathVariable("adminId") Integer adminId) {
        return adminService.remove(adminId);
    }

    @RequestMapping(value = "/admin/reset/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean reset(@PathVariable("adminId") Integer adminId) {
        return adminService.reset(adminId);
    }
    
    @RequestMapping(value = "/admin/user/search", method = RequestMethod.GET)
    public String allUser(HttpSession session, Map<String, Object> map) {
        SearchAllUserForm form = new SearchAllUserForm();
        session.setAttribute(SessionConstants.ALL_USER, form);
        PageModel<SearchAllUserResponse> pageModel = adminService.allUser(form, 1, 10);
        map.put("pageModel", pageModel);
        return "website/system/alluser";
    }
    
    @RequestMapping(value = "/admin/user/search", method = RequestMethod.POST)
    public String allUser(SearchAllUserForm form, HttpSession session, Map<String, Object> map) {
        session.setAttribute(SessionConstants.ALL_USER, form);
        PageModel<SearchAllUserResponse> pageModel = adminService.allUser(form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("user", form);
        return "website/system/alluser";
    }
    
    @RequestMapping(value = "/admin/user/search/{pageNo}/{pageSize}", method = RequestMethod.GET) 
    public String allUser(HttpSession session, Map<String, Object> map, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        SearchAllUserForm form = (SearchAllUserForm) session.getAttribute(SessionConstants.ALL_USER);
        PageModel<SearchAllUserResponse> pageModel = adminService.allUser(form, pageNo, pageSize);
        map.put("pageModel", pageModel);
        map.put("user", form);
        return "website/system/alluser";
    }
    
    @RequestMapping(value = "/admin/user/alive/{userId}/{status}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean userAlive(@PathVariable("userId") Integer userId, @PathVariable("status") String status) {
        return adminService.userAlive(userId, status);
    }
    
    @RequestMapping(value = "/admin/user/reset/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean userReset(@PathVariable("userId") Integer userId) {
        return adminService.userReset(userId);
    }
    
    @RequestMapping(value = "/admin/user/change", method = RequestMethod.POST)
    public boolean userChange(ChangeUserForm form, Map<String, Object> map) {
        return adminService.userChange(form);
    }
    
    @RequestMapping(value = "/admin/user/remove/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean userRemove(HttpServletRequest request, @PathVariable("userId") Integer userId) {
        return adminService.userRemove(request, userId);
    }
    
    @RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
    @ResponseBody
    public Integer userAdd(AddUserForm form) {
        return adminService.userAdd(form);
    }
    
    
}
