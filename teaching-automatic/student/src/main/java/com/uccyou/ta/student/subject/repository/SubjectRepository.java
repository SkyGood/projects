package com.uccyou.ta.student.subject.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.util.StringUtils;
import com.uccyou.ta.student.subject.request.SubjectRequest;
import com.uccyou.ta.student.subject.response.SubjectResponse;

@Repository
public class SubjectRepository {

    private JDBCAccess jdbcAccess;

    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

    public PageModel<SubjectResponse> subject(SubjectRequest request, Integer pageNo, Integer pageSize) {
        String selectSql = " SELECT t1.id, t1.className, t1.courseName, t1.teacher_id, t1.classRoom, t1.courseType, t1.notice, t1.credit, t1.teachingTime, t1.startWeek, t1.endWeek FROM teaching.teachingclass t1 INNER JOIN teaching.classstudent t2 ON t1.id = t2.teachingClass_id ";
        String searchSql = createSearchSql(request);
        String limitSql = " LIMIT ?, ? ";
        List<SubjectResponse> list = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<SubjectResponse>() {
            @Override
            public SubjectResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                SubjectResponse response = new SubjectResponse();
                createResponse(rs, response);
                return response;
            }
        }, (pageNo - 1) * pageSize, pageSize);
        String cntSql = " SELECT COUNT(1) FROM teaching.teachingclass t1 INNER JOIN teaching.classstudent t2 ON t1.id = t2.teachingClass_id ";
        Integer totalRecords = jdbcAccess.findInteger(cntSql + searchSql);
        PageModel<SubjectResponse> pageModel = new PageModel<SubjectResponse>();
        pageModel.setRecords(list);
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }

    protected void createResponse(ResultSet rs, SubjectResponse response) throws SQLException {
        response.setClassId(rs.getInt("id"));
        response.setClassRoom(rs.getString("classRoom"));
        response.setCourseName(rs.getString("courseName")); 
        response.setCourseType(rs.getString("courseType"));
        response.setStartWeek(rs.getString("startWeek"));
        response.setEndWeek(rs.getString("endWeek"));
        response.setCredit(rs.getDouble("credit"));
        response.setTeachingTime(rs.getString("teachingTime"));
        response.setNotice(rs.getString("notice"));
        response.setTeacherName(getTeacherName(rs.getInt("teacher_id")));  
    }

    private String getTeacherName(Integer teacherId) {
        Integer tableNum = teacherId % 3;
        String sql = "SELECT t.name FROM teaching.user" + tableNum + " t WHERE id = ?";
        String teacherName = jdbcAccess.findString(sql, teacherId);
        return teacherName;
    }

    private String createSearchSql(SubjectRequest request) {
        StringBuilder sql = new StringBuilder(" WHERE t2.studentCode = " + request.getIdentityCode() + " ");
        if (StringUtils.hasText(request.getCourseName())) {
            sql.append(" AND t1.courseName LIKE '%" + request.getCourseName() + "%' ");
        }
        if (StringUtils.hasText(request.getCourseType())) {
            sql.append(" AND t1.courseType = '" + request.getCourseType() + "' ");
        }
        return sql.toString();
    }

}
