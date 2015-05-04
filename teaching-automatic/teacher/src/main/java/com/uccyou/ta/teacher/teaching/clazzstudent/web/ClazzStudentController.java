package com.uccyou.ta.teacher.teaching.clazzstudent.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.ta.teacher.teaching.clazzstudent.service.ClazzStudentService;
import com.uccyou.ta.teacher.teaching.request.ImportClazzStudentRequest;

@RestController
public class ClazzStudentController {

    private ClazzStudentService clazzStudentService;

    @RequestMapping(value = "/teacher/clazz/import", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int importStudent(@RequestBody ImportClazzStudentRequest request, HttpServletRequest req) {
        return clazzStudentService.importStudent(request, req);
    }
    
    @Inject
    public void setClazzStudentService(ClazzStudentService clazzStudentService) {
        this.clazzStudentService = clazzStudentService;
    }
}
