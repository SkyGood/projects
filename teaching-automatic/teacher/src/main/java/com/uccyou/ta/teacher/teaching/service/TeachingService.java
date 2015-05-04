package com.uccyou.ta.teacher.teaching.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.teaching.admin.response.AdminClassResponse;
import com.uccyou.ta.teacher.teaching.repository.TeachingRepository;
import com.uccyou.ta.teacher.teaching.request.CreateTeachingClassRequest;
import com.uccyou.ta.teacher.teaching.request.TeachingClassSearchRequest;
import com.uccyou.ta.teacher.teaching.request.UpdateTeachingClassRequest;
import com.uccyou.ta.teacher.teaching.response.TeachingClassResponse;

@Service
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class TeachingService {

    private TeachingRepository teachingRepository;

    public PageModel<TeachingClassResponse> clazz(TeachingClassSearchRequest request, Integer pageNo, Integer pageSize) {
        return teachingRepository.clazz(request, pageNo, pageSize);
    }

    @Transactional
    public int create(CreateTeachingClassRequest request) {
        return teachingRepository.create(request);
    }

    public TeachingClassResponse findClassById(Integer classId) {
        return teachingRepository.findClassById(classId);
    }

    @Transactional
    public Boolean update(UpdateTeachingClassRequest request) {
        return teachingRepository.update(request);
    }

    public List<AdminClassResponse> manage(Integer classId) {
        return teachingRepository.manage(classId);
    }
    
    @Transactional
    public boolean remove(Integer classId) {
        return teachingRepository.remove(classId);
    }
    
    @Inject
    public void setTeachingRepository(TeachingRepository teachingRepository) {
        this.teachingRepository = teachingRepository;
    }
}
