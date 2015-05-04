package com.uccyou.ta.student.homework.web;

import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.student.homework.request.CommitRequest;
import com.uccyou.ta.student.homework.request.HomeworkRequest;
import com.uccyou.ta.student.homework.response.HomeworkResponse;
import com.uccyou.ta.student.homework.service.HomeworkService;
import com.uccyou.ta.student.subject.response.CommitResponse;

@RestController
public class HomeworkController {
    private HomeworkService homeworkService;

    @Inject
    public void setHomeworkService(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }
    
    @RequestMapping(value = "/student/homework/search/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<HomeworkResponse> search(@RequestBody HomeworkRequest request, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return homeworkService.search(request, pageNo, pageSize);
    }
    
    @RequestMapping(value = "/student/homework/commit/{workId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CommitResponse commit(@PathVariable("workId") Integer workId) {
        return homeworkService.commit(workId);
    }
    
    @RequestMapping(value = "/student/homework/commit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean commit(@RequestBody CommitRequest request) {
        return homeworkService.commit(request);
    }
    
}
