package com.uccyou.ta.system.admin.service;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.system.admin.repository.AdminRepository;
import com.uccyou.ta.system.admin.response.ChangeUserResponse;
import com.uccyou.ta.system.admin.response.SearchAllUserResponse;
import com.uccyou.ta.system.admin.web.request.AddUserForm;
import com.uccyou.ta.system.admin.web.request.ChangeUserForm;
import com.uccyou.ta.system.admin.web.request.ImportDataForm;
import com.uccyou.ta.system.admin.web.request.NewAdminForm;
import com.uccyou.ta.system.admin.web.request.SearchAllUserForm;
import com.uccyou.ta.system.admin.web.response.AdminInfoForm;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    @Inject
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public int importData(ImportDataForm form, MultipartFile file, HttpServletRequest req) {
        return adminRepository.importData(form, file, req);
    }

    @CacheEvict(value = "defaultCache", key = "'admins'")
    public Integer add(NewAdminForm admin) {
        return adminRepository.add(admin);
    }

    @Cacheable(value = "defaultCache", key = "'admins'")
    public List<AdminInfoForm> admin() {
        return adminRepository.admin();
    }

    @CacheEvict(value = "defaultCache", key = "'admins'")
    public boolean change(Integer adminId, String status) {
        return adminRepository.change(adminId, status);
    }

    @CacheEvict(value = "defaultCache", key = "'admins'")
    public boolean remove(Integer adminId) {
        return adminRepository.remove(adminId);
    }

    @CacheEvict(value = "defaultCache", key = "'admins'")
    public boolean reset(Integer adminId) {
        return adminRepository.reset(adminId);
    }

    public PageModel<SearchAllUserResponse> allUser(SearchAllUserForm form, Integer pageNo, Integer pageSize) {
        return adminRepository.allUser(form, pageNo, pageSize);
    }

    public Boolean userAlive(Integer userId, String status) {
        return adminRepository.userAlive(userId, status);
    }

    public Boolean userReset(Integer userId) {
        return adminRepository.userRest(userId);
    }

    public ChangeUserResponse userChange(Integer userId) {
        return adminRepository.userChange(userId);
    }

    public Boolean userChange(ChangeUserForm form) {
        return adminRepository.userChange(form);
    }

    public Boolean userRemove(HttpServletRequest request, Integer userId) {
        return  adminRepository.userRemove(request, userId);
    }

    public Integer userAdd(AddUserForm form) {
        return adminRepository.useAdd(form);
    }

}
