package com.uccyou.ta.system.admin.repository;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import com.uccyou.core.io.FileTransfer;
import com.uccyou.core.io.FileUpload;
import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.core.util.StringUtils;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.system.admin.request.AddUserRequest;
import com.uccyou.ta.system.admin.request.ChangeUserRequest;
import com.uccyou.ta.system.admin.request.ImportDataRequest;
import com.uccyou.ta.system.admin.request.SearchAllUserRequest;
import com.uccyou.ta.system.admin.response.ChangeUserResponse;
import com.uccyou.ta.system.admin.response.SearchAllUserResponse;
import com.uccyou.ta.system.admin.response.UserRemoveResponse;
import com.uccyou.ta.system.admin.web.request.AddUserForm;
import com.uccyou.ta.system.admin.web.request.AdminStatusForm;
import com.uccyou.ta.system.admin.web.request.ChangeUserForm;
import com.uccyou.ta.system.admin.web.request.ImportDataForm;
import com.uccyou.ta.system.admin.web.request.NewAdminForm;
import com.uccyou.ta.system.admin.web.request.SearchAllUserForm;
import com.uccyou.ta.system.admin.web.response.AdminInfoForm;

@Repository
public class AdminRepository extends AbstractRepository {

    private static Logger logger = LoggerFactory.getLogger(AdminRepository.class);
    
    public int importData(ImportDataForm form, MultipartFile file, HttpServletRequest req) {
        List<String> storePaths = new ArrayList<String>();
        try {
            storePaths = FileUpload.uploadFiles(form.getAdminId(), req, file);
        } catch (Exception e) {
            logger.info("upload excel file ={} failed, exception = {}", new Object[]{file.getOriginalFilename(), e.getStackTrace()});
            return -1;
        }
        String filePath = storePaths.get(0);
        ImportDataRequest request = new ImportDataRequest();
        request.setIdentity(form.getIdentity());
        request.setFilePath(filePath);
        String bodyContent = JSONBinder.binder(ImportDataRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(Integer.class).endpoint(EndPoint.SYSTEM).action("/importdata").appKey(appKey).body(bodyContent));
    }

    public Integer add(NewAdminForm admin) {
        String bodyContent = JSONBinder.binder(NewAdminForm.class).toJSON(admin);
        return uccyouClientApi.post(EndPointBuilder.create(Integer.class).endpoint(EndPoint.SYSTEM).action("/admin/add").appKey(appKey).body(bodyContent));
    }

    public List<AdminInfoForm> admin() {
        return uccyouClientApi.get(EndPointBuilder.create(List.class).endpoint(EndPoint.SYSTEM).action("/admin/admin").appKey(appKey));
    }

    public boolean change(Integer adminId, String status) {
        AdminStatusForm adminStatusForm = new AdminStatusForm();
        adminStatusForm.setAdmingId(adminId);
        adminStatusForm.setStatus(status);
        String bodyContent = JSONBinder.binder(AdminStatusForm.class).toJSON(adminStatusForm);
        return uccyouClientApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/admin/change").appKey(appKey).body(bodyContent));
    }

    public boolean remove(Integer adminId) {
        return uccyouClientApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/admin/remove/%s").appKey(appKey).arguments(adminId));
    }
    public boolean reset(Integer adminId) {
        return uccyouClientApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/admin/reset/%s").appKey(appKey).arguments(adminId));
    }

    public PageModel<SearchAllUserResponse> allUser(SearchAllUserForm form, Integer pageNo, Integer pageSize) {
        SearchAllUserRequest request = new SearchAllUserRequest();
        request.setName(form.getName());
        request.setIdentityCode(form.getIdentityCode());
        request.setIdentity(form.getIdentity());
        request.setPhone(form.getPhone());
        request.setEmail(form.getEmail());
        request.setQq(form.getQq());
        request.setAlive(form.getAlive());
        String bodyContent = JSONBinder.binder(SearchAllUserRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.SYSTEM).action("/admin/user/search/%s/%s").appKey(appKey).arguments(pageNo, pageSize).body(bodyContent));
    }

    public Boolean userAlive(Integer userId, String status) {
        return uccyouClientApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/admin/user/alive/%s/%s").appKey(appKey).arguments(userId, status));
    }

    public Boolean userRest(Integer userId) {
        return uccyouClientApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/admin/user/reset/%s").appKey(appKey).arguments(userId));
    }

    public ChangeUserResponse userChange(Integer userId) {
        return uccyouClientApi.get(EndPointBuilder.create(ChangeUserResponse.class).endpoint(EndPoint.SYSTEM).action("/admin/user/change/%s").appKey(appKey).arguments(userId));
    }

    public Boolean userChange(ChangeUserForm form) {
        ChangeUserRequest request = new ChangeUserRequest();
        request.setId(form.getId());
        request.setIdentityCode(form.getIdentityCode());
        request.setName(form.getName());
        request.setIdentity(form.getIdentity());
        request.setPhone(form.getPhone());
        request.setEmail(form.getEmail());
        request.setQq(form.getQq());
        String bodyContent = JSONBinder.binder(ChangeUserRequest.class).toJSON(request);
        return uccyouClientApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/admin/user/change").appKey(appKey).body(bodyContent));
    }

    public Boolean userRemove(HttpServletRequest request, Integer userId) {
        UserRemoveResponse response =  uccyouClientApi.delete(EndPointBuilder.create(UserRemoveResponse.class).endpoint(EndPoint.SYSTEM).action("/admin/user/remove/%s").appKey(appKey).arguments(userId));
        if (!response.getFlag()) return false;
        for ( String resLocation : response.getResLocation() ) {
            if (StringUtils.hasText(resLocation)) {
                try {
                    FileTransfer.removeAbsoluteFile(resLocation, request);
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return true;
    }

    public Integer useAdd(AddUserForm form) {
        AddUserRequest request = new AddUserRequest();
        request.setIdentityCode(form.getIdentityCode());
        request.setName(form.getName());
        request.setEmail(form.getEmail());
        request.setIdentity(form.getIdentity());
        request.setPhone(form.getPhone());
        request.setQq(form.getQq());
        String bodyContent = JSONBinder.binder(AddUserRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(Integer.class).endpoint(EndPoint.SYSTEM).action("/admin/user/add").appKey(appKey).body(bodyContent));
    }
}
