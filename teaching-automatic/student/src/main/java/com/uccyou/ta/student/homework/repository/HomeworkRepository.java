package com.uccyou.ta.student.homework.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.util.StringUtils;
import com.uccyou.ta.student.homework.request.CommitRequest;
import com.uccyou.ta.student.homework.request.HomeworkRequest;
import com.uccyou.ta.student.homework.response.HomeworkResponse;
import com.uccyou.ta.student.subject.response.CommitResponse;

@Repository
public class HomeworkRepository {

    private JDBCAccess jdbcAccess;

    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

    public PageModel<HomeworkResponse> search(HomeworkRequest request, Integer pageNo, Integer pageSize) {
        String selectSql = createSelectSql(request);
        String searchSql = createSearchSql(request);
        String limitSql = " LIMIT ?, ? ";
        List<HomeworkResponse> list = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<HomeworkResponse>() {
            @Override
            public HomeworkResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                HomeworkResponse response = new HomeworkResponse();
                response.setWorkId(rs.getString("id"));
                response.setWorkName(rs.getString("workName"));
                response.setCourseName(rs.getString("courseName"));
                response.setCourseType(rs.getString("courseType"));
                response.setTeacherName(getTeacherName(rs.getString("teacher_id")));
                return response;
            }
        }, (pageNo - 1) * pageSize, pageSize);
        String totalSql = " SELECT COUNT(1) FROM teaching.homework t1 INNER JOIN teaching.studenthomework t4 INNER JOIN teaching.teachingclass t2 ON t1.class_id = t2.id INNER JOIN teaching.classstudent t3 ON t2.id = t3.teachingClass_id ";
        Integer totalRecords = jdbcAccess.findInteger(totalSql + searchSql);

        PageModel<HomeworkResponse> pageModel = new PageModel<HomeworkResponse>();
        pageModel.setRecords(list);
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }

    private String createSearchSql(HomeworkRequest request) {
        StringBuilder sql = null;
        if ("Y".equals(request.getStatus())) {
            sql = new StringBuilder(" WHERE t3.studentCode = '" + request.getIdentityCode() + "' AND t1.alive = 'Y' ");
        } else {
            sql = new StringBuilder(" WHERE t1.id NOT IN (SELECT t.work_id FROM teaching.studenthomework t WHERE t.studentCode= '" + request.getIdentityCode() + "' ) AND t3.studentCode = '" + request.getIdentityCode() + "' AND t1.alive = 'Y' ");
        }
        if (StringUtils.hasText(request.getWorkName())) {
            sql.append(" AND t1.workName LIKE '" + request.getWorkName() + "' ");
        }
        return sql.toString();
    }

    private String createSelectSql(HomeworkRequest request) {
        if ("Y".equals(request.getStatus())) {
            return " SELECT t1.id, t1.workname, t2.courseName, t2.teacher_id, t2.courseType FROM teaching.homework t1 INNER JOIN teaching.studenthomework t4 ON t1.id = t4.work_id  INNER JOIN teaching.teachingclass t2 ON t1.class_id = t2.id INNER JOIN teaching.classstudent t3 ON t2.id = t3.teachingClass_id ";
        } else {
            return " SELECT t1.id, t1.workname, t2.courseName, t2.teacher_id, t2.courseType FROM teaching.homework t1 INNER JOIN teaching.teachingclass t2 ON t1.class_id = t2.id INNER JOIN teaching.classstudent t3 ON t2.id = t3.teachingClass_id ";
        }
    }

    protected String getTeacherName(String teacherId) {
        return jdbcAccess.findString(hashSql(teacherId), teacherId);
    }

    private String hashSql(String teacherId) {
        Integer tabNum = (int) (new Long(teacherId) % 3);
        return " SELECT t.name FROM teaching.user" + tabNum + " t WHERE id = ? ";
    }

    public CommitResponse commit(Integer workId) {

        int cnt = jdbcAccess.findInteger(SqlMapping.STUDENT_ATTACHMENT_IS_NULL, workId);
        if (1 == cnt) {
            CommitResponse response = jdbcAccess.findUniqueResult(SqlMapping.FIND_HOMEWORK_ATT_NULL, new RowMapper<CommitResponse>() {

                @Override
                public CommitResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CommitResponse response = new CommitResponse();
                    response.setResName(rs.getString("workName"));
                    response.setContent(rs.getString("content"));
                    return response;
                }
            }, workId);
            return response;
        } else {
            CommitResponse response = jdbcAccess.findUniqueResult(SqlMapping.FIND_HOMEWORK, new RowMapper<CommitResponse>() {
                @Override
                public CommitResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    CommitResponse response = new CommitResponse();
                    response.setWorkName(rs.getString("workName"));
                    response.setResLocation(rs.getString("resLocation").replace('\\', '/'));
                    response.setResName(rs.getString("resName"));
                    response.setContent(rs.getString("content"));
                    return response;
                }
            }, workId);
            return response;
        }

        /*
         * List<CommitResponse> list = jdbcAccess.find(SqlMapping.FIND_HOMEWORK,
         * new RowMapper<CommitResponse>() {
         * 
         * @Override public CommitResponse mapRow(ResultSet rs, int rowNum)
         * throws SQLException { CommitResponse response = new CommitResponse();
         * response.setWorkName(rs.getString("workName"));
         * response.setResLocation(rs.getString("resLocation").replace('\\',
         * '/')); response.setResName(rs.getString("resName"));
         * response.setContent(rs.getString("content")); return response; } },
         * workId); if (list.isEmpty()) return new CommitResponse(); return
         * list.get(0);
         */
    }

    public Boolean commit(CommitRequest request) {
        Date date = new Date();
        if (StringUtils.hasText(request.getResLocation())) {
            int rows = jdbcAccess.execute(SqlMapping.COMMIT_ATTACHMENT, request.getUserId(), request.getResName(), request.getResLocation(), date);
            if (0 == rows)
                return false;
            int resId = jdbcAccess.findInteger(SqlMapping.FIND_RES_ID, date, request.getResLocation());
            rows = jdbcAccess.execute(SqlMapping.COMMIT_EXISTS_ATTACHMENT, request.getWorkId(), request.getContent(), request.getStudentCode(), resId, date);
            if (0 == rows)
                return false;
        } else {
            int rows = jdbcAccess.execute(SqlMapping.COMMIT_NOT_EXISTS_ATTACHMENT, request.getWorkId(), request.getContent(), request.getStudentCode(), date);
            if (0 == rows)
                return false;

        }
        return true;
    }

}
