package com.uccyou.ta.teacher.homework.web;

import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.homework.request.AddHomeworkRequest;
import com.uccyou.ta.teacher.homework.request.HomeworkDetailRequest;
import com.uccyou.ta.teacher.homework.request.HomeworkLevelRequest;
import com.uccyou.ta.teacher.homework.request.HomeworkSearchRequest;
import com.uccyou.ta.teacher.homework.request.UpdateHomeworkRequest;
import com.uccyou.ta.teacher.homework.response.HomeworkDetailResponse;
import com.uccyou.ta.teacher.homework.response.HomeworkLevelResponse;
import com.uccyou.ta.teacher.homework.response.HomeworkRemoveResponse;
import com.uccyou.ta.teacher.homework.response.HomeworkSearchResponse;
import com.uccyou.ta.teacher.homework.response.UpdateHomeworkAttachmentResponse;
import com.uccyou.ta.teacher.homework.response.UpdateHomeworkResponse;
import com.uccyou.ta.teacher.homework.service.HomeworkService;


@RestController
public class HomeworkController {
    
    private HomeworkService homeworkService;
    @Inject
    public void setHomeworkService(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }
    
    @RequestMapping(value = "/teacher/homework/manage/{classId}/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    public PageModel<HomeworkSearchResponse> manage(@RequestBody HomeworkSearchRequest request, @PathVariable("classId") Integer classId, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return homeworkService.manage(request, classId, pageNo, pageSize);
    }
    
    @RequestMapping(value = "/teacher/homework/manage/{workId}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean alive(@PathVariable("workId") Integer workId, @PathVariable("status") String status) {
        return homeworkService.alive(workId, status);
    }
    
    @RequestMapping(value = "/teacher/homework/update/{workId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateHomeworkResponse update(@PathVariable("workId") Integer workId) {
        return homeworkService.update(workId);
    }
    
    @RequestMapping(value = "/teacher/homework/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateHomeworkAttachmentResponse update(@RequestBody UpdateHomeworkRequest request) {
        return homeworkService.update(request);
    }
    
    @RequestMapping(value = "/teacher/homework/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean add(@RequestBody AddHomeworkRequest request) {
        return homeworkService.add(request);
    }
    
    @RequestMapping(value = "/teacher/homework/detail/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<HomeworkDetailResponse> detail(@RequestBody HomeworkDetailRequest request, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return homeworkService.detail(request, pageNo, pageSize);
    }
    
    @RequestMapping(value = "/teacher/homework/level/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HomeworkLevelResponse level(@PathVariable("id") Integer id) {
        return homeworkService.level(id);
    }
    
    @RequestMapping(value = "/teacher/homework/level", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean level(@RequestBody HomeworkLevelRequest request) {
        return homeworkService.level(request);
    }
    
    @RequestMapping(value = "/teacher/homework/remove/{workId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public HomeworkRemoveResponse remove(@PathVariable("workId") Integer workId) {
        return homeworkService.remove(workId);
    }
    
}
