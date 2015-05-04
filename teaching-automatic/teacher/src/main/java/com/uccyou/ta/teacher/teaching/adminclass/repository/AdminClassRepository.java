package com.uccyou.ta.teacher.teaching.adminclass.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.ta.teacher.teaching.admin.request.AddAdminClassRequest;
import com.uccyou.ta.teacher.teaching.admin.request.ChangeAdminClassNameRequest;

@Repository
public final class AdminClassRepository {
    
    private JDBCAccess jdbcAccess;
   
    @Inject
    public void setJDBCAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

    public Integer add(AddAdminClassRequest request) {
        int  rows = jdbcAccess.execute(SqlMapping.ADD_ADMIN_CLASS, request.getTeachingClassId(), request.getAdminClassName());
        if ( 0 == rows) return 0;
        Integer adminClassId = jdbcAccess.findUniqueResult(SqlMapping.FIND_ADMIN_CLASS_ID, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("id");
            }
        }, request.getTeachingClassId(), request.getAdminClassName());
        return adminClassId;
    }

    public boolean change(ChangeAdminClassNameRequest request) {
        int rows = jdbcAccess.execute(SqlMapping.UPDATE_ADMIN_CLASS, request.getAdminClassName(), request.getAdminClassId());
        return 0 != rows;
    }

    public Boolean remove(Integer adminClassId) {
        int cnt = jdbcAccess.findInteger(SqlMapping.ADMIN_CLASS_EXISTS_STUDENT, adminClassId);
        if (0 != cnt) return false;
        int rows = jdbcAccess.execute(SqlMapping.REMOVE_ADMIN_CLASS_BY_ID, adminClassId);
        return 0 != rows;
    }

    public Boolean clean(Integer adminClassId) {
        try {
            jdbcAccess.execute(SqlMapping.CLEAN_STUDENT_ABSENT_RECORD, adminClassId);
            jdbcAccess.execute(SqlMapping.CLEAN_ADMIN_CLASS, adminClassId);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
