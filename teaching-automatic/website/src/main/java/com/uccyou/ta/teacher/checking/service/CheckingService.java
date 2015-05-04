package com.uccyou.ta.teacher.checking.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.checking.repository.CheckingRepository;
import com.uccyou.ta.teacher.checking.response.ClassStudentResponse;
import com.uccyou.ta.teacher.checking.web.request.CheckingRandomForm;

@Service
public class CheckingService {

    private CheckingRepository checkingRepository;

    public PageModel<ClassStudentResponse> findAllStudentsByClassId(Integer classId, Integer pageNo, Integer pageSize) {
        return checkingRepository.findAllStudentsByClassId(classId, pageNo, pageSize);
    }
    
    public PageModel<ClassStudentResponse> checkingAdmin(Integer adminClassId, Integer classId, int pageNo, int pageSize) {
        return checkingRepository.checkingAdmin(adminClassId, classId, pageNo, pageSize);
    }
    
    public PageModel<ClassStudentResponse> checkingRandom(CheckingRandomForm form) {
        return checkingRepository.checkingRandom(form);
    }
    
    public PageModel<ClassStudentResponse> randomPaging(Integer classId, Integer pageNo, Integer pageSize) {
        return checkingRepository.randomPaging(classId, pageNo, pageSize);
    }
    
    public boolean checkingClose(Integer classId) {
        return checkingRepository.checkingClose(classId);
    }
    
    public boolean absent(Integer classId, Integer studentId) {
        return checkingRepository.absent(classId, studentId);
    }
    
    public boolean note(Integer classId, Integer studentId) {
        return checkingRepository.note(classId, studentId);
    }
    
    @Inject
    public void setCheckingRepository(CheckingRepository checkingRepository) {
        this.checkingRepository = checkingRepository;
    }
    
}
