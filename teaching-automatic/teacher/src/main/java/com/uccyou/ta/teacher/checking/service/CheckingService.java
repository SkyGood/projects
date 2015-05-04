package com.uccyou.ta.teacher.checking.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.checking.repository.CheckingRepository;
import com.uccyou.ta.teacher.checking.request.CheckingAdminRequest;
import com.uccyou.ta.teacher.checking.request.CheckingRandomRequest;
import com.uccyou.ta.teacher.checking.response.AdminClassNameResponse;
import com.uccyou.ta.teacher.checking.response.ClassStudentResponse;

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class CheckingService {

    private CheckingRepository checkingRepository;

    public PageModel<ClassStudentResponse> checkingAll(Integer classId, Integer pageNo, Integer pageSize) {
        return checkingRepository.checkingAll(classId, pageNo, pageSize);
    }
    
    public PageModel<ClassStudentResponse> checkingAdmin(CheckingAdminRequest request, Integer pageNo, Integer pageSize) {
        return checkingRepository.checkingAdmin(request, pageNo, pageSize);
    }
    
    @Transactional
    public PageModel<ClassStudentResponse> checkingRandom(CheckingRandomRequest request) {
        return checkingRepository.checkingRandom(request);
    }
    
    public PageModel<ClassStudentResponse> randomPaging(Integer classId, Integer pageNo, Integer pageSize) {
        return checkingRepository.randomPaging(classId, pageNo, pageSize);
    }
    
    @Transactional
    public Boolean checkingClose(Integer classId) {
        return checkingRepository.checkingClose(classId);
    }
    
    @Transactional
    public Boolean absent(Integer classId, Integer studentId) {
        return checkingRepository.absent(classId, studentId);
    }
    
    @Transactional
    public Boolean note(Integer classId, Integer studentId) {
        return checkingRepository.note(classId, studentId);
    }
    
    public AdminClassNameResponse findAdminClassNameById(Integer adminClassId) {
        return checkingRepository.findAdminClassNameById(adminClassId);
    }
    
    @Inject
    public void setCheckingRepository(CheckingRepository checkingRepository) {
        this.checkingRepository = checkingRepository;
    }

}
