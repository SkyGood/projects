package com.uccyou.ta.user.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.uccyou.core.crypto.MD5;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.ta.system.user.request.ResetPwdRequest;
import com.uccyou.ta.system.user.request.UserInfoUpdateRequest;
import com.uccyou.ta.system.user.response.UserInfoResponse;

@Repository
public class UserRepository {

    private JDBCAccess jdbcAccess;
    
    private String hashSql(int tableNum) {
        return "SELECT phone,qq,email,create_date FROM teaching.user" + tableNum + " WHERE id = ?";
    }
    
    public UserInfoResponse center(Integer userId, Integer tableNum) {
        return jdbcAccess.findUniqueResult(hashSql(tableNum), new RowMapper<UserInfoResponse>() {
            @Override
            public UserInfoResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserInfoResponse response = new UserInfoResponse();
                response.setPhone(rs.getString("phone"));
                response.setEmail(rs.getString("email"));
                response.setQq(rs.getString("qq"));
                return response;
            }
        }, userId);
    }
    
    public boolean update(UserInfoUpdateRequest request) {
        String hashSql = "UPDATE teaching.user" + request.getTableNum() + " SET email = ?,phone = ?,qq = ? WHERE id = ?";
        int row = jdbcAccess.execute(hashSql, request.getEmail(), request.getPhone(), request.getQq(), request.getUserId());
        return row != 0;
    }
    
    public Boolean rePwd(ResetPwdRequest request) {
        String oldSql = createOldHashSql(request);
        int rows = jdbcAccess.findInteger(oldSql);
        if (0 == rows) return false;
        String newSql = createNewHashSql(request);
        rows = jdbcAccess.execute(newSql);
        return 0 != rows;
    }
    
    private String createNewHashSql(ResetPwdRequest request) {
        int tabNum = request.getUserId() % 3; 
        String newPwd = new MD5().encrypt(request.getNewPwd());
        String sql = "UPDATE teaching.user" + tabNum + " SET PASSWORD = '" + newPwd + "' WHERE id = " + request.getUserId();
        return sql;
    }

    private String createOldHashSql(ResetPwdRequest request) {
        int tabNum = request.getUserId() % 3;
        String oldPwd = new MD5().encrypt(request.getOldPwd());
        String sql = "SELECT COUNT(1) FROM user" + tabNum + " WHERE PASSWORD = '" + oldPwd + "' AND id = " + request.getUserId();
        return sql;
    }

    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

}
