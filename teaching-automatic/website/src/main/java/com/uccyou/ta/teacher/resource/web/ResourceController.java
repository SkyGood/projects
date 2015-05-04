package com.uccyou.ta.teacher.resource.web;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.commons.constants.SessionConstants;
import com.uccyou.ta.teacher.resource.response.AllResourceResponse;
import com.uccyou.ta.teacher.resource.response.ResourceChangeResponse;
import com.uccyou.ta.teacher.resource.response.ResourceSearchResponse;
import com.uccyou.ta.teacher.resource.service.ResourceService;
import com.uccyou.ta.teacher.resource.web.request.ResourceChangeForm;
import com.uccyou.ta.teacher.resource.web.request.ResourceReferenceForm;
import com.uccyou.ta.teacher.resource.web.request.ResourceSearchForm;
import com.uccyou.ta.teacher.resource.web.request.UploadForm;

@Controller
public class ResourceController {
    
    private ResourceService resourceService;
    @Inject
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
    
    @RequestMapping(value = "/teacher/resource/upload/{classId}", method = RequestMethod.GET)
    public String upload(@PathVariable("classId") Integer classId, HttpSession session, Map<String, Object> map) {
        Integer teacherId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        map.put("teacherId", teacherId);
        map.put("classId", classId);
        return "website/teacher/resource/addresource";
    }
    
    @RequestMapping(value = "/teacher/resource/upload", method = RequestMethod.POST)
    public String upload(UploadForm form, @RequestParam("res") MultipartFile file, HttpServletRequest request, Map<String, Object> map) {
        Boolean flag = resourceService.upload(form, file, request);
        if (flag) {
            return "redirect:/teacher/resource/search/" + form.getClassId();
        }
        map.put("resName", form.getResName());
        return "redirect:/teacher/resource/upload/" + form.getClassId();
    }
    
    /*无条件搜索第一页：GET*/
    @RequestMapping(value = "/teacher/resource/search/{classId}", method = RequestMethod.GET)
    public String search(@PathVariable("classId") Integer classId, HttpSession session, Map<String, Object> map) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        session.setAttribute(SessionConstants.RESOURCE_SEARCH, null);
        PageModel<ResourceSearchResponse> pageModel = resourceService.search(userId, classId, null, 1, 10);
        map.put("pageModel", pageModel);
        map.put("classId", classId);
        return "website/teacher/resource/resources";
    }
    /*条件搜索第一页：POST*/
    @RequestMapping(value = "/teacher/resource/search/{classId}", method = RequestMethod.POST)
    public String search(@PathVariable("classId") Integer classId, ResourceSearchForm form, HttpSession session , Map<String, Object> map) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        session.setAttribute(SessionConstants.RESOURCE_SEARCH, form);
        PageModel<ResourceSearchResponse> pageModel = resourceService.search(userId, classId, form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("classId", classId);
        map.put("res", form);
        return "website/teacher/resource/resources";
    }
    /*第二页++GET*/
    @RequestMapping(value = "/teacher/resource/search/{classId}/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String search(@PathVariable("classId") Integer classId, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, HttpSession session, Map<String, Object> map) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        ResourceSearchForm form = (ResourceSearchForm) session.getAttribute(SessionConstants.RESOURCE_SEARCH);
        PageModel<ResourceSearchResponse> pageModel = resourceService.search(userId, classId, form, pageNo, pageSize);
        map.put("pageModel", pageModel);
        map.put("res", form);
        return "website/teacher/resource/resources";
    }
    
    @RequestMapping(value = "/teacher/resource/alive/{resId}/{status}", method = RequestMethod.GET)
    @ResponseBody
    public boolean alive(@PathVariable("resId") Integer resId, @PathVariable("status") String status) {
        return resourceService.alive(resId, status);
    }
    
    @RequestMapping(value = "/teacher/resource/change/{classId}/{resId}", method = RequestMethod.GET)
    public String change(@PathVariable("classId") Integer classId, @PathVariable("resId") Integer resId, Map<String, Object> map) {
        ResourceChangeResponse response = resourceService.find(resId);
        map.put("resName", response.getResName());
        map.put("resId", resId);
        map.put("classId", classId);
        return "website/teacher/resource/changeresource";
    }
    
    @RequestMapping(value = "/teacher/resource/change/{classId}", method = RequestMethod.POST) 
    public String change(@PathVariable("classId") Integer classId, ResourceChangeForm form) {
        resourceService.change(form);
        return "redirect:/teacher/resource/search/" + classId;
    }
    
    @RequestMapping(value = "/teacher/resource/reference/{classId}", method = RequestMethod.GET)
    public String reference(@PathVariable("classId") Integer classId, HttpSession session, Map<String, Object> map) {
        Integer userId = (Integer) session.getAttribute(SessionConstants.USER_ID);
        ResourceReferenceForm form = new ResourceReferenceForm();
        form.setTeacherId(userId);
        form.setClassId(classId);
        session.setAttribute(SessionConstants.REFERENCE_RESOURCES_SEARCH, form);
        PageModel<AllResourceResponse> pageModel = resourceService.reference(form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("res", form);
        return "website/teacher/resource/referenceresource";
    }
    
    @RequestMapping(value = "/teacher/resource/reference", method = RequestMethod.POST)
    public String reference(ResourceReferenceForm form, HttpSession session, Map<String, Object> map) {
        session.setAttribute(SessionConstants.REFERENCE_RESOURCES_SEARCH, form);
        PageModel<AllResourceResponse> pageModel = resourceService.reference(form, 1, 10);
        map.put("pageModel", pageModel);
        map.put("res", form);
        return "website/teacher/resource/referenceresource";
    }
    
    @RequestMapping(value = "/teacher/resource/reference/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String reference(HttpSession session, Map<String, Object> map, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        ResourceReferenceForm form = (ResourceReferenceForm) session.getAttribute(SessionConstants.REFERENCE_RESOURCES_SEARCH);
        PageModel<AllResourceResponse> pageModel = resourceService.reference(form, pageNo, pageSize);
        map.put("pageModel", pageModel);
        map.put("res", form);
        return "website/teacher/resource/referenceresource";
    }
    
    @RequestMapping(value = "/teacher/resource/quote/{classId}/{resId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean reference(@PathVariable("classId") Integer classId, @PathVariable("resId") Integer resId) {
        return resourceService.quote(classId, resId);
    }
    
    @RequestMapping(value = "/teacher/resource/remove/{classId}/{resId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean remove(@PathVariable("classId") Integer classId, @PathVariable("resId") Integer resId, HttpServletRequest request) {
        return resourceService.remove(classId, resId, request);
    }
}
