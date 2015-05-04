package com.uccyou.ta.teacher.teaching.student.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.uccyou.ta.teacher.teaching.student.repository.ClazzStudentRepository;
import com.uccyou.ta.teacher.teaching.student.web.request.ImportClazzStudentForm;

@Service
public class ClazzStudentService {

    private ClazzStudentRepository clazzStudentRepository;

    public int importStudent(ImportClazzStudentForm form, MultipartFile file, HttpServletRequest request) {
        return clazzStudentRepository.importStudent(form, file, request);
    }
    
    @Inject
    public void setClazzStudentRepository(ClazzStudentRepository clazzStudentRepository) {
        this.clazzStudentRepository = clazzStudentRepository;
    }
}
