package com.uccyou.ta.teacher.teaching.adminclass.web;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.ta.teacher.teaching.admin.request.AddAdminClassRequest;
import com.uccyou.ta.teacher.teaching.admin.request.ChangeAdminClassNameRequest;
import com.uccyou.ta.teacher.teaching.adminclass.service.AdminClassService;

@RestController
public class AdminClassController {

    private AdminClassService adminClassService;

    @Inject
    public void setAdminClassService(AdminClassService adminClassService) {
        this.adminClassService = adminClassService;
    }

    @RequestMapping(value = "/teacher/clazz/admin/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer add(@RequestBody AddAdminClassRequest request) {
        return adminClassService.add(request);
    }
    
    @RequestMapping(value = "/teacher/clazz/admin/change", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean change(@RequestBody ChangeAdminClassNameRequest request) {
        return adminClassService.change(request);
    } 
    
    @RequestMapping(value = "/teacher/clazz/admin/remove/{adminClassId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean remove(@PathVariable("adminClassId") Integer adminClassId) {
        return adminClassService.remove(adminClassId);
    }
    
    @RequestMapping(value = "/teacher/clazz/admin/clean/{adminClassId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean clean(@PathVariable("adminClassId") Integer adminClassId) {
        return adminClassService.clean(adminClassId);
    }
}
