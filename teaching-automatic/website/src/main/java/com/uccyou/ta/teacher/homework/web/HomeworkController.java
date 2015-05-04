package com.uccyou.ta.teacher.homework.web;

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
import com.uccyou.ta.teacher.homework.response.HomeworkDetailResponse;
import com.uccyou.ta.teacher.homework.response.HomeworkLevelResponse;
import com.uccyou.ta.teacher.homework.response.HomeworkSearchResponse;
import com.uccyou.ta.teacher.homework.response.UpdateHomeworkResponse;
import com.uccyou.ta.teacher.homework.service.HomeworkService;
import com.uccyou.ta.teacher.homework.web.request.AddHomeworkForm;
import com.uccyou.ta.teacher.homework.web.request.HomeworkLevelForm;
import com.uccyou.ta.teacher.homework.web.request.HomeworkSearchForm;
import com.uccyou.ta.teacher.homework.web.request.UpdateHomeworkForm;

@Controller
public class HomeworkController {
    
    private HomeworkService homeworkService;
    @Inject
    public void setHomeworkService(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }
    
    @RequestMapping(value = "/teacher/homework/search/{classId}", method = RequestMethod.GET) 
    public String search(@PathVariable("classId") Integer classId, Map<String, Object> map) {
        PageModel<HomeworkSearchResponse> pageModel = homeworkService.search(classId, null, 1, 10);
        map.put("classId", classId);
        map.put("pageModel", pageModel);
        return "website/teacher/homework/homework";
    }
    
    @RequestMapping(value = "/teacher/homework/search/{classId}/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String search(@PathVariable("classId") Integer classId, @PathVariable("pageNo") Integer pageNo, @PathVariable Integer pageSize, HttpSession session, Map<String, Object> map) {
        HomeworkSearchForm form =  (HomeworkSearchForm) session.getAttribute(SessionConstants.HOMEWORK_SEARCH);
        PageModel<HomeworkSearchResponse> pageModel = homeworkService.search(classId, form, pageNo, pageSize);
        map.put("classId", classId);
        map.put("pageModel", pageModel);
        map.put("homework", form);
        return "website/teacher/homework/homework";
    }
    
    @RequestMapping(value = "/teacher/homework/search/{classId}", method = RequestMethod.POST)
    public String search(HomeworkSearchForm form, @PathVariable("classId") Integer classId, Map<String, Object> map, HttpSession session) {
        PageModel<HomeworkSearchResponse> pageModel = homeworkService.search(classId, form, 1, 10);
        session.setAttribute(SessionConstants.HOMEWORK_SEARCH, form);
        map.put("pageModel", pageModel);
        map.put("classId", classId);
        map.put("homework", form);
        return "website/teacher/homework/homework";
    }
    
    @RequestMapping(value = "/teacher/homework/alive/{workId}/{status}", method = RequestMethod.GET)
    @ResponseBody
    public boolean alive(@PathVariable("workId") Integer workId, @PathVariable("status") String status) {
        return homeworkService.alive(workId, status);
    } 
    
    @RequestMapping(value = "/teacher/homework/update/{classId}/{workId}", method = RequestMethod.GET)
    public String update(HttpSession session, @PathVariable("classId") Integer classId, @PathVariable("workId") Integer workId, Map<String, Object> map) {
        UpdateHomeworkResponse homework = homeworkService.update(workId);
        map.put("homework", homework);
        map.put("classId", classId);
        Integer teacherId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        map.put("teacherId", teacherId);
        return "website/teacher/homework/updatehomework";
    }
    
    @RequestMapping(value =  "/teacher/homework/update/{classId}", method = RequestMethod.POST)
    public String update(UpdateHomeworkForm form, @RequestParam("res") MultipartFile file, HttpServletRequest request, @PathVariable("classId") Integer classId) {
        homeworkService.update(form, file, request);
        return "redirect:/teacher/homework/search/" + classId;
    }
    
    @RequestMapping(value = "/teacher/homework/add/{classId}", method = RequestMethod.GET)
    public String add(@PathVariable("classId") Integer classId, Map<String, Object> map, HttpSession session) {
        Integer teacherId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        map.put("classId", classId);
        map.put("teacherId", teacherId);
        return "website/teacher/homework/addhomework";
    }
    
