package ccst.sh.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ccst.sh.admin.domain.request.AdminNotice;
import ccst.sh.admin.domain.response.LoginResponse;
import ccst.sh.admin.domain.response.NoticesResponse;
import ccst.sh.common.utils.MD5;

@Repository
public class AdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public LoginResponse login(String username, String password) {
        List<LoginResponse> list = jdbcTemplate.query(
                AdminSqlMapping.LOGIN, new RowMapper<LoginResponse>() {
                    @Override
                    public LoginResponse mapRow(ResultSet rs, int rows) throws SQLException {
                        LoginResponse response = new LoginResponse();
                        response.setAdminId(rs.getInt("id"));
                        response.setAdminName(rs.getString("NAME"));
                        return response;
                    }
                }, username, MD5.getMD5Code(password));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return new LoginResponse();
        }
    }

    public List<NoticesResponse> getNotices(Integer adminId) {
        List<NoticesResponse> list = jdbcTemplate.query(AdminSqlMapping.FIND_NOTICES, 
                new RowMapper<NoticesResponse>() {
                    @Override
                    public NoticesResponse mapRow(ResultSet rs, int rows) throws SQLException {
                        NoticesResponse  response = new NoticesResponse();
                        response.setNoticeId(rs.getInt("noticeid"));
                        response.setTopic(rs.getString("topic"));
                        response.setContent(rs.getString("content"));
                        response.setCreateDate(rs.getDate("create_date"));
                        return response;
                    }
                }, adminId);
        return list;
    }

    public Boolean addNotice(Integer adminId, AdminNotice notice) {
        Integer classId = jdbcTemplate.queryForObject(AdminSqlMapping.FIND_CLASS_ID, 
                new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rows) throws SQLException {
                        return rs.getInt("class_id");
                    }
                }, adminId);
        Integer rows = jdbcTemplate.update(AdminSqlMapping.INSERT_NOTICE, 
                    notice.getTopic(), notice.getContent(), classId, new Date());
        return 0 != rows;
    }

    public Boolean deleteNotice(Integer noticeId) {
        return 0 != jdbcTemplate.update(AdminSqlMapping.DELECT_NOTICE, noticeId);
    }
}
