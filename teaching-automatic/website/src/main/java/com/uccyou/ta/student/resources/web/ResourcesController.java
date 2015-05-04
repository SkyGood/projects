package com.uccyou.ta.student.resources.web;

import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.student.resources.response.SearchResponse;
import com.uccyou.ta.student.resources.service.ResourcesService;
import com.uccyou.ta.student.resources.web.request.SearchForm;

@Controller
public class ResourcesController {

    private ResourcesService resourcesService;

    @Inject
    public void setResourcesService(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }
    
    @RequestMapping(value = "/student/resources/search/{classId}", method = RequestMethod.GET) 
    public String search(Map<String, Object> map, HttpSession session, @PathVariable("classId") Integer classId) {
        SearchForm form = new SearchForm();
        form.setClassId(classId);
        PageModel<SearchResponse> pageModel = resourcesService.search(form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("res", form);
        session.setAttribute(SessionConstants.RESOURCE_SEARCH, form);
        return "website/student/resources/resources";
    }
    
    @RequestMapping(value = "/student/resources/search", method = RequestMethod.POST)
    public String search(SearchForm form, Map<String, Object> map, HttpSession session) {
        PageModel<SearchResponse> pageModel = resourcesService.search(form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("res", form);
        session.setAttribute(SessionConstants.RESOURCE_SEARCH, form);
        return "website/student/resources/resources";
    }
    
    @RequestMapping(value = "/student/resources/search/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String search(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, Map<String, Object> map, HttpSession session) {
        SearchForm form = (SearchForm) session.getAttribute(SessionConstants.RESOURCE_SEARCH);
        PageModel<SearchResponse> pageModel = resourcesService.search(form, pageNo, pageSize);
        map.put("pageModel", pageModel);
        map.put("res", form);
        return "website/student/resources/resources";
    }
    
}
