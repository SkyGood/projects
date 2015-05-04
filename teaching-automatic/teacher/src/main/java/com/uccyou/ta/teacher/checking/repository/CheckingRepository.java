package com.uccyou.ta.teacher.checking.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.util.CollectionTools;
import com.uccyou.ta.teacher.checking.request.CheckingAdminRequest;
import com.uccyou.ta.teacher.checking.request.CheckingRandomRequest;
import com.uccyou.ta.teacher.checking.response.AdminClassNameResponse;
import com.uccyou.ta.teacher.checking.response.ClassStudentResponse;

@Repository
public class CheckingRepository {

    private JDBCAccess jdbcAccess;

    public PageModel<ClassStudentResponse> checkingAll(Integer classId, Integer pageNo, Integer pageSize) {
        List<ClassStudentResponse> records = jdbcAccess.find(SqlMapping.CHECKING_ALL, new RowMapper<ClassStudentResponse>() {
            @Override
            public ClassStudentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                ClassStudentResponse res = new ClassStudentResponse();
                packClassStudentResponse(res, rs);
                return res;
            }
        }, classId, (pageNo - 1) * pageSize, pageSize);
        
        Integer totalRecords = jdbcAccess.findInteger(SqlMapping.CHECKING_ALL_COUNT, classId);
        PageModel<ClassStudentResponse> pageModel = new PageModel<ClassStudentResponse>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setRecords(records);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }
    
    public PageModel<ClassStudentResponse> checkingAdmin(CheckingAdminRequest request, Integer pageNo, Integer pageSize) {
        List<ClassStudentResponse> records = jdbcAccess.find(SqlMapping.CHECKING_ADMIN, new RowMapper<ClassStudentResponse>() {
            @Override
            public ClassStudentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                ClassStudentResponse res = new ClassStudentResponse();
                packClassStudentResponse(res, rs);
                return res;
            }
        }, request.getClassId(), request.getAdminClassId(), (pageNo - 1) * pageSize, pageSize);
        
        Integer totalRecords = jdbcAccess.findInteger(SqlMapping.CHECKING_ADMIN_COUNT, request.getClassId(), request.getAdminClassId());
        PageModel<ClassStudentResponse> pageModel = new PageModel<ClassStudentResponse>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setRecords(records);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }
    
    private void packClassStudentResponse(ClassStudentResponse res, ResultSet rs) throws SQLException {
        res.setStudentId(rs.getInt("id"));
        res.setStudentName(rs.getString("studentName"));
        res.setStudentCode(rs.getString("studentCode"));
        res.setStudentPhone(rs.getString("studentPhone"));
        res.setMonitor(rs.getString("monitor"));
        res.setLearner(rs.getString("learner"));
        res.setAbsentTime(rs.getInt("absentTime"));
        res.setNoteTime(rs.getInt("noteTime"));
    }
    
    private List<Integer> findCheckingStudents(CheckingRandomRequest request) {
        return jdbcAccess.find(SqlMapping.CHECKING_RANDOM_RESULT, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("id");
            }
        }, request.getClassId(), request.getCount());
    }
    
    public PageModel<ClassStudentResponse> checkingRandom(CheckingRandomRequest request) {
        PageModel<ClassStudentResponse> pageModel = new PageModel<ClassStudentResponse>();
        
        List<Integer> randomResult = findCheckingStudents(request);
        
        List<ClassStudentResponse> records = new ArrayList<ClassStudentResponse>();
        if (request.getCount() <= 10) { //不足或刚好10条数据，直接全部返回
            for (Integer r : randomResult) {
                ClassStudentResponse res = jdbcAccess.findUniqueResult(SqlMapping.FIND_STUDENT_INFO, new RowMapper<ClassStudentResponse>() {
                    @Override
                    public ClassStudentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ClassStudentResponse res = new ClassStudentResponse();
                        packClassStudentResponse(res, rs);
                        return res;
                    }
                }, r);
                records.add(res);
            }
            pageModel.setPageNo(1);
            pageModel.setPageSize(10);
            pageModel.setRecords(records);
            pageModel.setTotalRecords((long) request.getCount());
            
        } else { //超过10条，存入临时表，执行分页操作
            //save to tempCheckingRandom
            List<Object[]> temp = new ArrayList<Object[]>();
            Date date = new Date();
            for (Integer r : randomResult) {
                Object[] t = new Object[]{request.getClassId(), r, date};
                temp.add(t);
            }
            jdbcAccess.batchExecute(SqlMapping.CHECKING_RANDOM_SAVE_TEMP, temp);
            
            //find page one records
            List<Integer> pageOneResult = CollectionTools.subList(randomResult, 0, 10);
            for (Integer r : pageOneResult) {
                ClassStudentResponse res = jdbcAccess.findUniqueResult(SqlMapping.FIND_STUDENT_INFO, new RowMapper<ClassStudentResponse>() {
                    @Override
                    public ClassStudentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                        ClassStudentResponse res = new ClassStudentResponse();
                        packClassStudentResponse(res, rs);
                        return res;
                    }
                }, r);
                records.add(res);
            }
            pageModel.setPageNo(1);
            pageModel.setPageSize(10);
            pageModel.setRecords(records);
            pageModel.setTotalRecords((long) request.getCount());
        }
        return pageModel;
    }
    
    public PageModel<ClassStudentResponse> randomPaging(Integer classId, Integer pageNo, Integer pageSize) {
        List<ClassStudentResponse> records = new ArrayList<ClassStudentResponse>();
        List<Integer> ids = jdbcAccess.find(SqlMapping.FIND_RANDOM_PAGING_IDS, new RowMapper<Integer>() {
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("id");
            }
        }, classId, (pageNo - 1) * pageSize, pageSize);
        
        for (Integer id : ids) {
            ClassStudentResponse res = jdbcAccess.findUniqueResult(SqlMapping.FIND_STUDENT_INFO, new RowMapper<ClassStudentResponse>() {
                @Override
                public ClassStudentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    ClassStudentResponse res = new ClassStudentResponse();
                    packClassStudentResponse(res, rs);
                    return res;
                }
            }, id);
            records.add(res);
        }
        Integer totalRecords = jdbcAccess.findInteger(SqlMapping.FIND_RANDOM_COUNT, classId);
        PageModel<ClassStudentResponse> pageModel = new PageModel<ClassStudentResponse>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setRecords(records);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }
    
    public Boolean checkingClose(Integer classId) {
        int rows = jdbcAccess.execute(SqlMapping.DELETE_RANDOM_TEMP, classId);
        return rows != 0;
    }
    
    public Boolean absent(Integer classId, Integer studentId) {
        int row = jdbcAccess.execute(SqlMapping.ADD_ABSENT_TIME, studentId, classId);
        if (row == 0) return false;
        int result = jdbcAccess.execute(SqlMapping.ADD_ABSENT_RECORD, studentId, classId, new Date());
        return result != 0;
    }
    
    public Boolean note(Integer classId, Integer studentId) {
        int row = jdbcAccess.execute(SqlMapping.ADD_NOTE_TIME, studentId, classId);
        if (row == 0) return false;
        int result = jdbcAccess.execute(SqlMapping.ADD_NOTE_RECORD, studentId, classId, new Date());
        return result != 0;
    }
    
    public AdminClassNameResponse findAdminClassNameById(Integer adminClassId) {
        AdminClassNameResponse response = jdbcAccess.findUniqueResult(SqlMapping.FINDADMINCLASSNAMEBYID, new RowMapper<AdminClassNameResponse>() {
            @Override
            public AdminClassNameResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                AdminClassNameResponse response = new AdminClassNameResponse();
                response.setAdminClassName(rs.getString("className"));
                return response;
            }
        }, adminClassId);
        return response;
    }
    
    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

}
