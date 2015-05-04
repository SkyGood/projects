package com.uccyou.ta.teacher.teaching.teaching.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.teaching.admin.response.AdminClassResponse;
import com.uccyou.ta.teacher.teaching.response.TeachingClassResponse;
import com.uccyou.ta.teacher.teaching.teaching.repository.TeachingRepository;
import com.uccyou.ta.teacher.teaching.teaching.web.request.CreateTeachingClassForm;
import com.uccyou.ta.teacher.teaching.teaching.web.request.TeachingClassSearchForm;
import com.uccyou.ta.teacher.teaching.teaching.web.request.UpdateTeachingClassForm;

@Service
public class TeachingService {

    private TeachingRepository teachingRepository;

    public PageModel<TeachingClassResponse> clazz(Integer userId, TeachingClassSearchForm form, Integer pageNo, Integer pageSize) {
        return teachingRepository.clazz(userId, form, pageNo, pageSize);
    }
    
    public int create(CreateTeachingClassForm form) {
        return teachingRepository.create(form);
    }
    
    public boolean update(UpdateTeachingClassForm form, Integer userId) {
        return teachingRepository.update(form);
    }
    
    public TeachingClassResponse findClassById(Integer classId) {
        return teachingRepository.findClassById(classId);
    }
    
    @Cacheable(value = "defaultCache", key = "#classId + 'adminClasses'")
    public List<AdminClassResponse> findAdminClass(Integer classId) {
        return teachingRepository.findAdminClass(classId);
    }
    
    public boolean remove(Integer classId, Integer userId) {
        return teachingRepository.remove(classId);
    }
    
    @Inject
    public void setTeachingRepository(TeachingRepository teachingRepository) {
        this.teachingRepository = teachingRepository;
    }
}
