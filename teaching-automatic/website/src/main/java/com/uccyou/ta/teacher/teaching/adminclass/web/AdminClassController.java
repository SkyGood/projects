package com.uccyou.ta.teacher.teaching.adminclass.web;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.uccyou.ta.teacher.teaching.adminclass.service.AdminClassService;
import com.uccyou.ta.teacher.teaching.adminclass.web.request.AddAdminClassForm;
import com.uccyou.ta.teacher.teaching.adminclass.web.request.ChangeAdminClassNameForm;

@Controller
public class AdminClassController {
    
    private AdminClassService adminClassService;
    @Inject
    public void setAdminClassService(AdminClassService adminClassService) {
        this.adminClassService = adminClassService;
    }
    
    @RequestMapping(value = "/teacher/clazz/admin/add", method = RequestMethod.POST)
    @ResponseBody
    public Integer add(AddAdminClassForm form) {
        return adminClassService.add(form);
    } 
    
    @RequestMapping(value = "/teacher/clazz/admin/change", method = RequestMethod.POST)
    @ResponseBody
    public Boolean change(ChangeAdminClassNameForm form) {
        return adminClassService.change(form);
    }
    
    @RequestMapping(value = "/teacher/clazz/admin/remove/{adminClassId}/{teachingclassId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean remove(@PathVariable("adminClassId") Integer adminClassId, @PathVariable("teachingclassId") Integer teachingclassId) {
        return adminClassService.remove(adminClassId, teachingclassId);
    }
    
    @RequestMapping(value = "/teacher/clazz/admin/clean/{adminClassId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean clean(@PathVariable("adminClassId") Integer adminClassId) {
        return adminClassService.clean(adminClassId);
    }
}