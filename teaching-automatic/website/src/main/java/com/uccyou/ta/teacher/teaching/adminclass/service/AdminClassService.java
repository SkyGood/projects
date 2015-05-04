package com.uccyou.ta.teacher.teaching.adminclass.service;

import javax.inject.Inject;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.uccyou.ta.teacher.checking.response.AdminClassNameResponse;
import com.uccyou.ta.teacher.teaching.adminclass.repository.AdminClassRepository;
import com.uccyou.ta.teacher.teaching.adminclass.web.request.AddAdminClassForm;
import com.uccyou.ta.teacher.teaching.adminclass.web.request.ChangeAdminClassNameForm;

@Service
public class AdminClassService {

    private AdminClassRepository adminClassRepository;

    @Inject
    public void setAdminClassRepository(AdminClassRepository adminClassRepository) {
        this.adminClassRepository = adminClassRepository;
    }

    @CacheEvict(value = "defaultCache", key = "#form.teachingClassId + 'adminClasses'")
    public Integer add(AddAdminClassForm form) {
        return adminClassRepository.add(form);
    }

    @CacheEvict(value = "defaultCache", key = "#form.teachingClassId + 'adminClasses'")
    public Boolean change(ChangeAdminClassNameForm form) {
        return adminClassRepository.change(form);
    }

    @CacheEvict(value = "defaultCache", key = "#teachingclassId + 'adminClasses'")
    public Boolean remove(Integer adminClassId, Integer teachingclassId) {
        return adminClassRepository.remove(adminClassId);
    }

    public Boolean clean(Integer adminClassId) {
        return adminClassRepository.clean(adminClassId);
    }

    public AdminClassNameResponse findAdminClassNameById(Integer adminClassId) {
        return adminClassRepository.findAdminClassNameById(adminClassId);
    }
}
