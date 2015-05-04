package com.tianwen.commons.intranet.user.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.tianwen.commons.intranet.user.request.UserSearchRequest;
import com.tianwen.commons.intranet.user.response.UserResponse;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.core.page.PageModel;

@Repository
public class IntranetUserRepository {

    private JDBCAccess jdbcAccess;
    
    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

    public PageModel<UserResponse> users(UserSearchRequest request, Integer pageNo,
            Integer pageSize) {
        String selectSql = " SELECT t1.id AS userId,t1.userName,t1.qq,t1.phone,t1.email,t1.alive,t1.create_date,t2.id,t2.name,t2.code,t2.className FROM tianwen.user t1 INNER JOIN tianwen.student t2 ON t1.student_id = t2.id ";
        String searchSql = createUserSearchSql(request);
        String limitSql = " LIMIT ?,? ";
        List<UserResponse> users = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<UserResponse>() {
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserResponse user = new UserResponse();
                user.setUserId(rs.getInt("userId"));
                user.setUserName(rs.getString("userName"));
                user.setQq(rs.getString("qq"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setAlive(rs.getString("alive").equals("Y") ? true : false);
                user.setCreateDate(rs.getTimestamp("create_date"));
                user.setStudentId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setStudentCode(rs.getString("code"));
                user.setClassName(rs.getString("className"));
                return user;
            }
        }, (pageNo - 1) * pageSize, pageSize);
        //count
        String countSql = " SELECT COUNT(1) FROM tianwen.user t1 INNER JOIN tianwen.student t2 ON t1.student_id = t2.id ";
        Integer totalRecords = jdbcAccess.findInteger(countSql + searchSql);
        PageModel<UserResponse> pageModel = new PageModel<UserResponse>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setRecords(users);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }
    
    private String createUserSearchSql(UserSearchRequest request) {
        if (request == null) return "";
        StringBuilder sql = new StringBuilder(" WHERE 1 = 1 ");
        if (StringUtils.hasText(request.getName())) {
            sql.append(" AND t2.name LIKE '%" + request.getName() + "%' ");
        }
        if (StringUtils.hasText(request.getStudentCode())) {
            sql.append(" AND t2.code LIKE '%" + request.getStudentCode() + "%' ");
        }
        if (StringUtils.hasText(request.getUserName())) {
            sql.append(" AND t1.userName LIKE '%" + request.getUserName() + "%' ");
        }
        if (StringUtils.hasText(request.getAlive())) {
            sql.append(" AND t1.alive = '" + request.getAlive().toUpperCase() + "' ");
        }
        return sql.toString();
    }
    
    public boolean alive(Integer userId, String status) {
		
		
		int raws = jdbcAccess.execute(SqlMapping.UPDATE_USER_ALIVE_STATUS, status, userId);
		
		if (raws > 0)
			return true;
		
		return false;
	}

	public Integer userNumber() {
		String sql = " SELECT COUNT(1) FROM tianwen.`user` ";
		return jdbcAccess.findInteger(sql);
	}

}
