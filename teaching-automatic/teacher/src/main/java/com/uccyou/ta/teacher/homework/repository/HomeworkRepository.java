package com.uccyou.ta.teacher.homework.repository;

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
import com.uccyou.core.util.StringUtils;
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

@Repository
public class HomeworkRepository {

    private JDBCAccess jdbcAccess;

    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

    public PageModel<HomeworkSearchResponse> manage(HomeworkSearchRequest request, Integer classId, Integer pageNo, Integer pageSize) {

        String selectSql = " SELECT id, workName, create_date, alive FROM teaching.homework ";
        String searchSql = createHomeworkSearchSql(request, classId);
        String limitSql = " LIMIT ?, ? ";
        List<HomeworkSearchResponse> list = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<HomeworkSearchResponse>() {
            @Override
            public HomeworkSearchResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                HomeworkSearchResponse response = new HomeworkSearchResponse();
                response.setWorkId(rs.getInt("id"));
                response.setWorkName(rs.getString("workName"));
                response.setCreateDate(rs.getTimestamp("create_date"));
                response.setAlive(rs.getString("alive"));
                return response;
            }
        }, (pageNo - 1) * pageNo, pageSize);
        String countSql = " SELECT COUNT(1) FROM teaching.homework ";
        Integer count = jdbcAccess.findInteger(countSql + searchSql);
        PageModel<HomeworkSearchResponse> pageModel = new PageModel<HomeworkSearchResponse>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setRecords(list);
        pageModel.setTotalRecords((long) count);
        return pageModel;
    }

    private String createHomeworkSearchSql(HomeworkSearchRequest request, Integer classId) {
        StringBuilder sql = new StringBuilder(" WHERE class_id = " + classId + " ");
        if (StringUtils.hasText(request.getWorkName())) {
            sql.append(" AND workName LIKE '%" + request.getWorkName() + "%' ");
        }
        return sql.toString();
    }

    public Boolean alive(Integer workId, String status) {
        int rows = jdbcAccess.execute(SqlMapping.CHANGE_HOMEWORK_ALIVE, status, workId);
        return 0 != rows;
    }

    public UpdateHomeworkResponse update(final Integer workId) {
        List<UpdateHomeworkResponse> list = jdbcAccess.find(SqlMapping.FIND_HOMEWORK_BY_ID, new RowMapper<UpdateHomeworkResponse>() {
            @Override
            public UpdateHomeworkResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                UpdateHomeworkResponse response = new UpdateHomeworkResponse();
                response.setWorkName(rs.getString("workName"));
                response.setContent(rs.getString("content"));
                response.setWorkId(workId);
                return response;
            }
        }, workId);
        if (list.isEmpty()) {
            return new UpdateHomeworkResponse();
        }
        return list.get(0);
    }

    public UpdateHomeworkAttachmentResponse update(UpdateHomeworkRequest request) {
        Date date = new Date();
        // if not exists new attachment
        if (null == request.getResLocation()) {
            UpdateHomeworkAttachmentResponse response = new UpdateHomeworkAttachmentResponse();
            response.setFlag(true);
            try {
                jdbcAccess.execute(SqlMapping.UPDATE_HOMEWORK, request.getWorkName(), request.getContent(), request.getWorkId());
                return response;
            } catch (Exception e) {
                response.setFlag(false);
                return response;
            }
        } else {
            // if exists new attachment
            int cnt = jdbcAccess.findInteger(SqlMapping.OLD_ATTACHMENT_EXISTS, request.getWorkId());
            // 1,IF exists old attachment
            if (0 != cnt) {
                // find resId and resLocation
                UpdateHomeworkAttachmentResponse response = jdbcAccess.findUniqueResult(SqlMapping.FIND_RESINFO_BY_WORKID, new RowMapper<UpdateHomeworkAttachmentResponse>() {
                    @Override
                    public UpdateHomeworkAttachmentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                        UpdateHomeworkAttachmentResponse response = new UpdateHomeworkAttachmentResponse();
                        response.setFlag(true);
                        response.setResId(rs.getInt("id"));
                        response.setResLocation(rs.getString("resLocation"));
                        return response;
                    }
                }, request.getWorkId());
                // update oldattachment: table resources
                try {
                    jdbcAccess.execute(SqlMapping.UPDATE_HOMEWOK_RESOURCES, request.getResLocation(), request.getWorkName(), date, response.getResId());
                    jdbcAccess.execute(SqlMapping.UPDATE_HOMEWORK_EXISTS_ATTACHMENT, request.getWorkName(), request.getContent(), request.getWorkId());
                    return response;
                } catch (Exception e) {
                    response.setFlag(false);
                    return response;
                }
            } else {
                UpdateHomeworkAttachmentResponse response = new UpdateHomeworkAttachmentResponse();
                response.setFlag(true);
                // if not exists old attachment
                try {
                    // 1,insert into table: resources
                    jdbcAccess.execute(SqlMapping.ADD_HOMEWORK_ATTACHMENT, request.getTeacherId(), request.getWorkName(), request.getResLocation(), date);
                    Integer resId = jdbcAccess.findInteger(SqlMapping.FIND_RESID, request.getTeacherId(), request.getResLocation(), date);
                    // 2,update into table: homework
                    // workName = ?, content = ?, res_id = ? WHERE id = ?
                    jdbcAccess.execute(SqlMapping.UPDATE_HOMEWORK_NOT_EXISTS_ATTACHMENT, request.getWorkName(), request.getContent(), resId, request.getWorkId());
                    return response;
                } catch (Exception e) {
                    response.setFlag(false);
                    return response;
                }
            }
        }
    }

    public Boolean add(AddHomeworkRequest request) {

        int rows = 0;
        // Insert data into table:resources
        Date date = new Date();
        if (StringUtils.hasText(request.getResLocation())) {
            rows = jdbcAccess.execute(SqlMapping.ADD_HOMEWORK_ATTACHMENT, request.getTeacherId(), request.getWorkName(), request.getResLocation(), date);
            if (0 == rows)
                return false;
            // find homework attachment id
            List<Integer> list = jdbcAccess.find(SqlMapping.FIND_HOMEWORK_ATTACHMENT_ID, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getInt("id");
                }
            }, request.getResLocation(), date);
            if (list.isEmpty())
                return false;
            Integer resId = list.get(0);
            rows = jdbcAccess.execute(SqlMapping.ADD_HOMEWORK_EXISTS_ATTACHMENT, request.getWorkName(), request.getClassId(), request.getContent(), resId, date);
        } else {
            rows = jdbcAccess.execute(SqlMapping.ADD_HOMEWORK_NOT_EXISTS_ATTACHMENT, request.getWorkName(), request.getClassId(), request.getContent(), date);
        }
        return 0 != rows;
    }

    public PageModel<HomeworkDetailResponse> detail(HomeworkDetailRequest request, Integer pageNo, Integer pageSize) {
        List<HomeworkDetailResponse> list = new ArrayList<HomeworkDetailResponse>();
        String status = request.getStatus();
        Integer totalRecords = 0;
        if ("Y".equals(status)) {
            list = jdbcAccess.find(SqlMapping.FIND_COMMIT_HOMEWORK, new RowMapper<HomeworkDetailResponse>() {
                @Override
                public HomeworkDetailResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    HomeworkDetailResponse response = new HomeworkDetailResponse();
                    response.setStuHomeworkId(rs.getString("id"));
                    response.setStudentCode(rs.getString("studentCode"));
                    response.setStudentName(rs.getString("studentName"));
                    response.setStudentAdminClassName(rs.getString("className"));
                    response.setLevel(rs.getString("LEVEL"));
                    return response;
                }
            }, request.getWorkId(), (pageNo - 1) * pageSize, pageSize);
            totalRecords = jdbcAccess.findInteger(SqlMapping.HOMEWORK_COMMIT_COUNT, request.getWorkId());

        } else {
            list = jdbcAccess.find(SqlMapping.FIND_UNCOMMIT_HOMEWORK, new RowMapper<HomeworkDetailResponse>() {
                @Override
                public HomeworkDetailResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    HomeworkDetailResponse response = new HomeworkDetailResponse();
                    response.setStudentCode(rs.getString("studentCode"));
                    response.setStudentName(rs.getString("studentName"));
                    response.setStudentAdminClassName(rs.getString("className"));
                    return response;
                }
            }, request.getClassId(), request.getWorkId(), (pageNo - 1) * pageSize, pageSize);
            totalRecords = jdbcAccess.findInteger(SqlMapping.HOMEWORK_UNCOMMIT_COUNT, request.getClassId(), request.getWorkId());
        }

        PageModel<HomeworkDetailResponse> pageModel = new PageModel<HomeworkDetailResponse>();
        pageModel.setRecords(list);
        pageModel.setTotalRecords((long) totalRecords);
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        return pageModel;
    }

    public HomeworkLevelResponse level(Integer id) {
        int cnt = jdbcAccess.findInteger(SqlMapping.HOMEWORK_EXISTS_ATTACHMENT, id);
        if (1 == cnt) {
            List<HomeworkLevelResponse> list = jdbcAccess.find(SqlMapping.CHECK_HOMEWORK_BY_ID, new RowMapper<HomeworkLevelResponse>() {
                @Override
                public HomeworkLevelResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    HomeworkLevelResponse response = new HomeworkLevelResponse();
                    response.setStudentCode(rs.getString("studentCode"));
                    response.setStudentName(rs.getString("studentName"));
                    response.setAdminClassName(rs.getString("className"));
                    response.setContent(rs.getString("content"));
                    response.setResName(rs.getString("resName"));
                    response.setCreateDate(rs.getTimestamp("create_date"));
                    response.setResLocation(rs.getString("resLocation").replace('\\', '/'));
                    response.setLevel(rs.getString("LEVEL"));
                    return response;
                }
            }, id);
            if (list.isEmpty())
                return new HomeworkLevelResponse();
            return list.get(0);
        } else {
            List<HomeworkLevelResponse> list = jdbcAccess.find(SqlMapping.CHECK_HOMEWORK_NO_ATTACHMENT_BY_ID, new RowMapper<HomeworkLevelResponse>() {
                @Override
                public HomeworkLevelResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    HomeworkLevelResponse response = new HomeworkLevelResponse();
                    response.setStudentCode(rs.getString("studentCode"));
                    response.setStudentName(rs.getString("studentName"));
                    response.setAdminClassName(rs.getString("className"));
                    response.setContent(rs.getString("content"));
                    response.setCreateDate(rs.getTimestamp("create_date"));
                    response.setLevel(rs.getString("LEVEL"));
                    return response;
                }
            }, id);
            if (list.isEmpty())
                return new HomeworkLevelResponse();
            return list.get(0);
        }
    }

    public Boolean level(HomeworkLevelRequest request) {
        int rows = jdbcAccess.execute(SqlMapping.UPDATE_HOMEWORK_LEVEL, request.getLevel(), request.getId());
        return 0 != rows;
    }

    public HomeworkRemoveResponse remove(Integer workId) {
        HomeworkRemoveResponse response = new HomeworkRemoveResponse();
        response.setFlag(true);
        try {
            List<String> stuAttLocation = jdbcAccess.find(SqlMapping.FIND_STUDENT_ATTACHMENT_RESLOCATION, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("resLocation");
                }
            }, workId);
            if (!stuAttLocation.isEmpty())
                response.setStuAttLocation(stuAttLocation);
            List<String> teaAttLocations = jdbcAccess.find(SqlMapping.FIND_TEACHER_ATTACHMENT_RESLOCATION, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("resLocation");
                }
            }, workId);
            if (!teaAttLocations.isEmpty()) {
                response.setTeaAttLocation(teaAttLocations.get(0));
            }
            List<Object[]> stuAttId = jdbcAccess.find(SqlMapping.FIND_STUDENT_ATTACHMENT_ID, new RowMapper<Object[]>() {
                @Override
                public Object[] mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new Object[] { rs.getInt("id") };
                }
            }, workId);
            List<Integer> teaAttIds = jdbcAccess.find(SqlMapping.FIND_TEACHER_ATTACHMENT_ID, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getInt("id");
                }
            }, workId);
            jdbcAccess.execute(SqlMapping.REMOVE_STUDENT_HOMEWOK, workId);
            jdbcAccess.execute(SqlMapping.REMOVE_HOMEWORK, workId);
            if (!stuAttId.isEmpty()) {
                jdbcAccess.batchExecute(SqlMapping.REMOVE_STUDENT_ATTACHMENT, stuAttId);
            }
            if (!teaAttIds.isEmpty()) {
                jdbcAccess.execute(SqlMapping.DELETE_TEACHER_ATTACHMENT, teaAttIds.get(0));
            }

        } catch (Exception e) {
            response.setFlag(false);
            return response;
        }

        return response;
    }

}
