package com.uccyou.ta.student.resources.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.student.resources.repository.ResourcesRepository;
import com.uccyou.ta.student.resources.request.SearchRequest;
import com.uccyou.ta.student.resources.response.SearchResponse;

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class ResourcesService {

    private ResourcesRepository resourcesRepository;
    @Inject
    public void setResourcesRepository(ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }
    
    public PageModel<SearchResponse> search(SearchRequest request, Integer pageNo, Integer pageSize) {
        return resourcesRepository.search(request, pageNo, pageSize);
    }
    
}
