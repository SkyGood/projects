package com.uccyou.ta.teacher.resource.service;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.resource.repository.ResourceRepository;
import com.uccyou.ta.teacher.resource.response.AllResourceResponse;
import com.uccyou.ta.teacher.resource.response.ResourceChangeResponse;
import com.uccyou.ta.teacher.resource.response.ResourceSearchResponse;
import com.uccyou.ta.teacher.resource.web.request.ResourceChangeForm;
import com.uccyou.ta.teacher.resource.web.request.ResourceReferenceForm;
import com.uccyou.ta.teacher.resource.web.request.ResourceSearchForm;
import com.uccyou.ta.teacher.resource.web.request.UploadForm;

@Service
public class ResourceService {

    private ResourceRepository resourceRepository;

    @Inject
    public void setResourceRepository(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Boolean upload(UploadForm form, MultipartFile file, HttpServletRequest request) {
        return resourceRepository.upload(form, file, request);
    }

    public PageModel<ResourceSearchResponse> search(Integer userId, Integer classId, ResourceSearchForm form, Integer pageNo, Integer pageSize) {
        return resourceRepository.search(userId, classId, form, pageNo, pageSize);
    }

    public Boolean alive(Integer resId, String status) {
        return resourceRepository.alive(resId, status);
    }

    public ResourceChangeResponse find(Integer resId) {
        return resourceRepository.find(resId);
    }

    public Boolean change(ResourceChangeForm form) {
        return resourceRepository.change(form);
    }

    public PageModel<AllResourceResponse> reference(ResourceReferenceForm form, int pageNo, int pageSize) {
        return resourceRepository.reference(form, pageNo, pageSize);
    }

    public Boolean remove(Integer classId, Integer resId, HttpServletRequest request) {
        return resourceRepository.remove(classId, resId, request);
    }

    public Boolean quote(Integer classId, Integer resId) {
        return resourceRepository.quote(classId, resId);
    }
}
