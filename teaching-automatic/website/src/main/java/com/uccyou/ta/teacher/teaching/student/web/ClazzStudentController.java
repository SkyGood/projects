package com.uccyou.ta.teacher.teaching.student.web;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.teacher.teaching.admin.response.AdminClassResponse;
import com.uccyou.ta.teacher.teaching.student.service.ClazzStudentService;
import com.uccyou.ta.teacher.teaching.student.web.request.ImportClazzStudentForm;
import com.uccyou.ta.teacher.teaching.teaching.service.TeachingService;

@Controller
public class ClazzStudentController {

    private ClazzStudentService clazzStudentService;
    
    private TeachingService teachingService;

    @RequestMapping(value = "/teacher/clazz/import/{classId}", method = RequestMethod.GET)
    public String importStudent(@PathVariable("classId") Integer classId, Map<String, Object> map) {
        List<AdminClassResponse> adminClass = teachingService.findAdminClass(classId);
        map.put("classId", classId);
        map.put("adminClass", adminClass);
        return "website/teacher/teaching/clazzstudent/importstudent";
    }
    
    @RequestMapping(value = "/teacher/clazz/import", method = RequestMethod.POST)
    public String importStudent(ImportClazzStudentForm form, @RequestParam("excel") MultipartFile file, HttpServletRequest request, Map<String, Object> map) {
        Integer userId = (Integer) request.getSession().getAttribute(SessionConstants.USER_ID);
        form.setUserId(userId);
        int importCount = clazzStudentService.importStudent(form, file, request);
        List<AdminClassResponse> adminClass = teachingService.findAdminClass(form.getClassId());
        if (importCount != -1) {
            map.put("tip", "导入成功");
        } else {
            map.put("tip", "导入失败");
        }
        map.put("classId", form.getClassId());
        map.put("adminClass", adminClass);
        return "website/teacher/teaching/clazzstudent/importstudent";
    }
    
    @Inject
    public void setClazzStudentService(ClazzStudentService clazzStudentService) {
        this.clazzStudentService = clazzStudentService;
    }

    @Inject
    public void setTeachingService(TeachingService teachingService) {
        this.teachingService = teachingService;
    }
    
}
