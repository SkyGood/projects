package com.uccyou.ta.register.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.ta.system.register.request.UserRegisterRequest;

@Repository
public class RegisterRepository {

    private JDBCAccess jdbcAccess;
    
    public int register(UserRegisterRequest request) {
        int checkRegistered = jdbcAccess.findInteger(SqlMapping.CHECK_USER_REGISTERED, request.getIdentityCode(), request.getIdentity());
        if (checkRegistered != 0) {
            int rows = jdbcAccess.execute(SqlMapping.USER_REGISTER, request.getUserName(), request.getPassWord(), new Date(), request.getIdentityCode());
            if (rows != 0) {
                return jdbcAccess.findUniqueResult(SqlMapping.GET_USER_REGISTER_ID, new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getInt("id");
                    }
                }, request.getUserName(), request.getIdentityCode());
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public boolean checkUserName(String userName) {
        int row = jdbcAccess.findInteger(SqlMapping.CHECK_REGISTER_USERNAME, userName);
        return row == 0;
    }
    
    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

}
