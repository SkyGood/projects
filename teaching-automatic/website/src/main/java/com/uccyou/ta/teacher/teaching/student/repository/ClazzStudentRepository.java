package com.uccyou.ta.teacher.teaching.student.repository;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.uccyou.core.io.FileUpload;
import com.uccyou.core.json.JSONBinder;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.service.EndPointBuilder;
import com.uccyou.ta.commons.repository.AbstractRepository;
import com.uccyou.ta.teacher.teaching.request.ImportClazzStudentRequest;
import com.uccyou.ta.teacher.teaching.student.web.request.ImportClazzStudentForm;

@Repository
public class ClazzStudentRepository extends AbstractRepository {

    private static Logger logger = LoggerFactory.getLogger(ClazzStudentRepository.class);
    
    public int importStudent(ImportClazzStudentForm form, MultipartFile file, HttpServletRequest request) {
        //upload file
        List<String> storePaths = new ArrayList<String>();
        try {
            storePaths = FileUpload.uploadFiles(form.getUserId(), request, file);
        } catch (Exception e) {
            logger.info("upload excel file ={} failed, exception = {}", new Object[]{file.getOriginalFilename(), e.getStackTrace()});
            return -1;
        }
        String filePath = storePaths.get(0);
        ImportClazzStudentRequest req = new ImportClazzStudentRequest();
        req.setClassId(form.getClassId());
        req.setAdminClassId(form.getAdminClassId());
        req.setFilePath(filePath);
        String bodyContent = JSONBinder.binder(ImportClazzStudentRequest.class).toJSON(req);
        return uccyouClientApi.post(EndPointBuilder.create(Integer.class).endpoint(EndPoint.TEACHER).action("/teacher/clazz/import").appKey(appKey).body(bodyContent));
    }

}
