package com.uccyou.ta.teacher.resource.repository;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.uccyou.core.io.FileTransfer;
import com.uccyou.core.io.FileUpload;
import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.teacher.resource.request.ResourceChangeRequest;
import com.uccyou.ta.teacher.resource.request.ResourceReferenceRequest;
import com.uccyou.ta.teacher.resource.request.ResourceSearchRequest;
import com.uccyou.ta.teacher.resource.request.UploadRequest;
import com.uccyou.ta.teacher.resource.response.AllResourceResponse;
import com.uccyou.ta.teacher.resource.response.ResourceChangeResponse;
import com.uccyou.ta.teacher.resource.response.ResourceRemoveResponse;
import com.uccyou.ta.teacher.resource.response.ResourceSearchResponse;
import com.uccyou.ta.teacher.resource.web.request.ResourceChangeForm;
import com.uccyou.ta.teacher.resource.web.request.ResourceReferenceForm;
import com.uccyou.ta.teacher.resource.web.request.ResourceSearchForm;
import com.uccyou.ta.teacher.resource.web.request.UploadForm;

@Repository
public class ResourceRepository extends AbstractRepository {
    private static Logger logger = LoggerFactory.getLogger(ResourceRepository.class);

    public Boolean upload(UploadForm form, MultipartFile file, HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        try {
            list = FileUpload.uploadFiles(form.getTeacherId(), request, file);
        } catch (Exception e) {
            logger.info("upload excel file ={} failed, exception = {}", new Object[] { file.getOriginalFilename(), e.getStackTrace() });
            return false;
        }
        if (list.isEmpty()) {
            return false;
        }
        UploadRequest req = new UploadRequest();
        req.setResName(form.getResName());
        req.setClassId(form.getClassId());
        req.setResLocation(list.get(0));
        req.setTeacherId(form.getTeacherId());

        String bodyContent = JSONBinder.binder(UploadRequest.class).toJSON(req);
        return uccyouClientApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/resource/upload").appKey(appKey).body(bodyContent));
    }

    public PageModel<ResourceSearchResponse> search(Integer userId, Integer classId, ResourceSearchForm form, Integer pageNo, Integer pageSize) {
        ResourceSearchRequest request = new ResourceSearchRequest();
        request.setClassId(classId);
        request.setUserId(userId);
        if (null != form) {
            request.setResName(form.getResName());
            /*
             * request.setStartDate(form.getStartDate());
             * request.setEndDate(form.getEndDate());
             */
        }
        String bodyContent = JSONBinder.binder(ResourceSearchRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.TEACHER).action("/teacher/resource/search/%s/%s").appKey(appKey).body(bodyContent).arguments(pageNo, pageSize));
    }

    public Boolean alive(Integer resId, String status) {
        return uccyouClientApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/resource/alive/%s/%s").appKey(appKey).arguments(resId, status));
    }

    public ResourceChangeResponse find(Integer resId) {
        return uccyouClientApi.get(EndPointBuilder.create(ResourceChangeResponse.class).endpoint(EndPoint.TEACHER).action("/teacher/resource/find/%s").appKey(appKey).arguments(resId));
    }

    public Boolean change(ResourceChangeForm form) {
        ResourceChangeRequest request = new ResourceChangeRequest();
        request.setResId(form.getResId());
        request.setResName(form.getResName());
        String bodyContent = JSONBinder.binder(ResourceChangeRequest.class).toJSON(request);
        return uccyouClientApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/resource/change").appKey(appKey).body(bodyContent));
    }

    public PageModel<AllResourceResponse> reference(ResourceReferenceForm form, int pageNo, int pageSize) {
        ResourceReferenceRequest request = new ResourceReferenceRequest();
        request.setTeacherId(form.getTeacherId());
        request.setClassId(form.getClassId());
        request.setResName(form.getResName());
        String bodyContent = JSONBinder.binder(ResourceReferenceRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.TEACHER).action("/teacher/resource/reference/%s/%s").appKey(appKey).body(bodyContent).arguments(pageNo, pageSize));
    }

    public Boolean quote(Integer classId, Integer resId) {
        return uccyouClientApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/resource/quote/%s/%s").appKey(appKey).arguments(classId, resId));
    }
    
    public Boolean remove(Integer classId, Integer resId, HttpServletRequest request) {
        ResourceRemoveResponse response = uccyouClientApi.delete(EndPointBuilder.create(ResourceRemoveResponse.class).endpoint(EndPoint.TEACHER).action("/teacher/resource/alive/%s/%s").appKey(appKey).arguments(classId, resId));
        if (!response.getFlag()) {
            return false;
        }
        try {
            if (null != response.getResLocation()) {
                FileTransfer.removeAbsoluteFile(response.getResLocation(), request);
            }
        } catch (Exception e) {
            logger.info("delete teachingclass resources failed, exception = {}", new Object[] { e.getStackTrace() });
            return false;
        }
        return true;
    }

}
