package com.uccyou.ta.teacher.teaching.teaching.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.teacher.teaching.admin.response.AdminClassResponse;
import com.uccyou.ta.teacher.teaching.request.CreateTeachingClassRequest;
import com.uccyou.ta.teacher.teaching.request.TeachingClassSearchRequest;
import com.uccyou.ta.teacher.teaching.request.UpdateTeachingClassRequest;
import com.uccyou.ta.teacher.teaching.response.TeachingClassResponse;
import com.uccyou.ta.teacher.teaching.teaching.web.request.CreateTeachingClassForm;
import com.uccyou.ta.teacher.teaching.teaching.web.request.TeachingClassSearchForm;
import com.uccyou.ta.teacher.teaching.teaching.web.request.UpdateTeachingClassForm;

@Repository
public class TeachingRepository extends AbstractRepository {

    public PageModel<TeachingClassResponse> clazz(Integer userId, TeachingClassSearchForm form, Integer pageNo, Integer pageSize) {
        TeachingClassSearchRequest request = new TeachingClassSearchRequest();
        request.setUserId(userId);
        if (form != null) {
            request.setClassName(form.getClassName());
            request.setStartWeek(form.getStartWeek());
            request.setEndWeek(form.getEndWeek());
            request.setCourseName(form.getCourseName());
            request.setCourseType(form.getCourseType());
            request.setClassRoom(form.getClassRoom());
        }
        String bodyContent = JSONBinder.binder(TeachingClassSearchRequest.class).toJSON(request);
        PageModel<TeachingClassResponse> pageModel = uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/%s/%s").appKey(appKey).body(bodyContent).arguments(pageNo, pageSize));
        return pageModel;
    }

    public int create(CreateTeachingClassForm form) {
        CreateTeachingClassRequest request = new CreateTeachingClassRequest();
        request.setUserId(form.getUserId());
        request.setClassName(form.getClassName());
        request.setClassRoom(form.getClassRoom());
        request.setCourseName(form.getCourseName());
        request.setCourseType(form.getCourseType());
        request.setEndWeek(form.getEndWeek());
        request.setStartWeek(form.getStartWeek());
        request.setCredit(form.getCredit());
        request.setTeachingTime(form.getTeachingTime());
        request.setIdentityCode(form.getIdentityCode());
        String bodyContent = JSONBinder.binder(CreateTeachingClassRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(Integer.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/add").appKey(appKey).body(bodyContent));
    }

    public TeachingClassResponse findClassById(Integer classId) {
        return uccyouClientApi.get(EndPointBuilder.create(TeachingClassResponse.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/change/%s").appKey(appKey).arguments(classId));
    }

    public boolean update(UpdateTeachingClassForm form) {
        UpdateTeachingClassRequest request = new UpdateTeachingClassRequest();
        request.setClassId(form.getClassId());
        request.setClassName(form.getClassName());
        request.setClassRoom(form.getClassRoom());
        request.setCourseName(form.getCourseName());
        request.setCourseType(form.getCourseType());
        request.setCredit(form.getCredit());
        request.setEndWeek(form.getEndWeek());
        request.setTeachingTime(form.getTeachingTime());
        request.setNotice(form.getNotice());
        request.setStartWeek(form.getStartWeek());
        String bodyContent = JSONBinder.binder(UpdateTeachingClassRequest.class).toJSON(request); 
        return uccyouClientApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/change").appKey(appKey).body(bodyContent));
    }

    public List<AdminClassResponse> findAdminClass(Integer classId) {
        return uccyouClientApi.get(EndPointBuilder.create(List.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/manage/%s").appKey(appKey).arguments(classId));
    }

    public boolean remove(Integer classId) {
        return uccyouClientApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/remove/%s").appKey(appKey).arguments(classId));
    }

}
