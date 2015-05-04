package com.uccyou.ta.teacher.teaching.adminclass.repository;

import org.springframework.stereotype.Repository;

import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.core.util.StringUtils;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.teacher.checking.response.AdminClassNameResponse;
import com.uccyou.ta.teacher.teaching.admin.request.AddAdminClassRequest;
import com.uccyou.ta.teacher.teaching.admin.request.ChangeAdminClassNameRequest;
import com.uccyou.ta.teacher.teaching.adminclass.web.request.AddAdminClassForm;
import com.uccyou.ta.teacher.teaching.adminclass.web.request.ChangeAdminClassNameForm;

@Repository
public class AdminClassRepository extends AbstractRepository {

    public Integer add(AddAdminClassForm form) {
        if (!StringUtils.hasText(form.getAdminClassName())) {
            return 0;
        }
        AddAdminClassRequest request = new AddAdminClassRequest();
        request.setTeachingClassId(form.getTeachingClassId());
        request.setAdminClassName(form.getAdminClassName());
        String bodyContent  = JSONBinder.binder(AddAdminClassRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(Integer.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/admin/add").appKey(appKey).body(bodyContent));
    }

    public Boolean change(ChangeAdminClassNameForm form) {
        ChangeAdminClassNameRequest request = new ChangeAdminClassNameRequest();
        request.setAdminClassId(form.getAdminClassId());
        request.setAdminClassName(form.getAdminClassName());
        String bodyContent = JSONBinder.binder(ChangeAdminClassNameRequest.class).toJSON(request);
        return uccyouClientApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/admin/change").appKey(appKey).body(bodyContent));
    }

    public Boolean remove(Integer adminClassId) {
        return uccyouClientApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/admin/remove/%s").appKey(appKey).arguments(adminClassId));
    }

    public Boolean clean(Integer adminClassId) {
        return uccyouClientApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/admin/clean/%s").appKey(appKey).arguments(adminClassId));
    }

    public AdminClassNameResponse findAdminClassNameById(Integer adminClassId) {
        
        return uccyouClientApi.get(EndPointBuilder.create(AdminClassNameResponse.class).endpoint(EndPoint.TEACHER).action("/teacher/calss/admin/findadminclassnamebyid/%s").appKey(appKey).arguments(adminClassId));
    }
}
