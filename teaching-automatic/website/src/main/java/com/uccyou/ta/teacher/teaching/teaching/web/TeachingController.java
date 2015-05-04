package com.uccyou.ta.teacher.teaching.teaching.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.teacher.teaching.admin.response.AdminClassResponse;
import com.uccyou.ta.teacher.teaching.response.TeachingClassResponse;
import com.uccyou.ta.teacher.teaching.teaching.service.TeachingService;
import com.uccyou.ta.teacher.teaching.teaching.web.request.CreateTeachingClassForm;
import com.uccyou.ta.teacher.teaching.teaching.web.request.TeachingClassSearchForm;
import com.uccyou.ta.teacher.teaching.teaching.web.request.UpdateTeachingClassForm;

@Controller
public class TeachingController {

    private TeachingService teachingService;

    @RequestMapping(value = "/teacher/clazz", method = RequestMethod.GET)
    public String clazz(HttpSession session, Map<String, Object> map) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        session.setAttribute(SessionConstants.CLAZZ_SEARCH, null);
        PageModel<TeachingClassResponse> pageModel = teachingService.clazz(userId, null, 1, 10);
        map.put("pageModel", pageModel);
        return "website/teacher/teaching/teachingclass/clazz";
    }

    @RequestMapping(value = "/teacher/clazz", method = RequestMethod.POST)
    public String clazz(TeachingClassSearchForm form, HttpSession session, Map<String, Object> map) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        session.setAttribute(SessionConstants.CLAZZ_SEARCH, form);
        PageModel<TeachingClassResponse> pageModel = teachingService.clazz(userId, form, 1, 10);
        map.put("pageModel", pageModel);
        return "website/teacher/teaching/teachingclass/clazz";
    }

    @RequestMapping(value = "/teacher/clazz/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String clazz(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, HttpSession session, Map<String, Object> map) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        TeachingClassSearchForm form = (TeachingClassSearchForm) session.getAttribute(SessionConstants.CLAZZ_SEARCH);
        PageModel<TeachingClassResponse> pageModel = teachingService.clazz(userId, form, pageNo, pageSize);
        map.put("pageModel", pageModel);
        return "website/teacher/teaching/teachingclass/clazz";
    }

    @RequestMapping(value = "/teacher/clazz/add", method = RequestMethod.GET)
    public String create() {
        return "website/teacher/teaching/teachingclass/addclazz";
    }

    @RequestMapping(value = "/teacher/clazz/add", method = RequestMethod.POST)
    public String create(CreateTeachingClassForm form, HttpSession session) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        String identityCode = (String) session.getAttribute(SessionConstants.IDENTITY_CODE);
        form.setUserId(userId);
        form.setIdentityCode(identityCode);
        int clazzId = teachingService.create(form);
        return "redirect:/teacher/clazz";
    }

    @RequestMapping(value = "/teacher/clazz/change/{classId}", method = RequestMethod.GET)
    public String update(@PathVariable("classId") Integer classId, Map<String, Object> map) {
        TeachingClassResponse response = teachingService.findClassById(classId);
        if (StringUtils.hasText(response.getClassName())) {
            map.put("clazz", response);
            return "website/teacher/teaching/teachingclass/updateclazz";
        } else {
            return "redirect:/teacher/clazz";
        }
    }

    @RequestMapping(value = "/teacher/clazz/change", method = RequestMethod.POST)
    public String update(UpdateTeachingClassForm form, HttpSession session) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        teachingService.update(form, userId);
        return "redirect:/teacher/clazz";
    }
    
    @RequestMapping(value = "/teacher/clazz/manage/{classId}", method = RequestMethod.GET)
    public String manage(@PathVariable("classId") Integer classId, Map<String, Object> map) {
        TeachingClassResponse response = teachingService.findClassById(classId);
        List<AdminClassResponse> list = teachingService.findAdminClass(classId);
        if (StringUtils.hasText(response.getClassName())) {
            map.put("clazz", response);
            map.put("list", list);
            return "website/teacher/teaching/teachingclass/management";
        }
        return "redirect:/teacher/clazz";
    }
    
    @RequestMapping(value = "/teacher/clazz/remove/{classId}", method = RequestMethod.GET)
    @ResponseBody
    public boolean remove(@PathVariable("classId") Integer classId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        return teachingService.remove(classId, userId);
    }

    @Inject
    public void setTeachingService(TeachingService teachingService) {
        this.teachingService = teachingService;
    }
}
