package com.uccyou.ta.system.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.system.notice.repository.NoticeRepository;
import com.uccyou.ta.system.notice.response.NoticeChangeResponse;
import com.uccyou.ta.system.notice.response.NoticeSearchResponse;
import com.uccyou.ta.system.notice.response.RecentlyNoticeResponse;
import com.uccyou.ta.system.notice.web.request.NoticeAddForm;
import com.uccyou.ta.system.notice.web.request.NoticeChangeForm;
import com.uccyou.ta.system.notice.web.request.NoticeSearchForm;

@Service
public class NoticeService {
    
    private NoticeRepository noticeRepository;

    @Inject
    public void setNoticeRepository(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public PageModel<NoticeSearchResponse> notice(NoticeSearchForm form, Integer pageNo, Integer pageSize) {
        return noticeRepository.notice(form, pageNo, pageSize);
    }

    @CacheEvict(value = "defaultCache", key = "#form.reader + 'notice'")
    public Boolean add(NoticeAddForm form) {
        return noticeRepository.add(form);
    }
    
    public NoticeChangeResponse change(Integer id) {
        return noticeRepository.change(id);
    }

    @CacheEvict(value = "defaultCache", key = "#form.reader + 'notice'")
    public Boolean change(NoticeChangeForm form) {
        return noticeRepository.change(form);
    }

    @CacheEvict(value = "defaultCache", key = "#reader + 'notice'")
    public Boolean remove(Integer id, String reader) {
        return noticeRepository.remove(id);
    }
    
    @Cacheable(value = "defaultCache", key = "#identity + 'notice'")
    public List<RecentlyNoticeResponse> recentNotice(String identity) {
        return noticeRepository.recentNotice(identity);
    }

    
}
