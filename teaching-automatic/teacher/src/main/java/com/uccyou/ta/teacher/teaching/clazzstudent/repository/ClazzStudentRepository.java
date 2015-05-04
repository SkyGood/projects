package com.uccyou.ta.teacher.teaching.clazzstudent.repository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.io.ExcelIO;
import com.uccyou.core.io.FileTransfer;
import com.uccyou.core.service.EndPoint;
import com.uccyou.ta.teacher.teaching.request.ImportClazzStudentRequest;

@Repository
public final class ClazzStudentRepository {

    private static Logger logger = LoggerFactory.getLogger(ClazzStudentRepository.class);
    
    private JDBCAccess jdbcAccess;

    public int importStudent(ImportClazzStudentRequest request, HttpServletRequest req) {
        String filePath = request.getFilePath();
        String realPath = EndPoint.WEBSITE.getEndpoint() + filePath;
        realPath = realPath.replace('\\', '/');
        int importCount = 0;
        String tempFile = "";
        try {
            tempFile = FileTransfer.transfer(realPath, req);
            ExcelIO ei = new ExcelIO();
            importCount = ei.importData(tempFile, jdbcAccess, request.getClassId().toString(), request.getAdminClassId().toString());
        } catch (Exception e) {
            logger.info("Import excel datas failed. Exception = {}, ExceptionInfo = {}", new Object[] { e.getStackTrace(), e.getMessage() });
            return -1;
        }
        return importCount;
    }
    
    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

}
