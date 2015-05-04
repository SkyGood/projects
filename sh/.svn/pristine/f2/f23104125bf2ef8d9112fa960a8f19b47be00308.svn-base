package ccst.sh.system.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ccst.sh.common.utils.PageModel;
import ccst.sh.common.utils.StringUtils;
import ccst.sh.system.user.domain.request.SysUserSearchRequest;
import ccst.sh.system.user.domain.response.SysUserSearchResponse;

@Repository
public class SysUserDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PageModel<SysUserSearchResponse> sysUser(SysUserSearchRequest request,  Integer pageNo, Integer pageSize) {
        String searchSql = createSearchSql(request);
        String limitSql = " LIMIT ?,? ";
        List<SysUserSearchResponse> list = jdbcTemplate.query(SysUserSqlMapping.SELECT_USERS + searchSql + limitSql, 
                new RowMapper<SysUserSearchResponse>() {
                    @Override
                    public SysUserSearchResponse mapRow(ResultSet rs, int rows) throws SQLException {
                        SysUserSearchResponse response = new SysUserSearchResponse();
                        response.setUserId(rs.getInt("userId"));
                        response.setUserName(rs.getString("username"));
                        response.setTel(rs.getString("tel"));
                        response.setEmail(rs.getString("email"));
                        response.setQq(rs.getString("qq"));
                        response.setAlive(rs.getString("alive"));
                        return response;
                    }
                }, (pageNo - 1) * pageSize, pageSize);
        Integer totalRecords = jdbcTemplate.queryForObject(SysUserSqlMapping.COUNT_USERS  + searchSql, Integer.class);
        PageModel<SysUserSearchResponse> userPage = new PageModel<SysUserSearchResponse>();
        userPage.setList(list);
        userPage.setPageNo(pageNo);
        userPage.setPageSize(pageSize);
        userPage.setTotalRecords(totalRecords);
        return userPage;
    }

    private String createSearchSql(SysUserSearchRequest request) {
        StringBuilder searchSql = new StringBuilder(" WHERE 1=1 ");
        if (StringUtils.hasText(request.getUserName())) {
            searchSql.append(" AND username LIKE '%" + request.getUserName() + "%' ");
        }
        if (StringUtils.hasText(request.getAlive())) {
            searchSql.append(" AND alive = " + request.getAlive());
        }
        return searchSql.toString();
    }

    public Boolean sysUserAlive(Integer userId, String alive) {
        Boolean toAlive = false;
        if ("A".equals(alive)) { /*开启*/
            toAlive = true;
        }
        return 0 != jdbcTemplate.update(SysUserSqlMapping.USER_ALIVE, toAlive, userId);
    }
}
