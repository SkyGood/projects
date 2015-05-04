package com.uccyou.ta.student.subject.web;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.student.subject.response.SubjectResponse;
import com.uccyou.ta.student.subject.service.SubjectService;
import com.uccyou.ta.student.subject.web.request.SubjectForm;

@Controller
public class SubjectController {
    
    private SubjectService subjectService;
    @Inject
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    
    @RequestMapping(value = "/student/subject/search", method = RequestMethod.GET)
    public String search(Map<String, Object> map, HttpSession session) {
        SubjectForm form = new SubjectForm();
        String identityCode = (String) session.getAttribute(SessionConstants.IDENTITY_CODE);
        form.setIdentityCode(identityCode);
        PageModel<SubjectResponse> pageModel = subjectService.search(form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("subject", form);
        session.setAttribute(SessionConstants.SUBJECT_SEARCH, form);
        return "website/student/subject/subject";
    }
    
    @RequestMapping(value = "/student/subject/search", method = RequestMethod.POST)
    public String search(SubjectForm form, HttpSession session, Map<String, Object> map) {
        session.setAttribute(SessionConstants.SUBJECT_SEARCH, form);
        PageModel<SubjectResponse> pageModel = subjectService.search(form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("subject", form);
        return "website/student/subject/subject";
    }
    
    @RequestMapping(value = "/student/subject/search/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String search(HttpSession session, Map<String, Object> map, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        SubjectForm form = (SubjectForm) session.getAttribute(SessionConstants.SUBJECT_SEARCH);
        PageModel<SubjectResponse> pageModel = subjectService.search(form, pageNo, pageSize);
        map.put("pageModel", pageModel);
        map.put("subject", form);
        return "website/student/subject/subject";
    }
    
}
