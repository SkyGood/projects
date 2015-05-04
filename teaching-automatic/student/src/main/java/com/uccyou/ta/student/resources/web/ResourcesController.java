package com.uccyou.ta.student.resources.web;

import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.student.resources.request.SearchRequest;
import com.uccyou.ta.student.resources.response.SearchResponse;
import com.uccyou.ta.student.resources.service.ResourcesService;

@RestController
public class ResourcesController {
    
    private ResourcesService resourcesService;
    @Inject
    public void setResourcesService(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }
    
    @RequestMapping(value = "/student/resources/search/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<SearchResponse> search(@RequestBody SearchRequest request, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return resourcesService.search(request, pageNo, pageSize);
    }
    
}
