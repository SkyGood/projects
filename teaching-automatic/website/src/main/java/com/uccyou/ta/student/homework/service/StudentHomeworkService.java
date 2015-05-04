package com.uccyou.ta.student.homework.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.student.homework.repository.StudentHomeworkRepository;
import com.uccyou.ta.student.homework.response.HomeworkResponse;
import com.uccyou.ta.student.homework.web.request.CommitForm;
import com.uccyou.ta.student.homework.web.request.HomeworkForm;
import com.uccyou.ta.student.subject.response.CommitResponse;

@Service
public class StudentHomeworkService {

    private StudentHomeworkRepository studentHomeworkRepository;

    @Inject
    public void setStudentHomeworkRepository(StudentHomeworkRepository studentHomeworkRepository) {
        this.studentHomeworkRepository = studentHomeworkRepository;
    }

    public PageModel<HomeworkResponse> search(HomeworkForm form, int pageNo, int pageSize) {
        return studentHomeworkRepository.search(form, pageNo, pageSize);
    }

    public CommitResponse commit(Integer workId) {
        return studentHomeworkRepository.commit(workId);
    }

    public Boolean commit(CommitForm form, MultipartFile file, HttpServletRequest request) {
        return studentHomeworkRepository.commit(form, file, request);
    }

}
