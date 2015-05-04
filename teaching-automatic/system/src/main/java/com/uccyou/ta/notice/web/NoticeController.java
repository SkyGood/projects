package com.uccyou.ta.notice.web;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.notice.service.NoticeService;
import com.uccyou.ta.system.notice.request.NoticeAddRequest;
import com.uccyou.ta.system.notice.request.NoticeChangeRequest;
import com.uccyou.ta.system.notice.request.NoticeSearchRequest;
import com.uccyou.ta.system.notice.response.NoticeChangeResponse;
import com.uccyou.ta.system.notice.response.NoticeSearchResponse;
import com.uccyou.ta.system.notice.response.RecentlyNoticeResponse;

@RestController
public class NoticeController {

    private NoticeService noticeService;

    @Inject
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @RequestMapping(value = "/admin/notice/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<NoticeSearchResponse> notice(@RequestBody NoticeSearchRequest request, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return noticeService.notice(request, pageNo, pageSize);
    }

    @RequestMapping(value = "/notice/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean add(@RequestBody NoticeAddRequest request) {
        return noticeService.add(request);
    }
    
    @RequestMapping(value = "/notice/change/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public NoticeChangeResponse change(@PathVariable("id") Integer id) {
        return noticeService.change(id);
    }
    
    @RequestMapping(value = "/notice/change", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean change(@RequestBody NoticeChangeRequest request) {
        return noticeService.change(request);
    }
    
    @RequestMapping(value = "/notice/remove/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean remove(@PathVariable("id") Integer id) {
        return noticeService.remove(id);
    }
    
    @RequestMapping(value = "/notice/recent/{identity}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RecentlyNoticeResponse> recentNotice(@PathVariable("identity") String identity) {
        return noticeService.recentNotice(identity);
    }
}
