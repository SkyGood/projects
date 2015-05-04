package com.uccyou.ta.student.subject.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.student.subject.repository.SubjectRepository;
import com.uccyou.ta.student.subject.request.SubjectRequest;
import com.uccyou.ta.student.subject.response.SubjectResponse;

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class SubjectService {
    
    private SubjectRepository subjectRepository;
    @Inject
    public void setSubjectRepository(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    public PageModel<SubjectResponse> subject(SubjectRequest request, Integer pageNo, Integer pageSize) {
        return subjectRepository.subject(request, pageNo, pageSize);
    }
    
}
