package com.uccyou.ta.student.resources.repository;

import org.springframework.stereotype.Repository;

import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.student.resources.request.SearchRequest;
import com.uccyou.ta.student.resources.response.SearchResponse;
import com.uccyou.ta.student.resources.web.request.SearchForm;

@Repository
public class ResourcesRepository extends AbstractRepository {

    public PageModel<SearchResponse> search(SearchForm form, int pageNo, int pageSize) {
        SearchRequest request = new SearchRequest();
        request.setClassId(form.getClassId());
        request.setResName(form.getResName());
        String bodyContent = JSONBinder.binder(SearchRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.STUDENT).action("/student/resources/search/%s/%s").appKey(appKey).body(bodyContent).arguments(pageNo, pageSize));
    }
}
