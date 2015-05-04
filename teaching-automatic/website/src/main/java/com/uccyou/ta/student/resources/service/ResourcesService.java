package com.uccyou.ta.student.resources.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import com.uccyou.core.page.PageModel;
import com.uccyou.ta.student.resources.repository.ResourcesRepository;
import com.uccyou.ta.student.resources.response.SearchResponse;
import com.uccyou.ta.student.resources.web.request.SearchForm;

@Service
public class ResourcesService {

    private ResourcesRepository resourcesRepository;
    @Inject
    public void setResourcesRepository(ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }

    public PageModel<SearchResponse> search(SearchForm form, int pageNo, int pageSize) {
        return resourcesRepository.search(form, pageNo, pageSize);
    }

}
