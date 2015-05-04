package com.uccyou.ta.system.system.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.system.notice.response.RecentlyNoticeResponse;
import com.uccyou.ta.system.notice.service.NoticeService;
import com.uccyou.ta.system.system.service.SystemService;

@Controller
public class SystemController {

    private SystemService systemService;
    
    private NoticeService noticeService;

    @RequestMapping(value = "/system/homepage", method = RequestMethod.GET)
    public String homepage(HttpSession session, Map<String, Object> map) {
        String name = (String) session.getAttribute(SessionConstants.USER_NAME);
        String identityCode = (String) session.getAttribute(SessionConstants.IDENTITY_CODE);
        String identity = (String) session.getAttribute(SessionConstants.IDENTITY);
        List<RecentlyNoticeResponse> notice = noticeService.recentNotice(identity);
        map.put("name", name);
        map.put("identityCode", identityCode);
        map.put("identity", identity);
        map.put("notice", notice);
        return "website/system/homepage";
    }
    
    @RequestMapping(value = "/404")
    public String notFound() {
        return "404";
    }
    
    @RequestMapping(value = "/500")
    public String serverError() {
        return "500";
    }
    
    @Inject
    public void setSystemService(SystemService systemService) {
        this.systemService = systemService;
    }

    @Inject
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }
}
