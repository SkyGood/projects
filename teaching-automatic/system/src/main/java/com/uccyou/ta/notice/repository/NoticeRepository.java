package com.uccyou.ta.notice.repository;

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
import com.uccyou.ta.system.notice.request.NoticeAddRequest;
import com.uccyou.ta.system.notice.request.NoticeChangeRequest;
import com.uccyou.ta.system.notice.request.NoticeSearchRequest;
import com.uccyou.ta.system.notice.response.NoticeChangeResponse;
import com.uccyou.ta.system.notice.response.NoticeSearchResponse;
import com.uccyou.ta.system.notice.response.RecentlyNoticeResponse;

@Repository
public class NoticeRepository {

    private JDBCAccess jdbcAccess;

    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

    public PageModel<NoticeSearchResponse> notice(NoticeSearchRequest request,
            Integer pageNo, Integer pageSize) {
        String selectSql = " SELECT t1.id, t1.title, t1.reader, t2.userName, t1.create_date FROM teaching.systemnotice t1  INNER JOIN teaching.admin t2 ON t1.admin_id = t2.adminId ";
        String searchSql = createSearchSql(request);
        String limitSql = "LIMIT ?, ?";
        List<NoticeSearchResponse> list = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<NoticeSearchResponse>() {
            @Override
            public NoticeSearchResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeSearchResponse response = new NoticeSearchResponse();
                response.setId(rs.getInt("id"));
                response.setTitle(rs.getString("title"));
                response.setReader(rs.getString("reader"));
                response.setAdminName(rs.getString("userName"));
                response.setCreateDate(rs.getTimestamp("create_date"));
                return response;
            }
        }, (pageNo - 1) * pageSize, pageSize);
        String countSql = " SELECT COUNT(1) FROM teaching.systemnotice t1  INNER JOIN teaching.admin t2 ON t1.admin_id = t2.adminId ";
        Integer totalRecords = jdbcAccess.findInteger(countSql + searchSql);
        PageModel<NoticeSearchResponse> pageModel = new PageModel<NoticeSearchResponse>();
        pageModel.setRecords(list);
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setTotalRecords( (long) totalRecords);
        return pageModel;
    }

    private String createSearchSql(NoticeSearchRequest request) {
        StringBuilder sql = new StringBuilder(" WHERE 1 = 1 ");
        if (StringUtils.hasText(request.getTitle())) {
            sql.append(" AND t1.title LIKE '%" + request.getTitle() + "%' ");
        }
        if (StringUtils.hasText(request.getAdminName())) {
            sql.append(" AND t2.userName LIKE '%" + request.getAdminName()
                    + "%' ");
        }
        if (StringUtils.hasText(request.getReader())) {
            sql.append(" AND t1.reader = '" + request.getReader() + "' ");
        }
        return sql.toString();
    }

    public Boolean add(NoticeAddRequest request) {
        int rows = jdbcAccess.execute(SqlMapping.ADD_NOTICE, request.getAdminId(), request.getTitle(), request.getContent(), request.getReader(), new Date());
        return 0 != rows;
    }

    public NoticeChangeResponse change(Integer id) {
        List<NoticeChangeResponse> list = jdbcAccess.find(SqlMapping.FIND_NOTICE_TO_CHANGE, new RowMapper<NoticeChangeResponse>() {
            @Override
            public NoticeChangeResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                NoticeChangeResponse response  = new NoticeChangeResponse();
                response.setId(rs.getInt("id"));
                response.setReader(rs.getString("reader"));
                response.setTitle(rs.getString("title"));
                response.setContent(rs.getString("content"));
                return response;
            }
        }, id);
        if (list.isEmpty()) return new NoticeChangeResponse();
        return list.get(0);
    }

    public Boolean change(NoticeChangeRequest request) {
        int rows = jdbcAccess.execute(SqlMapping.UPDATE_NOTICE, request.getTitle(), request.getReader(), request.getContent(), request.getId());
        return 0 != rows;
    }

    public Boolean remove(Integer id) {
        int rows = jdbcAccess.execute(SqlMapping.REMOVE_NOTICE, id);
        return 0 != rows;
    }

    public List<RecentlyNoticeResponse> recentNotice(String identity) {
        List<RecentlyNoticeResponse> list = new ArrayList<RecentlyNoticeResponse>();
        list = jdbcAccess.find(SqlMapping.RECENT_NOTICE, new RowMapper<RecentlyNoticeResponse>() {
            @Override
            public RecentlyNoticeResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                RecentlyNoticeResponse response = new RecentlyNoticeResponse();
                response.setTitle(rs.getString("title"));
                return response;
            }
        }, identity);
        return list;
    }
}
