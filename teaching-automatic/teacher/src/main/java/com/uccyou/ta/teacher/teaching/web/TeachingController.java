package com.uccyou.ta.teacher.teaching.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.teaching.admin.response.AdminClassResponse;
import com.uccyou.ta.teacher.teaching.request.CreateTeachingClassRequest;
import com.uccyou.ta.teacher.teaching.request.TeachingClassSearchRequest;
import com.uccyou.ta.teacher.teaching.request.UpdateTeachingClassRequest;
import com.uccyou.ta.teacher.teaching.response.TeachingClassResponse;
import com.uccyou.ta.teacher.teaching.service.TeachingService;

@RestController
public class TeachingController {

    private TeachingService teachingService;

    @RequestMapping(value = "/teacher/clazz/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<TeachingClassResponse> clazz(@RequestBody TeachingClassSearchRequest request, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return teachingService.clazz(request, pageNo, pageSize);
    }
    
    @RequestMapping(value = "/teacher/clazz/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int create(@RequestBody CreateTeachingClassRequest request) {
        return teachingService.create(request);
    }
    
    @RequestMapping(value = "/teacher/clazz/change/{classId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeachingClassResponse update(@PathVariable("classId") Integer classId) {
        return teachingService.findClassById(classId);
    }
    
    @RequestMapping(value = "/teacher/clazz/change", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean update(@RequestBody UpdateTeachingClassRequest request) {
        return teachingService.update(request);
    }
    
    @RequestMapping(value = "/teacher/clazz/manage/{classId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
    public List<AdminClassResponse> manage(@PathVariable("classId") Integer classId) {
        return teachingService.manage(classId);
    }
    
    @RequestMapping(value = "/teacher/clazz/remove/{classId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean remove(@PathVariable("classId") Integer classId) {
        return teachingService.remove(classId);
    }
    
    @Inject
    public void setTeachingService(TeachingService teachingService) {
        this.teachingService = teachingService;
    }
}
