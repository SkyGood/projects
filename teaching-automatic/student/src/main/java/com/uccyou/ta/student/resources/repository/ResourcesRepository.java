package com.uccyou.ta.student.resources.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.util.StringUtils;
import com.uccyou.ta.student.resources.request.SearchRequest;
import com.uccyou.ta.student.resources.response.SearchResponse;

@Repository
public class ResourcesRepository {

    private JDBCAccess jdbcAccess;

    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

    public PageModel<SearchResponse> search(SearchRequest request, Integer pageNo, Integer pageSize) {
        String selectSql = " SELECT t1.resName, t1.resLocation, t1.create_date FROM teaching.resources t1 INNER JOIN teaching.classresources t2 ON t1.id = t2.res_id ";
        String searchSql = createSearchSql(request);
        String limitSql = " LIMIT ?, ? ";
        List<SearchResponse> list = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<SearchResponse>() {
            @Override
            public SearchResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                SearchResponse response = new SearchResponse();
                response.setResName(rs.getString("resName"));
                response.setResLocation(rs.getString("resLocation").replace('\\', '/'));
                response.setCreateDate(rs.getString("create_date"));
                return response;
            }
        }, (pageNo - 1) * pageSize, pageSize);
        String totalSql = " SELECT COUNT(1) FROM teaching.resources t1 INNER JOIN teaching.classresources t2 ON t1.id = t2.res_id ";
        Integer totalRecords = jdbcAccess.findInteger(totalSql + searchSql);

        PageModel<SearchResponse> pageModel = new PageModel<SearchResponse>();
        pageModel.setRecords(list);
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }

    private String createSearchSql(SearchRequest request) {
        StringBuilder sql = new StringBuilder(" WHERE t2.class_id = " + request.getClassId() + " AND t1.type = 'R' AND t2.alive = 'Y' ");
        if (StringUtils.hasText(request.getResName())) {
            sql.append(" AND t1.resName LIKE '%" + request.getResName() + "%' ");
        }
        return sql.toString();
    }

}
