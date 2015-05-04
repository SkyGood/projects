package com.uccyou.ta.teacher.homework.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.homework.repository.HomeworkRepository;
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

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})  
public class HomeworkService {
    
    private HomeworkRepository homeworkRepository;
    @Inject
    public void setHomeworkRepository(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }
    
    public PageModel<HomeworkSearchResponse> manage(HomeworkSearchRequest request, Integer classId, Integer pageNo, Integer pageSize) {
        return homeworkRepository.manage(request, classId, pageNo, pageSize);
    }
    
    @Transactional
    public Boolean alive(Integer workId, String status) {
        return homeworkRepository.alive(workId, status);
    }
    public UpdateHomeworkResponse update(Integer workId) {
        return homeworkRepository.update(workId);
    }
    
    @Transactional
    public UpdateHomeworkAttachmentResponse update(UpdateHomeworkRequest request) {
        return homeworkRepository.update(request);
    }

    @Transactional
    public Boolean add(AddHomeworkRequest request) {
        return homeworkRepository.add(request);
    }

    public PageModel<HomeworkDetailResponse> detail(HomeworkDetailRequest request, Integer pageNo, Integer pageSize) {
        return homeworkRepository.detail(request, pageNo, pageSize);
    }

    public HomeworkLevelResponse level(Integer id) {
        return homeworkRepository.level(id);
    }

    @Transactional
    public Boolean level(HomeworkLevelRequest request) {
        return homeworkRepository.level(request);
    }

    @Transactional
    public HomeworkRemoveResponse remove(Integer workId) {
        return homeworkRepository.remove(workId);
    }
    
}
