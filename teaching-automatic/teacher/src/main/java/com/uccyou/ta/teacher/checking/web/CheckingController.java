package com.uccyou.ta.teacher.checking.web;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.checking.request.CheckingAdminRequest;
import com.uccyou.ta.teacher.checking.request.CheckingRandomRequest;
import com.uccyou.ta.teacher.checking.response.AdminClassNameResponse;
import com.uccyou.ta.teacher.checking.response.ClassStudentResponse;
import com.uccyou.ta.teacher.checking.service.CheckingService;

@RestController
public class CheckingController {

    private CheckingService checkingService;

    @RequestMapping(value = "/teacher/checking/all/{classId}/{pageNo}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<ClassStudentResponse> checkingAll(@PathVariable("classId") Integer classId, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return checkingService.checkingAll(classId, pageNo, pageSize);
    }
    
    @RequestMapping(value = "/teacher/checking/admin/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<ClassStudentResponse> checkingAdmin(@RequestBody CheckingAdminRequest request, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return checkingService.checkingAdmin(request, pageNo, pageSize);
    }
    
    @RequestMapping(value = "/teacher/checking/random")
    public PageModel<ClassStudentResponse> checkingRandom(@RequestBody CheckingRandomRequest request) {
        return checkingService.checkingRandom(request);
    }
    
    @RequestMapping(value = "/teacher/checking/random/{classId}/{pageNo}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<ClassStudentResponse> checkingRandom(@PathVariable("classId") Integer classId, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return checkingService.randomPaging(classId, pageNo, pageSize);
    }
    
    @RequestMapping(value = "/teacher/checking/close/{classId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean checkingClose(@PathVariable("classId") Integer classId) {
        return checkingService.checkingClose(classId);
    }
    
    @RequestMapping(value = "/teacher/checking/absent/{classId}/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean absent(@PathVariable("classId") Integer classId, @PathVariable("studentId") Integer studentId) {
        return checkingService.absent(classId, studentId);
    }
    
    @RequestMapping(value = "/teacher/checking/note/{classId}/{studentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean note(@PathVariable("classId") Integer classId, @PathVariable("studentId") Integer studentId) {
        return checkingService.note(classId, studentId);
    }
    
    @RequestMapping(value = "/teacher/calss/admin/findadminclassnamebyid/{adminClassId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AdminClassNameResponse findAdminClassNameById(@PathVariable("adminClassId") Integer adminClassId) {
        return checkingService.findAdminClassNameById(adminClassId);
    }
    
    @Inject
    public void setCheckingService(CheckingService checkingService) {
        this.checkingService = checkingService;
    }
}
