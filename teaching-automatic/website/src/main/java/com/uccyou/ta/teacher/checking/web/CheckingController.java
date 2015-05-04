package com.uccyou.ta.teacher.checking.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.teacher.checking.response.ClassStudentResponse;
import com.uccyou.ta.teacher.checking.service.CheckingService;
import com.uccyou.ta.teacher.checking.web.request.CheckingRandomForm;
import com.uccyou.ta.teacher.teaching.admin.response.AdminClassResponse;
import com.uccyou.ta.teacher.teaching.adminclass.service.AdminClassService;
import com.uccyou.ta.teacher.teaching.teaching.service.TeachingService;

@Controller
public class CheckingController {

    private CheckingService checkingService;
    
    private TeachingService teachingService;
    
    private AdminClassService adminClassService;

    @RequestMapping(value = "/teacher/checking/{classId}", method = RequestMethod.GET)
    public String checking(@PathVariable("classId") Integer classId, Map<String, Object> map) {
        List<AdminClassResponse> adminClass = teachingService.findAdminClass(classId);
        map.put("adminClass", adminClass);
        map.put("classId", classId);
        return "website/teacher/checking/checking";
    }
    
    @RequestMapping(value = "/teacher/checking/all/{classId}", method = RequestMethod.GET)
    public String checkingAll(@PathVariable("classId") Integer classId, HttpSession session, Map<String, Object> map) {
        session.setAttribute(SessionConstants.TEACHING_CLASS_ID, classId);
        PageModel<ClassStudentResponse> pageModel = checkingService.findAllStudentsByClassId(classId, 1, 10);
        map.put("pageModel", pageModel);
        map.put("classId", classId);
        map.put("type", "a");
        return "website/teacher/checking/checkinglist";
    }
    
    @RequestMapping(value = "/teacher/checking/all/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String checkingAll(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, HttpSession session, Map<String, Object> map) {
        Integer classId = (Integer) session.getAttribute(SessionConstants.TEACHING_CLASS_ID);
        PageModel<ClassStudentResponse> pageModel = checkingService.findAllStudentsByClassId(classId, pageNo, pageSize);
        map.put("pageModel", pageModel);
        map.put("classId", classId);
        map.put("type", "a");
        return "website/teacher/checking/checkinglist";
    }
    
    @RequestMapping(value = "/teacher/checking/admin/{adminClassId}/{classId}", method = RequestMethod.GET)
    public String checkingAdmin(@PathVariable("adminClassId") Integer adminClassId, @PathVariable("classId") Integer classId, HttpSession session, Map<String, Object> map) {
        session.setAttribute(SessionConstants.ADMIN_CLASS_ID, adminClassId);
        session.setAttribute(SessionConstants.TEACHING_CLASS_ID, classId);
        PageModel<ClassStudentResponse> pageModel = checkingService.checkingAdmin(adminClassId, classId, 1, 10);
        String adminClassName = adminClassService.findAdminClassNameById(adminClassId).getAdminClassName();
        map.put("pageModel", pageModel);
        map.put("adminClassName", adminClassName);
        map.put("classId", classId);
        map.put("type", "m");
        return "website/teacher/checking/checkinglist";
    }
    
    @RequestMapping(value = "/teacher/checking/admin/p/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String checkingAdmin(HttpSession session, Map<String, Object> map, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        Integer adminClassId = (Integer) session.getAttribute(SessionConstants.ADMIN_CLASS_ID);
        Integer classId = (Integer) session.getAttribute(SessionConstants.TEACHING_CLASS_ID);
        PageModel<ClassStudentResponse> pageModel = checkingService.checkingAdmin(adminClassId, classId, pageNo, pageSize);
        String adminClassName = adminClassService.findAdminClassNameById(adminClassId).getAdminClassName();
        map.put("pageModel", pageModel);
        map.put("adminClassName", adminClassName);
        map.put("classId", classId);
        map.put("type", "m");
        return "website/teacher/checking/checkinglist";
    }
    
    @RequestMapping(value = "/teacher/checking/random/{classId}", method = RequestMethod.GET)
    public String checkingRandom(@PathVariable("classId") Integer classId, Map<String, Object> map) {
        map.put("classId", classId);
        return "website/teacher/checking/checkingrandom";
    }
    
    @RequestMapping(value = "/teacher/checking/random", method = RequestMethod.POST)
    public String checkingRandom(CheckingRandomForm form, HttpSession session, Map<String, Object> map) {
        session.setAttribute(SessionConstants.TEACHING_CLASS_ID, form.getClassId());
        PageModel<ClassStudentResponse> pageModel = checkingService.checkingRandom(form);
        map.put("pageModel", pageModel);
        map.put("classId", form.getClassId());
        map.put("type", "r");
        return "website/teacher/checking/checkinglist";
    }
    
    @RequestMapping(value = "/teacher/checking/random/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String checkingRandom(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, HttpSession session, Map<String, Object> map) {
        Integer classId = (Integer) session.getAttribute(SessionConstants.TEACHING_CLASS_ID);
        PageModel<ClassStudentResponse> pageModel = checkingService.randomPaging(classId, pageNo, pageSize);
        map.put("pageModel", pageModel);
        map.put("classId", classId);
        map.put("type", "r");
        return "website/teacher/checking/checkinglist";
    }
    
    @RequestMapping(value = "/teacher/checking/close", method = RequestMethod.GET)
    public String close(HttpSession session) {
        Integer classId = (Integer) session.getAttribute(SessionConstants.TEACHING_CLASS_ID);
        boolean flag = checkingService.checkingClose(classId);
        return "redirect:/teacher/clazz/manage/" + classId;
    }
    
    @RequestMapping(value = "/teacher/checking/absent/{classId}/{studentId}", method = RequestMethod.GET)
    @ResponseBody
    public boolean absent(@PathVariable("classId") Integer classId, @PathVariable("studentId") Integer studentId) {
        return checkingService.absent(classId, studentId);
    }
    
    @RequestMapping(value = "/teacher/checking/note/{classId}/{studentId}", method = RequestMethod.GET)
    @ResponseBody
    public boolean note(@PathVariable("classId") Integer classId, @PathVariable("studentId") Integer studentId) {
        return checkingService.note(classId, studentId);
    }
    
    @Inject
    public void setCheckingService(CheckingService checkingService) {
        this.checkingService = checkingService;
    }

    @Inject
    public void setTeachingService(TeachingService teachingService) {
        this.teachingService = teachingService;
    }

    @Inject
    public void setAdminClassService(AdminClassService adminClassService) {
        this.adminClassService = adminClassService;
    }
    
}