    @RequestMapping(value = "/teacher/homework/add", method = RequestMethod.POST)
    public String add(AddHomeworkForm form, @RequestParam("res") MultipartFile file, HttpServletRequest request, Map<String, Object> map) {
        Boolean flag = homeworkService.add(form, file, request);
        if (!flag) {
            String tip = "上传失败！";
            map.put("tip", tip);
        }
        return "redirect:/teacher/homework/search/" + form.getClassId();
    }
    
    @RequestMapping(value = "/teacher/homework/detail/{classId}/{workId}", method = RequestMethod.GET)
    public String detail(@PathVariable("classId") Integer classId, @PathVariable("workId") Integer workId, HttpSession session, Map<String, Object> map) {
        session.setAttribute(SessionConstants.TEACHING_CLASS_ID, classId);
        session.setAttribute(SessionConstants.WORK_ID, workId);
        PageModel<HomeworkDetailResponse> pageModel =  homeworkService.detail(classId, workId, "Y", 1, 10);
        map.put("pageModel", pageModel);
        map.put("type", "commit");
        map.put("classId", classId);
        return "website/teacher/homework/homeworkdetail";
    }

    @RequestMapping(value = "/teacher/homework/commit", method = RequestMethod.GET)
    public String commit(HttpSession session, Map<String, Object> map) {  
        Integer classId = (Integer) session.getAttribute(SessionConstants.TEACHING_CLASS_ID);
        Integer workId = (Integer) session.getAttribute(SessionConstants.WORK_ID);
        PageModel<HomeworkDetailResponse> pageModel =  homeworkService.detail(classId, workId, "Y", 1, 10);
        map.put("pageModel", pageModel);
        map.put("type", "commit");
        return "website/teacher/homework/homeworkdetail";
    }
    
    @RequestMapping(value = "/teacher/homework/commit/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String commit(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, HttpSession session, Map<String, Object> map) {
        Integer classId = (Integer) session.getAttribute(SessionConstants.TEACHING_CLASS_ID);
        Integer workId = (Integer) session.getAttribute(SessionConstants.WORK_ID);
        PageModel<HomeworkDetailResponse> pageModel = homeworkService.detail(classId, workId, "Y", pageNo, pageSize);
        map.put("pageModel", pageModel);
        map.put("type", "commit");
        return "website/teacher/homework/homeworkdetail";
    }
    
    
    @RequestMapping(value = "/teacher/homework/uncommit", method = RequestMethod.GET)
    public String uncommit(HttpSession session, Map<String, Object> map) {
        Integer classId = (Integer) session.getAttribute(SessionConstants.TEACHING_CLASS_ID);
        Integer workId = (Integer) session.getAttribute(SessionConstants.WORK_ID);
        PageModel<HomeworkDetailResponse> pageModel = homeworkService.detail(classId, workId, "N", 1, 10);
        map.put("pageModel", pageModel);
        map.put("type", "uncommit");
        return "website/teacher/homework/homeworkdetail";
    }
    
    
    @RequestMapping(value = "/teacher/homework/uncommit/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String uncommit(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, HttpSession session, Map<String, Object> map) {
        Integer classId = (Integer) session.getAttribute(SessionConstants.TEACHING_CLASS_ID);
        Integer workId = (Integer) session.getAttribute(SessionConstants.WORK_ID);
        PageModel<HomeworkDetailResponse> pageModel = homeworkService.detail(classId, workId, "N", pageNo, pageSize);
        map.put("pageModel", pageModel);
        map.put("type", "uncommit");
        return "website/teacher/homework/homeworkdetail";
    }
    
    @RequestMapping(value = "/teacher/homework/level/{id}", method = RequestMethod.GET)
    public String level(@PathVariable("id") Integer id, Map<String, Object> map) {
        HomeworkLevelResponse response = homeworkService.level(id);
        map.put("response", response);
        map.put("id", id);
        return "website/teacher/homework/levelhomework";
    }
    
    @RequestMapping(value = "/teacher/homework/level", method = RequestMethod.POST)
    public String level(HomeworkLevelForm form, Map<String, Object> map) {
        Boolean flag = homeworkService.level(form); 
        if (!flag) {
            map.put("tip", "操作失败！");
            return "redirect:/teacher/homework/level/" + form.getId();
        }
        return "redirect:/teacher/homework/commit/1/10";
    }
    
    @RequestMapping(value = "/teacher/homework/remove/{workId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean remove(@PathVariable("workId") Integer workId, HttpServletRequest request) {
        return homeworkService.remove(workId, request);
    }
}
