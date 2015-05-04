package com.uccyou.ta.student.subject.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.student.subject.repository.SubjectRepository;
import com.uccyou.ta.student.subject.response.SubjectResponse;
import com.uccyou.ta.student.subject.web.request.SubjectForm;

@Service
public class SubjectService {
    
    private SubjectRepository subjectRepository;
    @Inject
    public void setSubjectRepository(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }
    public PageModel<SubjectResponse> search(SubjectForm form, int pageNo, int pageSize) {
        return subjectRepository.search(form, pageNo, pageSize);
    }
    
}
