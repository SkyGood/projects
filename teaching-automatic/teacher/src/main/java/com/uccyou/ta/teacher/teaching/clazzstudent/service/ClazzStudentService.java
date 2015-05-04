package com.uccyou.ta.teacher.teaching.clazzstudent.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.ta.teacher.teaching.clazzstudent.repository.ClazzStudentRepository;
import com.uccyou.ta.teacher.teaching.request.ImportClazzStudentRequest;

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class ClazzStudentService {

    private ClazzStudentRepository clazzStudentRepository;

    @Transactional
    public int importStudent(ImportClazzStudentRequest request, HttpServletRequest req) {
        return clazzStudentRepository.importStudent(request, req);
    }
    
    @Inject
    public void setClazzStudentRepository(ClazzStudentRepository clazzStudentRepository) {
        this.clazzStudentRepository = clazzStudentRepository;
    }
}
