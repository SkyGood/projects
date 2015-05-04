package com.uccyou.ta.system.notice.web;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.system.notice.response.NoticeChangeResponse;
import com.uccyou.ta.system.notice.response.NoticeSearchResponse;
import com.uccyou.ta.system.notice.service.NoticeService;
import com.uccyou.ta.system.notice.web.request.NoticeAddForm;
import com.uccyou.ta.system.notice.web.request.NoticeChangeForm;
import com.uccyou.ta.system.notice.web.request.NoticeSearchForm;

@Controller
public class NoticeController {

    private NoticeService noticeService;

    @Inject
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public String notice(HttpSession session, Map<String, Object> map) {
        Integer adminId = (Integer) session.getAttribute(SessionConstants.ADMIN_ID);
        NoticeSearchForm form = new NoticeSearchForm();
        PageModel<NoticeSearchResponse> pageModel = noticeService.notice(form, 1, 10);
        session.setAttribute(SessionConstants.ADMIN_NOTICE_SEARCH, form);
        map.put("pageModel", pageModel);
        return "website/system/systemnotice";
    }

    @RequestMapping(value = "/notice/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String notice(HttpSession session, Map<String, Object> map, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        NoticeSearchForm form = (NoticeSearchForm) session.getAttribute(SessionConstants.ADMIN_NOTICE_SEARCH);
        PageModel<NoticeSearchResponse> pageModel = noticeService.notice(form, pageNo, pageSize);
        map.put("pageModel", pageModel);
        return "website/system/systemnotice";
    }

    @RequestMapping(value = "/notice", method = RequestMethod.POST)
    public String notice(HttpSession session, NoticeSearchForm form, Map<String, Object> map) {
        PageModel<NoticeSearchResponse> pageModel = noticeService.notice(form, 1, 10);
        session.setAttribute(SessionConstants.ADMIN_NOTICE_SEARCH, form);
        map.put("pageModel", pageModel);
        return "website/system/systemnotice";
    }

    @RequestMapping(value = "/notice/add", method = RequestMethod.GET)
    public String add(Map<String, Object> map, HttpSession session) {
        Integer adminId = (Integer) session.getAttribute(SessionConstants.ADMIN_ID);
        map.put("adminId", adminId);
        return "website/system/addnotice";
    }

    @RequestMapping(value = "/notice/add", method = RequestMethod.POST)
    public String add(NoticeAddForm form, HttpSession session, Map<String, Object> map) {
        Boolean flag = noticeService.add(form);
        if (!flag) {
            String tip = "添加失败!";
            map.put("tip", tip);
            return "website/system/addnotice";
        }
        return "redirect:/notice";
    }

    @RequestMapping(value = "/notice/change/{id}", method = RequestMethod.GET)
    public String change(@PathVariable("id") Integer id,  @PathVariable("reader") String reader, Map<String, Object> map) {
        NoticeChangeResponse response = noticeService.change(id);
        map.put("id", id);
        map.put("notice", response);
        return "website/system/changenotice";
    }

    @RequestMapping(value = "/notice/change", method = RequestMethod.POST)
    public String change(NoticeChangeForm form, Map<String, Object> map) {
        Boolean flag = noticeService.change(form);
        if (!flag) {
            String tip = "修改留言失败！";
            map.put("tip", tip);
            return "website/system/changenotice";
        }
        return "redirect:/notice";
    }
    
    @RequestMapping(value = "/notice/remove/{id}/{reader}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") Integer id, @PathVariable("reader") String reader) {
        noticeService.remove(id, reader);
        return "redirect:/notice";
    }
}
