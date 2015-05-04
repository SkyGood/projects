package com.uccyou.ta.teacher.teaching.adminclass.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.ta.teacher.teaching.admin.request.AddAdminClassRequest;
import com.uccyou.ta.teacher.teaching.admin.request.ChangeAdminClassNameRequest;
import com.uccyou.ta.teacher.teaching.adminclass.repository.AdminClassRepository;

@Service
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class AdminClassService {
    
    private AdminClassRepository adminClassRepository;
    
    @Inject
    public void setAdminClassRepository(AdminClassRepository adminClassRepository) {
        this.adminClassRepository = adminClassRepository;
    }

    @Transactional
    public Integer add(AddAdminClassRequest request) {
        return adminClassRepository.add(request);
    }

    @Transactional
    public boolean change(ChangeAdminClassNameRequest request) {
        return adminClassRepository.change(request);
    }

    @Transactional
    public Boolean remove(Integer adminClassId) {
        return adminClassRepository.remove(adminClassId);
    }

    @Transactional
    public Boolean clean(Integer adminClassId) {
        return adminClassRepository.clean(adminClassId);
    }
}
