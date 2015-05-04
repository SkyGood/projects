package com.uccyou.ta.teacher.resources.service;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.resource.request.ResourceChangeRequest;
import com.uccyou.ta.teacher.resource.request.ResourceReferenceRequest;
import com.uccyou.ta.teacher.resource.request.ResourceSearchRequest;
import com.uccyou.ta.teacher.resource.request.UploadRequest;
import com.uccyou.ta.teacher.resource.response.AllResourceResponse;
import com.uccyou.ta.teacher.resource.response.ResourceChangeResponse;
import com.uccyou.ta.teacher.resource.response.ResourceRemoveResponse;
import com.uccyou.ta.teacher.resource.response.ResourceSearchResponse;
import com.uccyou.ta.teacher.resources.repository.ResourceRepository;

@Service
@Transactional(readOnly = true, rollbackFor = { Exception.class })
public class ResourceService {
    private ResourceRepository resourceRepository;

    @Inject
    public void setResourceRepository(ResourceRepository repository) {
        this.resourceRepository = repository;
    }

    @Transactional
    public Boolean upload(UploadRequest request) {
        return resourceRepository.upload(request);
    }

    public PageModel<ResourceSearchResponse> search(ResourceSearchRequest request, Integer pageNo, Integer pageSize) {
        return resourceRepository.search(request, pageNo, pageSize);
    }

    @Transactional
    public Boolean alive(Integer resId, String status) {
        return resourceRepository.alive(resId, status);
    }

    public ResourceChangeResponse find(Integer resId) {
        return resourceRepository.find(resId);
    }

    @Transactional
    public Boolean change(ResourceChangeRequest request) {
        return resourceRepository.change(request);
    }

    public PageModel<AllResourceResponse> reference(ResourceReferenceRequest request, Integer pageNo, Integer pageSize) {
        return resourceRepository.reference(request, pageNo, pageSize);
    }

    @Transactional
    public Boolean reference(Integer classId, Integer resId) {
        return resourceRepository.reference(classId, resId);
    }
    
    @Transactional
    public ResourceRemoveResponse remove(Integer classId, Integer resId) {
        return resourceRepository.remove(classId, resId);
    }

}
