package com.uccyou.ta.student.subject.web;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.student.subject.request.SubjectRequest;
import com.uccyou.ta.student.subject.response.SubjectResponse;
import com.uccyou.ta.student.subject.service.SubjectService;

@RestController
public class SubjectController {

    private SubjectService subjectService;

    @Inject
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping(value = "/student/subject/search/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<SubjectResponse> subject(@RequestBody SubjectRequest request, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return subjectService.subject(request, pageNo, pageSize);
    }

}
