package com.uccyou.ta.system.notice.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.system.notice.request.NoticeAddRequest;
import com.uccyou.ta.system.notice.request.NoticeChangeRequest;
import com.uccyou.ta.system.notice.request.NoticeSearchRequest;
import com.uccyou.ta.system.notice.response.NoticeChangeResponse;
import com.uccyou.ta.system.notice.response.NoticeSearchResponse;
import com.uccyou.ta.system.notice.response.RecentlyNoticeResponse;
import com.uccyou.ta.system.notice.web.request.NoticeAddForm;
import com.uccyou.ta.system.notice.web.request.NoticeChangeForm;
import com.uccyou.ta.system.notice.web.request.NoticeSearchForm;

@Repository
public class NoticeRepository extends AbstractRepository {

    public PageModel<NoticeSearchResponse> notice(NoticeSearchForm form, Integer pageNo, Integer pageSize) {
        NoticeSearchRequest request = new NoticeSearchRequest();
        request.setAdminName(form.getAdminName());
        request.setReader(form.getReader());
        request.setTitle(form.getTitle());
        String bodyContent = JSONBinder.binder(NoticeSearchRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.SYSTEM).action("/admin/notice/%s/%s").appKey(appKey).body(bodyContent).arguments(pageNo, pageSize));
    }

    public Boolean add(NoticeAddForm form) {
        NoticeAddRequest request = new NoticeAddRequest();
        request.setAdminId(form.getAdminId());
        request.setTitle(form.getTitle());
        request.setReader(form.getReader());
        request.setContent(form.getContent());
        String bodyContent = JSONBinder.binder(NoticeAddRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/notice/add").appKey(appKey).body(bodyContent));
    }

    public NoticeChangeResponse change(Integer id) {
        return uccyouClientApi.get(EndPointBuilder.create(NoticeChangeResponse.class).endpoint(EndPoint.SYSTEM).action("/notice/change/%s").appKey(appKey).arguments(id));
    }

    public Boolean change(NoticeChangeForm form) {
        NoticeChangeRequest request = new NoticeChangeRequest();
        request.setId(form.getId());
        request.setTitle(form.getTitle());
        request.setReader(form.getReader());
        request.setContent(form.getContent());
        String bodyContent = JSONBinder.binder(NoticeChangeRequest.class).toJSON(request);
        return uccyouClientApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/notice/change").appKey(appKey).body(bodyContent));
    }

    public Boolean remove(Integer id) {
        return uccyouClientApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SYSTEM).action("/notice/remove/%s").appKey(appKey).arguments(id));
    }

    public List<RecentlyNoticeResponse> recentNotice(String identity) {
        return uccyouClientApi.get(EndPointBuilder.create(List.class).endpoint(EndPoint.SYSTEM).action("/notice/recent/%s").appKey(appKey).arguments(identity));
    }
}
