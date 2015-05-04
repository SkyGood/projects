package com.uccyou.ta.teacher.resources.repository;

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
import com.uccyou.ta.teacher.resource.request.ResourceChangeRequest;
import com.uccyou.ta.teacher.resource.request.ResourceReferenceRequest;
import com.uccyou.ta.teacher.resource.request.ResourceSearchRequest;
import com.uccyou.ta.teacher.resource.request.UploadRequest;
import com.uccyou.ta.teacher.resource.response.AllResourceResponse;
import com.uccyou.ta.teacher.resource.response.ResourceChangeResponse;
import com.uccyou.ta.teacher.resource.response.ResourceRemoveResponse;
import com.uccyou.ta.teacher.resource.response.ResourceSearchResponse;

@Repository
public final class ResourceRepository {
    private JDBCAccess jdbcAccess;

    @Inject
    public void setJDBCAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

    public Boolean upload(UploadRequest request) {
        Date date = new Date();
        int rows = jdbcAccess.execute(SqlMapping.UPLOAD_RESOURCE, request.getTeacherId(), request.getResName(), request.getResLocation(), date);
        if (0 == rows)
            return false;
        List<Integer> list = jdbcAccess.find(SqlMapping.FIND_RESOURCES_ID, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("id");
            }
        }, request.getResLocation(), date);
        if (list.isEmpty())
            return false;
        Integer resId = list.get(0);
        rows = jdbcAccess.execute(SqlMapping.UPLOAD_CLASS_RESOURCE, request.getClassId(), resId);
        return 0 != rows;
    }

    public PageModel<ResourceSearchResponse> search(ResourceSearchRequest request, Integer pageNo, Integer pageSize) {
        String selectSql = " SELECT t1.id, t1.resName, t1.create_date, t2.alive FROM teaching.resources t1 INNER JOIN teaching.classresources t2 ON t1.id = t2.res_id  ";
        String searchSql = createResourceSearchSql(request);
        String limitSql = " LIMIT ?, ? ";
        List<ResourceSearchResponse> list = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<ResourceSearchResponse>() {
            @Override
            public ResourceSearchResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResourceSearchResponse response = new ResourceSearchResponse();
                response.setResId(rs.getInt("id"));
                response.setResName(rs.getString("resName"));
                response.setCreateDate(rs.getTimestamp("create_date"));
                response.setAlive(rs.getString("alive"));
                return response;
            }
        }, (pageNo - 1) * pageSize, pageSize);

        String countSql = " SELECT COUNT(1) FROM teaching.resources t1 INNER JOIN teaching.classresources t2 ON t1.id = t2.res_id ";
        Integer totalRecords = jdbcAccess.findInteger(countSql + searchSql);
        PageModel<ResourceSearchResponse> pageModel = new PageModel<ResourceSearchResponse>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setRecords(list);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }

    private String createResourceSearchSql(ResourceSearchRequest request) {
        StringBuilder sql = new StringBuilder(" WHERE t2.class_id = " + request.getClassId() + " AND t1.user_id = " + request.getUserId() + " ");
        if (StringUtils.hasText(request.getResName())) {
            sql.append(" AND t1.resName LIKE '%" + request.getResName() + "%' ");
        }
        return sql.toString();
    }

    public Boolean alive(Integer resId, String status) {
        int rows = jdbcAccess.execute(SqlMapping.CHANGE_TEACHING_RESOURCE_ALIVE, status, resId);
        rows = jdbcAccess.execute(SqlMapping.CHANGE_RESOURCE_ALIVE, status, resId);
        return 0 != rows;
    }

    public ResourceChangeResponse find(Integer resId) {
        List<ResourceChangeResponse> list = jdbcAccess.find(SqlMapping.FIND_RESOURCE_BY_ID, new RowMapper<ResourceChangeResponse>() {
            @Override
            public ResourceChangeResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                ResourceChangeResponse response = new ResourceChangeResponse();
                response.setResName(rs.getString("resName"));
                return response;
            }
        }, resId);
        if (list.isEmpty()) {
            return new ResourceChangeResponse();
        }
        return list.get(0);
    }

    public Boolean change(ResourceChangeRequest request) {
        int rows = jdbcAccess.execute(SqlMapping.MODIFY_RESOURCE, request.getResName(), request.getResId());
        return 0 != rows;
    }

    public PageModel<AllResourceResponse> reference(ResourceReferenceRequest request, Integer pageNo, Integer pageSize) {
        PageModel<AllResourceResponse> pageModel = new PageModel<AllResourceResponse>();
        List<AllResourceResponse> list = jdbcAccess.find(SqlMapping.FIND_ALL_RESOURCES, new RowMapper<AllResourceResponse>() {
            @Override
            public AllResourceResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                AllResourceResponse response = new AllResourceResponse();
                response.setResId(rs.getInt("id"));
                response.setResName(rs.getString("resName"));
                response.setDate(rs.getTimestamp("create_date"));
                return response;
            }
        }, request.getTeacherId(), request.getClassId(), (pageNo - 1) * pageSize, pageSize);
        Integer totalRecords = jdbcAccess.findInteger(SqlMapping.FIND_ALL_COUNT, request.getTeacherId(), request.getClassId());
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setRecords(list);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }

    public Boolean reference(Integer classId, Integer resId) {
        int cnt = jdbcAccess.findInteger(SqlMapping.RESOURCE_IS_EXISTS, classId, resId);
        if (0 < cnt)
            return false;
        int rows = jdbcAccess.execute(SqlMapping.REFERENCE_RESOURCE, classId, resId);
        return 0 != rows;
    }

    public ResourceRemoveResponse remove(Integer classId, Integer resId) {
        ResourceRemoveResponse response = new ResourceRemoveResponse();
        response.setFlag(true);
        int cnt = jdbcAccess.findInteger(SqlMapping.RESOURCE_REFERENCE_EXISTS, resId);
        try {
            jdbcAccess.execute(SqlMapping.REMOVE_CLASS_RESOURCES, classId, resId);
            if (1 == cnt) {
                String resLocation = jdbcAccess.findString(SqlMapping.FIND_RESOURCES_LOCATION, resId);
                response.setResLocation(resLocation);
                jdbcAccess.execute(SqlMapping.REMOVE_RESOURCES, resId);
            }
        } catch (Exception e) {
            response.setFlag(false);
            return response;
        }
        return response;
    }
}
