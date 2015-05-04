package com.uccyou.ta.student.homework.repository;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import com.uccyou.core.io.FileUpload;
import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.student.homework.request.CommitRequest;
import com.uccyou.ta.student.homework.request.HomeworkRequest;
import com.uccyou.ta.student.homework.response.HomeworkResponse;
import com.uccyou.ta.student.homework.web.request.CommitForm;
import com.uccyou.ta.student.homework.web.request.HomeworkForm;
import com.uccyou.ta.student.subject.response.CommitResponse;

@Repository
public class StudentHomeworkRepository extends AbstractRepository {

    private Logger logger = LoggerFactory.getLogger(StudentHomeworkRepository.class);

    public PageModel<HomeworkResponse> search(HomeworkForm form, int pageNo, int pageSize) {
        HomeworkRequest request = new HomeworkRequest();
        request.setIdentityCode(form.getIdentityCode());
        request.setStatus(form.getStatus());
        request.setWorkName(form.getWorkName());
        String bodyContent = JSONBinder.binder(HomeworkRequest.class).toJSON(request);
        return uccyouClientApi.post(EndPointBuilder.create(PageModel.class).endpoint(EndPoint.STUDENT).action("/student/homework/search/%s/%s").appKey(appKey).body(bodyContent).arguments(pageNo, pageSize));
    }

    public CommitResponse commit(Integer workId) {
        return uccyouClientApi.get(EndPointBuilder.create(CommitResponse.class).endpoint(EndPoint.STUDENT).action("/student/homework/commit/%s").appKey(appKey).arguments(workId));
    }

    public Boolean commit(CommitForm form, MultipartFile file, HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        CommitRequest req = new CommitRequest();
        if (null != file) {
            try {
                list = FileUpload.uploadFiles(form.getUserId(), request, file);
            } catch (Exception e) {
                logger.info("upload excel file ={} failed, exception = {}", new Object[] { file.getOriginalFilename(), e.getStackTrace() });
                return false;
            }
            if (!list.isEmpty()) {
                String resLocation = list.get(0);
                req.setResLocation(resLocation);
                req.setResName(form.getResName());
            }
        }
        req.setUserId(form.getUserId());
        req.setContent(form.getContent());
        req.setWorkId(form.getWorkId());
        req.setStudentCode(form.getStudentCode());

        String bodyContent = JSONBinder.binder(CommitRequest.class).toJSON(req);
        return uccyouClientApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.STUDENT).action("/student/homework/commit").appKey(appKey).body(bodyContent));
    }

}
