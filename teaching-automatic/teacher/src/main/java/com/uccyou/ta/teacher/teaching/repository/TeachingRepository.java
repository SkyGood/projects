package com.uccyou.ta.teacher.teaching.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.core.page.PageModel;
import com.uccyou.ta.teacher.teaching.admin.response.AdminClassResponse;
import com.uccyou.ta.teacher.teaching.request.CreateTeachingClassRequest;
import com.uccyou.ta.teacher.teaching.request.TeachingClassSearchRequest;
import com.uccyou.ta.teacher.teaching.request.UpdateTeachingClassRequest;
import com.uccyou.ta.teacher.teaching.response.TeachingClassResponse;

@Repository
public final class TeachingRepository {

    private JDBCAccess jdbcAccess;

    public PageModel<TeachingClassResponse> clazz(TeachingClassSearchRequest request, Integer pageNo, Integer pageSize) {
        String selectSql = " SELECT id,teacher_id,className,classRoom,courseName,courseType,startWeek,endWeek,teachingTime,credit,notice,create_date FROM teaching.teachingclass ";
        String searchSql = createClazzSearchSql(request);
        String limitSql = " LIMIT ?,? ";
        List<TeachingClassResponse> records = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<TeachingClassResponse>() {
            @Override
            public TeachingClassResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                TeachingClassResponse res = new TeachingClassResponse();
                transferResultToEntity(rs, res);
                return res;
            }
        }, (pageNo - 1) * pageSize, pageSize);
        String countSql = " SELECT COUNT(1) FROM teaching.teachingclass ";
        int totalRecords = jdbcAccess.findInteger(countSql + searchSql);
        PageModel<TeachingClassResponse> pageModel = new PageModel<TeachingClassResponse>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setRecords(records);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }

    private String createClazzSearchSql(TeachingClassSearchRequest request) {
        StringBuilder sql = new StringBuilder(" WHERE teacher_id = " + request.getUserId() + " ");
        if (StringUtils.hasText(request.getClassName())) {
            sql.append(" AND className LIKE '%" + request.getClassName() + "%' ");
        }
        if (StringUtils.hasText(request.getClassRoom())) {
            sql.append(" AND classRoom = '" + request.getClassRoom() + "' ");
        }
        if (StringUtils.hasText(request.getCourseName())) {
            sql.append(" AND courseName LIKE '%" + request.getCourseName() + "%' ");
        }
        if (StringUtils.hasText(request.getCourseType())) {
            sql.append(" AND courseType = '" + request.getCourseType() + "' ");
        }
        if (request.getStartWeek() != null) {
            sql.append(" AND startWeek = " + request.getStartWeek() + " ");
        }
        if (request.getEndWeek() != null) {
            sql.append(" AND endWeek = " + request.getEndWeek() + " ");
        }
        return sql.toString();
    }

    private void transferResultToEntity(ResultSet rs, TeachingClassResponse res) throws SQLException {
        res.setClassId(rs.getInt("id"));
        res.setClassName(rs.getString("className"));
        res.setClassRoom(rs.getString("classRoom"));
        res.setCourseName(rs.getString("courseName"));
        res.setCourseType(rs.getString("courseType"));
        res.setCreateDate(rs.getTimestamp("create_date"));
        res.setCredit(rs.getDouble("credit"));
        res.setEndWeek(rs.getInt("endWeek"));
        res.setTeachingTime(rs.getString("teachingTime"));
        res.setStartWeek(rs.getInt("startWeek"));
        res.setNotice(rs.getString("notice"));
    }

    public int create(CreateTeachingClassRequest request) {
        Date createDate = new Date();
        int row = jdbcAccess.execute(SqlMapping.CREATE_TEACHING_CLASS, request.getUserId(), request.getIdentityCode(), request.getClassName(), request.getClassRoom(), request.getCourseName(), request.getCourseType(), request.getStartWeek(), request.getEndWeek(), request.getTeachingTime(), request.getCredit(), createDate);
        if (row != 0) {
            int clazzId = jdbcAccess.findInteger(SqlMapping.GET_JUST_CREATE_TEACHING_CLASS_ID, createDate, request.getUserId());
            return clazzId;
        } else {
            return 0;
        }
    }

    public TeachingClassResponse findClassById(Integer classId) {
        List<TeachingClassResponse> results = jdbcAccess.find(SqlMapping.GET_CLASS_BY_ID, new RowMapper<TeachingClassResponse>() {
            @Override
            public TeachingClassResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                TeachingClassResponse response = new TeachingClassResponse();
                transferResultToEntity(rs, response);
                return response;
            }
        }, classId);

        if (!results.isEmpty()) {
            return results.get(0);
        }
        return new TeachingClassResponse();
    }

    public Boolean update(UpdateTeachingClassRequest request) {
        int rows = jdbcAccess.execute(SqlMapping.UPDATE_TEACHING_CLASS_INFO, request.getClassName(), request.getClassRoom(), request.getCourseName(), request.getCourseType(), request.getStartWeek(), request.getEndWeek(), request.getTeachingTime(), request.getCredit(), request.getNotice(), request.getClassId());
        return rows != 0;
    }

    public List<AdminClassResponse> manage(Integer classId) {
        List<AdminClassResponse> list = jdbcAccess.find(SqlMapping.GET_AMDIN_CLASS, new RowMapper<AdminClassResponse>() {

            @Override
            public AdminClassResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                AdminClassResponse adminClassResponse = new AdminClassResponse();
                adminClassResponse.setAdminClassId(rs.getInt("id"));
                adminClassResponse.setAdminClassName(rs.getString("className"));
                return adminClassResponse;
            }
        }, classId);
        return list;
    }
    
    /* not perfect */
    public boolean remove(Integer classId) {
        //① 查询到所有的行政班
        List<Object[]> adminClassIds = jdbcAccess.find(SqlMapping.FIND_ALL_ADMIN_CLASS, new RowMapper<Object[]>() {
            @Override
            public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Object[]{rs.getInt("id")};
            }
        }, classId);
        
        //② 根据行政班查所有的学生
        List<Object[]> classStudentIds = new ArrayList<Object[]>();
        for (Object[] adminClassId : adminClassIds) {
            List<Object[]> studentIds = jdbcAccess.find(SqlMapping.FIND_ALL_CLASS_STUDENT, new RowMapper<Object[]>() {
                @Override
                public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Object[]{rs.getInt("id")};
                }
            }, classId, adminClassId[0]);
            classStudentIds.addAll(studentIds);
        }
        
        //③ 根据classId和学生ID查询所有的缺席记录
        List<Object[]> absentNoteIds = new ArrayList<Object[]>();
        for (Object[] classStudentId : classStudentIds) {
            List<Object[]> recordIds = jdbcAccess.find(SqlMapping.FIND_ALL_ABSENT_NOTE_RECORD, new RowMapper<Object[]>() {
                @Override
                public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Object[]{rs.getInt("id")};
                }
            }, classId, classStudentId[0]);
            absentNoteIds.addAll(recordIds);
        }
        
        
        /* ④
         * ④-①根据classId查询到所有的引用资源，并记录下引用记录的ID待删除
         * 进一步判断该资源是否还存在其他引用select count(1) != 1
         * ④-② 最后拿到只被本班级引用的资源的ID
         */
        //④-①根据classId查询到所有的引用资源，并记录下引用记录的ID待删除
        List<Object[]> refIds = jdbcAccess.find(SqlMapping.FIND_REF_RESOURCES, new RowMapper<Object[]>() {
            @Override
            public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Object[]{rs.getInt("id")};
            }
        }, classId);
        //④-② 拿到只被本班级引用的资源的ID
        List<Object[]> delRefIds = jdbcAccess.find(SqlMapping.FIND_ONCE_REF_RESOURCES, new RowMapper<Object[]>() {
            @Override
            public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Object[]{rs.getInt("res_id")};
            }
        }, classId);
        
        //⑤ 根据classId查出所有的作业，并过滤出有附件的ID
        List<Object[]> homeworkIds = jdbcAccess.find(SqlMapping.FIND_ALL_HOMEWORK, new RowMapper<Object[]>() {
            @Override
            public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Object[]{rs.getInt("id")};
            }
        }, classId);
        //res
        List<Object[]> delHomeworkIds = jdbcAccess.find(SqlMapping.FIND_RES_HOMEWORK, new RowMapper<Object[]>() {
            @Override
            public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Object[]{rs.getInt("res_id")};
            }
        }, classId);
        
        //⑥ 根据作业ID查出学生提交作业的ID，并过滤出有附件的ID
        List<Object[]> studentHomeworkIds = new ArrayList<Object[]>(); 
        for (Object[] homeworkId : homeworkIds) {
            List<Object[]> stuWorkIds = jdbcAccess.find(SqlMapping.FIND_STUDENT_HOMEWORK, new RowMapper<Object[]>() {
                @Override
                public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Object[]{rs.getInt("id")};
                }
            }, homeworkId[0]);
            studentHomeworkIds.addAll(stuWorkIds);
        }
        //res
        List<Object[]> delStudentHomeworkIds = new ArrayList<Object[]>();
        for (Object[] workId : studentHomeworkIds) {
            List<Object[]> resStuIds = jdbcAccess.find(SqlMapping.FIND_RES_STUDENT_HOMEWORK, new RowMapper<Object[]>() {
                @Override
                public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Object[]{rs.getInt("res_id")};
                }
            }, workId[0]);
            delStudentHomeworkIds.addAll(resStuIds);
        }
        //⑦ 组合④-②、⑤、⑥过滤结果的ID集合
        List<Object[]> delResIds = new ArrayList<Object[]>();
        delResIds.addAll(delRefIds);
        delResIds.addAll(delHomeworkIds);
        delResIds.addAll(delStudentHomeworkIds);
        
        //删除学生提交的作业
        jdbcAccess.batchExecute(SqlMapping.DELETE_STUDENT_HOMEWORK, studentHomeworkIds);
        //删除班级作业
        jdbcAccess.batchExecute(SqlMapping.DELETE_HOMEWORK, homeworkIds);
        //删除④-①的引用记录
        jdbcAccess.batchExecute(SqlMapping.DELETE_RES_REF, refIds);
        //根据⑦的结果删除所有附件
        jdbcAccess.batchExecute(SqlMapping.DELETE_RES, delResIds);
        //删除③
        jdbcAccess.batchExecute(SqlMapping.DELETE_ABSENT, absentNoteIds);
        //删除②
        jdbcAccess.batchExecute(SqlMapping.DELETE_CLASS_STUDENT, classStudentIds);
        //删除①
        jdbcAccess.batchExecute(SqlMapping.DELETE_ADMIN_CLASS, adminClassIds);
        //删除教学班，完成删除
        jdbcAccess.execute(SqlMapping.DELETE_TEACHING_CLASS, classId);
        return true;
    }
    
    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }
}
