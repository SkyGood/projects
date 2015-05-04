package com.uccyou.ta.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.notice.repository.NoticeRepository;
import com.uccyou.ta.system.notice.request.NoticeAddRequest;
import com.uccyou.ta.system.notice.request.NoticeChangeRequest;
import com.uccyou.ta.system.notice.request.NoticeSearchRequest;
import com.uccyou.ta.system.notice.response.NoticeChangeResponse;
import com.uccyou.ta.system.notice.response.NoticeSearchResponse;
import com.uccyou.ta.system.notice.response.RecentlyNoticeResponse;

@Service
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class NoticeService {

    private NoticeRepository noticeRepository;

    @Inject
    public void setNoticeRepository(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }
    
    public PageModel<NoticeSearchResponse> notice(NoticeSearchRequest request, Integer pageNo, Integer pageSize) {
        return noticeRepository.notice(request, pageNo, pageSize);
    }

    @Transactional
    public Boolean add(NoticeAddRequest request) {
        return noticeRepository.add(request);
    }

    public NoticeChangeResponse change(Integer id) {
        return noticeRepository.change(id);
    }

    @Transactional
    public Boolean change(NoticeChangeRequest request) {
        return noticeRepository.change(request);
    }

    @Transactional
    public Boolean remove(Integer id) {
        return noticeRepository.remove(id);
    }

    public List<RecentlyNoticeResponse> recentNotice(String identity) {
        return noticeRepository.recentNotice(identity);
    }

}
