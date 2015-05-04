package com.uccyou.ta.admin.service;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.admin.repository.AdminRepository;
import com.uccyou.ta.system.admin.request.AddUserRequest;
import com.uccyou.ta.system.admin.request.AdminStatusRequest;
import com.uccyou.ta.system.admin.request.ChangeUserRequest;
import com.uccyou.ta.system.admin.request.ImportDataRequest;
import com.uccyou.ta.system.admin.request.NewAdminRequest;
import com.uccyou.ta.system.admin.request.SearchAllUserRequest;
import com.uccyou.ta.system.admin.response.AdminInfoResponse;
import com.uccyou.ta.system.admin.response.ChangeUserResponse;
import com.uccyou.ta.system.admin.response.SearchAllUserResponse;
import com.uccyou.ta.system.admin.response.UserRemoveResponse;

@Service
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class AdminService {

    private AdminRepository adminRepository;

    @Inject
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public int importData(ImportDataRequest request, HttpServletRequest req) {
        return adminRepository.importData(request, req);
    }

    @Transactional
    public Integer add(NewAdminRequest admin) {
        return adminRepository.add(admin);
    }

    public List<AdminInfoResponse> admin() {
        return adminRepository.admin();
    }

    @Transactional
    public boolean change(AdminStatusRequest request) {
        return adminRepository.change(request);
    }

    @Transactional
    public boolean remove(Integer adminId) {
        return adminRepository.remove(adminId);
    }

    @Transactional
    public boolean reset(Integer adminId) {
        return adminRepository.reset(adminId);
    }

    public PageModel<SearchAllUserResponse> allUser(SearchAllUserRequest request, Integer pageNo, Integer pageSize) {
        return adminRepository.allUser(request, pageNo, pageSize);
    }

    @Transactional
    public Boolean userAlive(Integer userId, String status) {
        return adminRepository.userAlive(userId, status);
    }

    @Transactional
    public Boolean userReset(Integer userId) {
        return adminRepository.userReset(userId);
    }

    public ChangeUserResponse userChange(Integer userId) {
        return adminRepository.userChange(userId);
    }

    @Transactional
    public Boolean userChange(ChangeUserRequest request) {
        return adminRepository.userChange(request);
    }

    @Transactional
    public UserRemoveResponse userRemove(Integer userId) {
        return adminRepository.userRemove(userId);
    }

    @Transactional
    public Integer userAdd(AddUserRequest request) {
        return adminRepository.userAdd(request);
    }

}
