package com.uccyou.ta.login.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.ta.system.login.request.UserLoginRequest;
import com.uccyou.ta.system.login.response.LoginResponse;

@Repository
public class LoginRepository {

    private JDBCAccess jdbcAccess;

    private String hashSql(int tableNum) {
        return " SELECT id,NAME FROM teaching.user" + tableNum + " WHERE identityCode = ? AND PASSWORD = ? AND identity = ? AND alive = 'Y'";
    }

    public LoginResponse login(UserLoginRequest request) {
        List<LoginResponse> results = new ArrayList<LoginResponse>();
        Integer tableNum = null;
        if (!"A".equals(request.getIdentity())) {
            try {
                tableNum = (int) ((Long.parseLong(request.getIdentityCode())) % 3);
            } catch (Exception e) {
                return new LoginResponse();
            }
            results = jdbcAccess.find(hashSql(tableNum), new RowMapper<LoginResponse>() {
                @Override
                public LoginResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    LoginResponse response = new LoginResponse();
                    response.setUserId(rs.getInt("id"));
                    response.setName(rs.getString("name"));
                    return response;
                }
            }, request.getIdentityCode(), request.getPassWord(), request.getIdentity());
        } else {
            results = jdbcAccess.find(SqlMapping.ADMIN_LOGIN, new RowMapper<LoginResponse>() {
                @Override
                public LoginResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                    LoginResponse response = new LoginResponse();
                    response.setAdminId(rs.getInt("adminId"));
                    response.setImportData("Y".equals(rs.getString("importData")) ? true : false);
                    return response;
                }
            }, request.getIdentityCode(), request.getPassWord());
        }

        if (!results.isEmpty()) {
            if (!"A".equals(request.getIdentity())) {
                LoginResponse res = results.get(0);
                res.setTableNum(tableNum);
                return res;
            }
            return results.get(0);
        } else {
            return new LoginResponse();
        }
    }

    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }
}
