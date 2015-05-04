package com.uccyou.ta.teacher.resources.web;

import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.resource.request.ResourceChangeRequest;
import com.uccyou.ta.teacher.resource.request.ResourceReferenceRequest;
import com.uccyou.ta.teacher.resource.request.ResourceSearchRequest;
import com.uccyou.ta.teacher.resource.request.UploadRequest;
import com.uccyou.ta.teacher.resource.response.AllResourceResponse;
import com.uccyou.ta.teacher.resource.response.ResourceChangeResponse;
import com.uccyou.ta.teacher.resource.response.ResourceRemoveResponse;
import com.uccyou.ta.teacher.resource.response.ResourceSearchResponse;
import com.uccyou.ta.teacher.resources.service.ResourceService;

@RestController
public class ResourceController {
    private ResourceService resourceService;
    @Inject
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
    
    @RequestMapping(value = "/teacher/resource/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean upload(@RequestBody UploadRequest request) {
        return resourceService.upload(request);
    }
    
    @RequestMapping(value = "/teacher/resource/alive/{resId}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean alive(@PathVariable("resId") Integer resId, @PathVariable("status") String status) {
        return resourceService.alive(resId, status);
    }
    
    @RequestMapping(value = "/teacher/resource/search/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<ResourceSearchResponse> search(@RequestBody ResourceSearchRequest request, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return resourceService.search(request, pageNo, pageSize);
    }
    
    @RequestMapping(value = "/teacher/resource/find/{resId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResourceChangeResponse find(@PathVariable("resId") Integer resId) {
        return resourceService.find(resId);
    }
    
    @RequestMapping(value = "/teacher/resource/change", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean change(@RequestBody ResourceChangeRequest request) {
        return resourceService.change(request);
    }
    
    @RequestMapping(value = "/teacher/resource/reference/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<AllResourceResponse> reference(@RequestBody ResourceReferenceRequest request, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return resourceService.reference(request, pageNo, pageSize);
    }
    @RequestMapping(value = "/teacher/resource/quote/{classId}/{resId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean reference(@PathVariable("classId") Integer classId, @PathVariable("resId") Integer resId) {
        return resourceService.reference(classId, resId);
    }
    
    @RequestMapping(value = "/teacher/resource/alive/{classId}/{resId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResourceRemoveResponse remove(@PathVariable("classId") Integer classId, @PathVariable("resId") Integer resId) {
        return resourceService.remove(classId, resId);
    }
}
