package com.uccyou.ta.student.homework.web;

import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.student.homework.response.HomeworkResponse;
import com.uccyou.ta.student.homework.service.StudentHomeworkService;
import com.uccyou.ta.student.homework.web.request.CommitForm;
import com.uccyou.ta.student.homework.web.request.HomeworkForm;
import com.uccyou.ta.student.subject.response.CommitResponse;

@Controller
public class StudentHomeworkController {

    private StudentHomeworkService studentHomeworkService;

    @Inject
    public void setStudentHomeworkService(StudentHomeworkService studentHomeworkService) {
        this.studentHomeworkService = studentHomeworkService;
    }

    @RequestMapping(value = "/student/homework/search", method = RequestMethod.GET)
    public String search(Map<String, Object> map, HttpSession session) {
        HomeworkForm form = new HomeworkForm();
        String identityCode  = (String) session.getAttribute(SessionConstants.IDENTITY_CODE);
        form.setIdentityCode(identityCode);
        PageModel<HomeworkResponse> pageModel = studentHomeworkService.search(form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("homework", form);
        session.setAttribute(SessionConstants.HOMEWORK_SEARCH, form);
        return "website/student/homework/homework";
    }

    @RequestMapping(value = "/student/homework/search", method = RequestMethod.POST)
    public String search(HomeworkForm form, Map<String, Object> map, HttpSession session) {
        PageModel<HomeworkResponse> pageModel = studentHomeworkService.search(form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("homework", form);
        session.setAttribute(SessionConstants.HOMEWORK_SEARCH, form);
        return "website/student/homework/homework";
    }

    @RequestMapping(value = "/student/homework/search/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String search(HttpSession seesion, Map<String, Object> map, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        HomeworkForm form = (HomeworkForm) seesion.getAttribute(SessionConstants.HOMEWORK_SEARCH);
        PageModel<HomeworkResponse> pageModel = studentHomeworkService.search(form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("homework", form);
        return "website/student/homework/homework";
    }

    @RequestMapping(value = "/student/homework/commit/{workId}", method = RequestMethod.GET)
    public String commit(Map<String, Object> map, HttpSession session, @PathVariable("workId") Integer workId) {
        CommitResponse response = studentHomeworkService.commit(workId);
        String identityCode  = (String) session.getAttribute(SessionConstants.IDENTITY_CODE);
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        map.put("homework", response);
        map.put("studentCode", identityCode);
        map.put("workId", workId);
        map.put("userId", userId);
        return "website/student/homework/commit";
    }

    @RequestMapping(value = "/student/homework/commit", method = RequestMethod.POST)
    public String commit(CommitForm form, @RequestParam("res") MultipartFile file, HttpServletRequest request, Map<String, Object> map) {
        Boolean flag = studentHomeworkService.commit(form, file, request);
        if (!flag) {
            String tip = "提交失败！";
            map.put("tip", tip);
        }
        return "redirect:/student/homework/search/";
    }

}
