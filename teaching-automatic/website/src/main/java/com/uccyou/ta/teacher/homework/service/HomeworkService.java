package com.uccyou.ta.teacher.homework.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.homework.repository.HomeworkRepository;
import com.uccyou.ta.teacher.homework.response.HomeworkDetailResponse;
import com.uccyou.ta.teacher.homework.response.HomeworkLevelResponse;
import com.uccyou.ta.teacher.homework.response.HomeworkSearchResponse;
import com.uccyou.ta.teacher.homework.response.UpdateHomeworkResponse;
import com.uccyou.ta.teacher.homework.web.request.AddHomeworkForm;
import com.uccyou.ta.teacher.homework.web.request.HomeworkLevelForm;
import com.uccyou.ta.teacher.homework.web.request.HomeworkSearchForm;
import com.uccyou.ta.teacher.homework.web.request.UpdateHomeworkForm;

@Service
public class HomeworkService {
    
    private HomeworkRepository homeworkRepository;
    @Inject
    public void setHomeworkRepository(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }
    public PageModel<HomeworkSearchResponse> search(Integer classId, HomeworkSearchForm form, int pageNo, int pageSize) {
        return homeworkRepository.search(classId, form, pageNo, pageSize);
    }

    public Boolean alive(Integer workId, String status) {
        return homeworkRepository.alive(workId, status);
    }
    
    public UpdateHomeworkResponse update(Integer workId) {
        return homeworkRepository.update(workId);
    }
    public Boolean update(UpdateHomeworkForm form, MultipartFile file, HttpServletRequest request) {
        return homeworkRepository.update(form, file, request);
    }
    public Boolean add(AddHomeworkForm form, MultipartFile file, HttpServletRequest request) {
        return homeworkRepository.add(form, file, request);
    }
    
    public PageModel<HomeworkDetailResponse> detail(Integer classId, Integer workId, String status, int pageNo, int pageSize) {
        return homeworkRepository.detail(classId, workId, status, pageNo, pageSize);
    }
    public HomeworkLevelResponse level(Integer id) {
        return homeworkRepository.level(id);
    }
    public Boolean level(HomeworkLevelForm form) {
        return homeworkRepository.level(form);
    }
    public Boolean remove(Integer workId, HttpServletRequest request) {
        return homeworkRepository.remove(workId, request);
    }
}
