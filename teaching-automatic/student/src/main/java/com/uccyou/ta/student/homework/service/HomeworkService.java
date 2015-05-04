package com.uccyou.ta.student.homework.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.student.homework.repository.HomeworkRepository;
import com.uccyou.ta.student.homework.request.CommitRequest;
import com.uccyou.ta.student.homework.request.HomeworkRequest;
import com.uccyou.ta.student.homework.response.HomeworkResponse;
import com.uccyou.ta.student.subject.response.CommitResponse;

@Service
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class HomeworkService {
    
    private HomeworkRepository homeworkRepository;
    @Inject
    public void setHomeworkRepository(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }
    
    public PageModel<HomeworkResponse> search(HomeworkRequest request, Integer pageNo, Integer pageSize) {
        return homeworkRepository.search(request, pageNo, pageSize);
    }

    public CommitResponse commit(Integer workId) {
        return homeworkRepository.commit(workId);
    }

    @Transactional
    public Boolean commit(CommitRequest request) {
        return homeworkRepository.commit(request);
    }
    
}
