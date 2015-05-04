package com.uccyou.ta.student.subject.repository;

import org.springframework.stereotype.Repository;

import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.student.subject.request.SubjectRequest;
import com.uccyou.ta.student.subject.response.SubjectResponse;
import com.uccyou.ta.student.subject.web.request.SubjectForm;

@Repository
public class SubjectRepository extends AbstractRepository {

    public PageModel<SubjectResponse> search(SubjectForm form, int pageNo, int pageSize) {
        SubjectRequest request = new SubjectRequest();
        request.setIdentityCode(form.getIdentityCode());
        request.setCourseType(form.getCourseType());
        request.setCourseName(form.getCourseName());
        String bodyContent = JSONBinder.binder(SubjectRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.STUDENT).action("/student/subject/search/%s/%s").appKey(appKey).body(bodyContent).arguments(pageNo, pageSize));
    }
    
}
