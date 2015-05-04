package com.uccyou.ta.teacher.checking.repository;

import org.springframework.stereotype.Repository;

import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.teacher.checking.request.CheckingAdminRequest;
import com.uccyou.ta.teacher.checking.request.CheckingRandomRequest;
import com.uccyou.ta.teacher.checking.response.ClassStudentResponse;
import com.uccyou.ta.teacher.checking.web.request.CheckingRandomForm;

@Repository
public class CheckingRepository extends AbstractRepository {

    public PageModel<ClassStudentResponse> findAllStudentsByClassId(Integer classId, Integer pageNo, Integer pageSize) {
        return uccyouClientApi.get(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.TEACHER).action("/teacher/checking/all/%s/%s/%s").appKey(appKey).arguments(classId, pageNo, pageSize));
    }

    public PageModel<ClassStudentResponse> checkingAdmin(Integer adminClassId, Integer classId, int pageNo, int pageSize) {
        CheckingAdminRequest request = new CheckingAdminRequest();
        request.setAdminClassId(adminClassId);
        request.setClassId(classId);
        String bodyContent = JSONBinder.binder(CheckingAdminRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.TEACHER).action("/teacher/checking/admin/%s/%s").appKey(appKey).body(bodyContent).arguments(pageNo, pageSize));
    }

    public PageModel<ClassStudentResponse> checkingRandom(CheckingRandomForm form) {
        CheckingRandomRequest request = new CheckingRandomRequest();
        request.setClassId(form.getClassId());
        request.setCount(form.getCount());
        String bodyContent = JSONBinder.binder(CheckingRandomRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.TEACHER).action("/teacher/checking/random").appKey(appKey).body(bodyContent));
    }

    public PageModel<ClassStudentResponse> randomPaging(Integer classId, Integer pageNo, Integer pageSize) {
        return uccyouClientApi.get(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.TEACHER).action("/teacher/checking/random/%s/%s/%s").appKey(appKey).arguments(classId, pageNo, pageSize));
    }

    public boolean checkingClose(Integer classId) {
        return uccyouClientApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/checking/close/%s").appKey(appKey).arguments(classId));
    }

    public boolean absent(Integer classId, Integer studentId) {
        return uccyouClientApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/checking/absent/%s/%s").appKey(appKey).arguments(classId, studentId));
    }

    public boolean note(Integer classId, Integer studentId) {
        return uccyouClientApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/checking/note/%s/%s").appKey(appKey).arguments(classId, studentId));
    }

}
