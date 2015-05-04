package com.uccyou.ta.teacher.homework.repository;

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
import com.uccyou.ta.teacher.homework.request.AddHomeworkRequest;
import com.uccyou.ta.teacher.homework.request.HomeworkDetailRequest;
import com.uccyou.ta.teacher.homework.request.HomeworkLevelRequest;
import com.uccyou.ta.teacher.homework.request.HomeworkSearchRequest;
import com.uccyou.ta.teacher.homework.request.UpdateHomeworkRequest;
import com.uccyou.ta.teacher.homework.response.HomeworkDetailResponse;
import com.uccyou.ta.teacher.homework.response.HomeworkLevelResponse;
import com.uccyou.ta.teacher.homework.response.HomeworkRemoveResponse;
import com.uccyou.ta.teacher.homework.response.HomeworkSearchResponse;
import com.uccyou.ta.teacher.homework.response.UpdateHomeworkAttachmentResponse;
import com.uccyou.ta.teacher.homework.response.UpdateHomeworkResponse;
import com.uccyou.ta.teacher.homework.web.request.AddHomeworkForm;
import com.uccyou.ta.teacher.homework.web.request.HomeworkLevelForm;
import com.uccyou.ta.teacher.homework.web.request.HomeworkSearchForm;
import com.uccyou.ta.teacher.homework.web.request.UpdateHomeworkForm;
import com.uccyou.ta.teacher.resource.repository.ResourceRepository;

@Repository
public class HomeworkRepository extends AbstractRepository {

    private static Logger logger = LoggerFactory.getLogger(ResourceRepository.class);

    public PageModel<HomeworkSearchResponse> search(Integer classId, HomeworkSearchForm form, int pageNo, int pageSize) {
        HomeworkSearchRequest request = new HomeworkSearchRequest();
        if (null != form) {
            request.setWorkName(form.getWorkName());
        }
        String bodyContent = JSONBinder.binder(HomeworkSearchRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.TEACHER).action("/teacher/homework/manage/%s/%s/%s").appKey(appKey).body(bodyContent).arguments(classId, pageNo, pageSize));
    }

    public Boolean alive(Integer workId, String status) {
        return uccyouClientApi.get(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/homework/manage/%s/%s").appKey(appKey).arguments(workId, status));
    }

    public UpdateHomeworkResponse update(Integer workId) {
        return uccyouClientApi.get(EndPointBuilder.create(UpdateHomeworkResponse.class).endpoint(EndPoint.TEACHER).action("/teacher/homework/update/%s").appKey(appKey).arguments(workId));
    }

    public Boolean update(UpdateHomeworkForm form, MultipartFile file, HttpServletRequest request) {
        UpdateHomeworkRequest req = new UpdateHomeworkRequest();
        req.setWorkId(form.getWorkId());
        req.setWorkName(form.getWorkName());
        req.setContent(form.getContent());
        req.setTeacherId(form.getTeacherId());
        // if file is exists, add info into req.reLocation
        if (0 < file.getSize()) {
            try {
                List<String> list = FileUpload.uploadFiles(form.getTeacherId(), request, file);
                req.setResLocation(list.get(0));
            } catch (Exception e) {
                logger.info("upload updatehomewok attachment file ={} failed, exception = {}", new Object[] { file.getOriginalFilename(), e.getStackTrace() });
                return false;
            }
        }
        String bodyContent = JSONBinder.binder(UpdateHomeworkRequest.class).toJSON(req);
        // update homewok info
        UpdateHomeworkAttachmentResponse response = uccyouClientApi.put(EndPointBuilder.create(UpdateHomeworkAttachmentResponse.class).endpoint(EndPoint.TEACHER).action("/teacher/homework/update").appKey(appKey).body(bodyContent));
        // if teacher project is deal fail,delete new attachment
        if (!response.getFlag()) {
            if (0 < file.getSize()) {
                try {
                    FileTransfer.removeAbsoluteFile(req.getResLocation(), request);
                } catch (Exception e) {
                    logger.info("remove updatehomewok attachment file ={} failed, exception = {}", new Object[] { file.getOriginalFilename(), e.getStackTrace() });
                }
            }
            return false;
        }
        // if exists old attachment, remove old attachment
        if (null != response.getResLocation()) {
            try {
                FileTransfer.removeAbsoluteFile(response.getResLocation(), request);
            } catch (Exception e) {
                logger.info("remove updatehomewok attachment file ={} failed, exception = {}", new Object[] { file.getOriginalFilename(), e.getStackTrace() });
                return false;
            }
        }
        return true;
    }

    public Boolean add(AddHomeworkForm form, MultipartFile file, HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        AddHomeworkRequest req = new AddHomeworkRequest();
        if (0 < file.getSize()) {
            try {
                list = FileUpload.uploadFiles(form.getTeacherId(), request, file);
            } catch (Exception e) {
                logger.info("upload excel file ={} failed, exception = {}", new Object[] { file.getOriginalFilename(), e.getStackTrace() });
                return false;
            }
            if (!list.isEmpty()) {
                String resLocation = list.get(0);
                req.setResLocation(resLocation);
            }
        }
        req.setClassId(form.getClassId());
        req.setTeacherId(form.getTeacherId());
        req.setWorkName(form.getWorkName());
        req.setContent(form.getContent());
        String bodyContent = JSONBinder.binder(AddHomeworkRequest.class).toJSON(req);
        return uccyouClientApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/homework/add").appKey(appKey).body(bodyContent));
    }

    public PageModel<HomeworkDetailResponse> detail(Integer classId, Integer workId, String status, int pageNo, int pageSize) {
        HomeworkDetailRequest request = new HomeworkDetailRequest();
        request.setClassId(classId);
        request.setWorkId(workId);
        request.setStatus(status);
        String bodyContent = JSONBinder.binder(HomeworkDetailRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.TEACHER).action("/teacher/homework/detail/%s/%s").appKey(appKey).body(bodyContent).arguments(pageNo, pageSize));
    }

    public HomeworkLevelResponse level(Integer id) {
        return uccyouClientApi.get(EndPointBuilder.create(HomeworkLevelResponse.class).endpoint(EndPoint.TEACHER).action("/teacher/homework/level/%s").appKey(appKey).arguments(id));
    }

    public Boolean level(HomeworkLevelForm form) {
        HomeworkLevelRequest request = new HomeworkLevelRequest();
        request.setId(form.getId());
        request.setLevel(form.getLevel());
        String bodyContent = JSONBinder.binder(HomeworkLevelRequest.class).toJSON(request);
        return uccyouClientApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.TEACHER).action("/teacher/homework/level").appKey(appKey).body(bodyContent));
    }

    public Boolean remove(Integer workId, HttpServletRequest request) {
        HomeworkRemoveResponse response = uccyouClientApi.delete(EndPointBuilder.create(HomeworkRemoveResponse.class).endpoint(EndPoint.TEACHER).action("/teacher/homework/remove/%s").appKey(appKey).arguments(workId));
        if (!response.getFlag()) {
            return false;
        }
        try {
            if (null != response.getStuAttLocation()) {
                for (String location : response.getStuAttLocation()) {
                    FileTransfer.removeAbsoluteFile(location, request);
                }
            }
            if (null != response.getTeaAttLocation()) {
                FileTransfer.removeAbsoluteFile(response.getTeaAttLocation(), request);
            }
        } catch (Exception e) {
            logger.info("delete homework attachment failed, exception = {}", new Object[] { e.getStackTrace() });
            return false;
        }
        return true;
    }
}
