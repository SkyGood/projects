package com.uccyou.ta.admin.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.uccyou.core.crypto.MD5;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.core.io.ExcelIO;
import com.uccyou.core.io.FileTransfer;
import com.uccyou.core.page.PageModel;
import com.uccyou.core.service.EndPoint;
import com.uccyou.core.util.StringUtils;
import com.uccyou.ta.system.admin.request.AddUserRequest;
import com.uccyou.ta.system.admin.request.AdminStatusRequest;
import com.uccyou.ta.system.admin.request.ChangeUserRequest;
import com.uccyou.ta.system.admin.request.ImportDataRequest;
import com.uccyou.ta.system.admin.request.NewAdminRequest;
import com.uccyou.ta.system.admin.request.SearchAllUserRequest;
import com.uccyou.ta.system.admin.response.AdminInfoResponse;
import com.uccyou.ta.system.admin.response.ChangeUserResponse;
import com.uccyou.ta.system.admin.response.SearchAllUserResponse;
import com.uccyou.ta.system.admin.response.UserRemoveResponse;

@Repository
public class AdminRepository {

    private static Logger logger = LoggerFactory.getLogger(AdminRepository.class);
    private JDBCAccess jdbcAccess;
    private MD5 md5;
    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }
    @Inject
    public void setMd5(MD5 md5) {
        this.md5 = md5;
    }
    public int importData(ImportDataRequest request, HttpServletRequest req) {
        String filePath = request.getFilePath();
        String realPath = EndPoint.WEBSITE.getEndpoint() + filePath;
        realPath = realPath.replace('\\', '/');
        int importCount = 0;
        String tempFile = "";
        try {
            tempFile = FileTransfer.transfer(realPath, req);
            ExcelIO ei = new ExcelIO();
            importCount = ei.importData(tempFile, jdbcAccess,
                    md5.encrypt("123456"), request.getIdentity());
        } catch (Exception e) {
            logger.info(
                    "Import excel datas failed. Exception = {}, ExceptionInfo = {}",
                    new Object[] { e.getStackTrace(), e.getMessage() });
            return -1;
        }
        if (importCount > 0) {
            int maxId = jdbcAccess.findInteger(SqlMapping.IMPORT_MAX_ID);
            int beginId = maxId - importCount;
            jdbcAccess.execute(SqlMapping.HASH_USER_0, beginId);
            jdbcAccess.execute(SqlMapping.HASH_USER_1, beginId);
            jdbcAccess.execute(SqlMapping.HASH_USER_2, beginId);
        }
        FileTransfer.removeFile(tempFile);
        return importCount;
    }

    public Integer add(NewAdminRequest admin) {
        Integer cnt = jdbcAccess.findInteger(SqlMapping.ADMIN_ALREADY_EXISTS,
                admin.getAdminname());
        if (cnt > 0) return 0;
        cnt = jdbcAccess.findInteger(SqlMapping.ADMIN_COUNT);
        if (cnt > 8) return 0;
        Integer maxId = jdbcAccess.findInteger(SqlMapping.FIND_MAX_ID);
        String password = new MD5().encrypt("123456");
        int rows = jdbcAccess.execute(SqlMapping.ADD_ADMIN, maxId, admin.getAdminname(), password, new Date());
        if (0 == rows) return 0;
        return maxId;
    }

    public List<AdminInfoResponse> admin() {
        List<AdminInfoResponse> list = jdbcAccess.find(SqlMapping.ALL_ADMIN_INFO, new RowMapper<AdminInfoResponse>() {
            @Override
            public AdminInfoResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                AdminInfoResponse admin = new AdminInfoResponse();
                admin.setAdminId(rs.getInt("adminId"));
                admin.setUsername(rs.getString("username"));
                admin.setAlive(rs.getString("alive"));
                admin.setCreateDate(rs.getTimestamp("create_date"));
                return admin;
            }
        });
        return list;
    }

    public boolean change(AdminStatusRequest request) {
        int rows = jdbcAccess.execute(SqlMapping.CHANGE_STATUS, request.getStatus(), request.getAdmingId());
        return rows != 0;
    }

    public boolean remove(Integer adminId) {
        String minId = jdbcAccess.findString(SqlMapping.FIND_MIN_ID);
        int rows = jdbcAccess.execute(SqlMapping.UPDATE_SYSTEM_NOTICE, minId, adminId);
        if (0 == rows) return false;
        rows = jdbcAccess.execute(SqlMapping.REMOVE_ADMIN, adminId);
        return 0 != rows;
    }

    public boolean reset(Integer adminId) {
        int rows = jdbcAccess.execute(SqlMapping.RESET_ADMIN_PASSWD, md5.encrypt("123456"), adminId);
        return 0 != rows;
    }

    public PageModel<SearchAllUserResponse> allUser(SearchAllUserRequest request, Integer pageNo, Integer pageSize) {
        String selectSql = " SELECT id, identityCode, name, identity, phone, email, qq, alive  FROM teaching.user ";
        String totalSql = " SELECT COUNT(1) FROM teaching.user ";
        String searchSql = createAllUserSearchSql(request);
        String limitSql = " LIMIT ?, ? ";
        List<SearchAllUserResponse> list = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<SearchAllUserResponse>() {
            @Override
            public SearchAllUserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                SearchAllUserResponse response = new SearchAllUserResponse();
                response.setId(rs.getInt("id"));
                response.setIdentityCode(rs.getString("identityCode"));
                response.setName(rs.getString("name"));
                response.setIdentity(rs.getString("identity"));
                response.setPhone(rs.getString("phone"));
                response.setEmail(rs.getString("email"));
                response.setQq(rs.getString("qq"));
                response.setAlive(rs.getString("alive"));
                return response;
            }
        }, (pageNo - 1) * pageSize, pageSize);
        Integer totalRecords = jdbcAccess.findInteger(totalSql + searchSql);
        PageModel<SearchAllUserResponse> pageModel = new PageModel<SearchAllUserResponse>();
        pageModel.setRecords(list);
        pageModel.setTotalRecords((long) totalRecords);
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        return pageModel;
    }

    private String createAllUserSearchSql(SearchAllUserRequest request) {
        StringBuilder sql = new StringBuilder(" WHERE 1 = 1 ");
        if (StringUtils.hasText(request.getIdentityCode())) {
            sql.append(" AND identityCode LIKE '%" + request.getIdentityCode() + "%' ");
        }
        if (StringUtils.hasText(request.getName())) {
            sql.append(" AND name LIKE '%" + request.getName() + "%' ");
        }
        if (StringUtils.hasText(request.getIdentity())) {
            if (!"A".equals(request.getIdentity())) {
                sql.append(" AND identity = '" + request.getIdentity() + "' ");
            }
        }
        if (StringUtils.hasText(request.getPhone())) {
            sql.append(" AND phone = '" + request.getPhone() + "' ");
        }
        if (StringUtils.hasText(request.getQq())) {
            sql.append(" AND qq = '" + request.getQq() + "' ");
        }
        if (StringUtils.hasText(request.getEmail())) {
            sql.append(" AND email LIKE '%" + request.getEmail() + "%' ");
        }
        if (StringUtils.hasText(request.getAlive())) {
            sql.append(" AND alive = '" + request.getAlive() + "' ");
        }
        return sql.toString();
    }

    public Boolean userAlive(Integer userId, String status) {
        String identityCode = jdbcAccess.findString(SqlMapping.FIND_IDENTITY_CODE_BY_ID, userId);
        int rows = jdbcAccess.execute(SqlMapping.UPDATE_USER_ALIVE, status, identityCode);
        if (0 == rows) return false;
        String hashSql = createUserAliveSql(status, identityCode);
        rows = jdbcAccess.execute(hashSql);
        return 0 != rows;
    }

    private String createUserAliveSql(String status, String identityCode) {
        Integer tableNum = (int) (Long.parseLong(identityCode) % 3);
        return "UPDATE teaching.user" + tableNum + " SET alive = '" + status + "' WHERE identityCode = '" + identityCode + "' ";
    }

    public Boolean userReset(Integer userId) {
        String identityCode = jdbcAccess.findString(SqlMapping.FIND_IDENTITY_CODE_BY_ID, userId);
        int rows = jdbcAccess.execute(SqlMapping.RESET_USER_PASSWORD, md5.encrypt("123456"), identityCode);
        if (0 == rows) return false;
        String hashSql = creatUserResetSql(identityCode);
        rows = jdbcAccess.execute(hashSql);
        return 0 != rows;
    }

    private String creatUserResetSql(String identityCode) {
        Integer tabNum = (int) (Long.parseLong(identityCode) % 3);
        return "UPDATE teaching.user" + tabNum + " SET PASSWORD = '" + md5.encrypt("123456") + "' WHERE identityCode = '" + identityCode + "'";
    }

    public ChangeUserResponse userChange(Integer userId) {
        final String identityCode = jdbcAccess.findString(SqlMapping.FIND_IDENTITY_CODE_BY_ID, userId);
        String sql = createUserChangeSql(identityCode);
        ChangeUserResponse response = jdbcAccess.findUniqueResult(sql, new RowMapper<ChangeUserResponse>() {
            @Override
            public ChangeUserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                ChangeUserResponse response = new ChangeUserResponse();
                response.setId(rs.getInt("id"));
                response.setIdentityCode(identityCode);
                response.setName(rs.getString("NAME"));
                response.setIdentity(rs.getString("identity"));
                response.setPhone(rs.getString("phone"));
                response.setEmail(rs.getString("email"));
                response.setQq(rs.getString("qq"));
                return response;
            }
        });
        return response;
    }

    private String createUserChangeSql(String identityCode) {
        Integer tabNum = (int) (Long.parseLong(identityCode) % 3);
        return "SELECT id, NAME, identity, phone, email, qq FROM teaching.user" + tabNum + " WHERE identityCode = '" + identityCode + "'";
    }

    public Boolean userChange(ChangeUserRequest request) {
        String identityCode = jdbcAccess.findString(SqlMapping.FIND_IDENTITY_CODE_BY_ID, request.getId());
        Integer tabNum = (int) (Long.parseLong(identityCode) % 3);
        // 2，如果identityCode与原数据库相同
        if (identityCode.equals(request.getIdentityCode())) {
            // 1)相同，直接进行update
            int rows = jdbcAccess.execute(SqlMapping.UPDATE_USER_INFO, request.getName(), request.getPhone(), request.getEmail(), request.getQq(), request.getIdentity(), request.getId());
            if (0 == rows) return false;
            String sql = createOldCodeSql(request, tabNum);
            rows = jdbcAccess.execute(sql);
            return 0 != rows;
        } else {
            // 1)如果原来存在此学号，返回false
            String sql = createCodeIsExists(request.getIdentityCode());
            int rows = jdbcAccess.findInteger(sql);
            if (0 != rows) return false;
            sql = creDelOldTabSql(tabNum, request.getId());
            rows = jdbcAccess.execute(sql);
            if (0 == rows) return false;
            rows = jdbcAccess.execute(SqlMapping.UPDATE_USER_NEW_CODE_INFO, request.getIdentityCode(), md5.encrypt("123456"), request.getName(), request.getPhone(), request.getEmail(), request.getQq(), request.getIdentity(), request.getId());
            if (0 == rows) return false;
            sql = creNewCodeTabSql(request);
            rows = jdbcAccess.execute(sql);
            return 0 != rows;
        }
    }

    private String creNewCodeTabSql(ChangeUserRequest request) {
        Integer tabNum = (int) (Long.parseLong(request.getIdentityCode()) % 3);
        return "INSERT INTO teaching.user" + tabNum + "(id, identityCode, PASSWORD, email, NAME, phone, qq, identity, alive) VALUES(" + request.getId() + ", '" + request.getIdentityCode() + "', '" + md5.encrypt("123456") + "', '" + request.getEmail() + "', '" + request.getName() + "', '" + request.getPhone() + "', '" + request.getQq() + "', '" + request.getIdentity() + "', 'Y')";
    }

    private String createCodeIsExists(String identityCode) {
        Integer tabNum = (int) (Long.parseLong(identityCode) % 3);
        return "SELECT COUNT(1) FROM teaching.user" + tabNum + " WHERE identityCode = '" + identityCode + "'";
    }

    private String creDelOldTabSql(Integer tabNum, Integer id) {
        return "DELETE FROM teaching.user" + tabNum + " WHERE id = " + id;
    }

    private String createOldCodeSql(ChangeUserRequest request, Integer tabNum) {
        return "UPDATE teaching.user" + tabNum + " SET name = '" + request.getName() + "', phone = '" + request.getPhone() + "', email = '" + request.getEmail() + "', qq = '" + request.getQq() + "', identity = '" + request.getIdentity() + "' WHERE id = " + request.getId();
    }

    public UserRemoveResponse userRemove(Integer userId) {
        UserRemoveResponse response = new UserRemoveResponse();
        //根据userId找到学生的学号
        String studentCode = jdbcAccess.findString(SqlMapping.FIND_IDENTITY_CODE_BY_ID, userId);
        //判断班级中是否存在此学生
        //找到所有的学生List<Integer>studentId
        List<Integer> stuList = jdbcAccess.find(SqlMapping.FIND_STUDENT_ID, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getInt("id");
            }
        } , studentCode);
        //①如果班级中不存在,直接删除user表和散列表
        if (!stuList.isEmpty()) {
        //②如果班级中存在学生
            List<String> resList = jdbcAccess.find(SqlMapping.FIND_RESOURCES, new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getString("resLocation");
                }
            }, userId);
            response.setResLocation(resList);
            //根据学生code删除表studentHomework
            jdbcAccess.execute(SqlMapping.REMOVE_USER_STUDENT_HOMEWORK, studentCode);
            //根据userId删除resource记录
            jdbcAccess.execute(SqlMapping.REMOVE_USER_RESOURCES, userId);
            //根据学生id删除表tempcheckingradom
            for (Integer studentId : stuList) {
                jdbcAccess.execute(SqlMapping.REMOVE_RANDOM, studentId);
            }
            //根据学生id删除absentRecord
            for (Integer studentId : stuList) {
                jdbcAccess.execute(SqlMapping.REMOVE_ABSENT_NOTE_RECORD, studentId);
            }
            //根据学生code删除表classstudent
            jdbcAccess.execute(SqlMapping.REMOVE_USER_IN_CLASS_STUDENT, studentCode);
        }
        //根据学生code删除user表和散列user子表
        jdbcAccess.execute(SqlMapping.REMOVE_USER, userId);
        String sql = creRemoveUser(studentCode);
        jdbcAccess.execute(sql);
        response.setFlag(true);
        return response;
    }
    
    private String creRemoveUser(String identityCode) {
        Integer tabNum = (int) (Long.parseLong(identityCode) % 3);
        return "DELETE FROM teaching.user" + tabNum + " WHERE identityCode = '" + identityCode + "'";
    }

    public Integer userAdd(AddUserRequest request) {
        int rows = jdbcAccess.findInteger(SqlMapping.USER_ALREADY_EXISTS, request.getIdentityCode());
        if (0 < rows) return 0;
        rows = jdbcAccess.execute(SqlMapping.ADMIN_ADD_USER, request.getIdentityCode(), md5.encrypt("123456"), request.getEmail(), request.getName(), request.getPhone(), request.getQq(), request.getIdentity());
        if (0 == rows) return 0;
        Integer maxId = jdbcAccess.findInteger(SqlMapping.FIND_USER_MAX_ID);
        String sql = ceateAddUserHashSql(request, maxId);
        rows = jdbcAccess.execute(sql);
        if (0 == rows) return 0;
        return maxId;
    }

    private String ceateAddUserHashSql(AddUserRequest request, Integer maxId) {
        Integer tabNum = (int) Long.parseLong(request.getIdentityCode()) % 3;
        return "INSERT INTO teaching.user" + tabNum + "(id, identityCode, PASSWORD, email, NAME, phone, qq, identity, alive)  VALUES(" + maxId + ", '" + request.getIdentityCode() + "', '" + md5.encrypt("123456") + "', '" + request.getEmail() + "', '" + request.getName() + "', '" + request.getPhone() + "', '" + request.getQq() + "', '" + request.getIdentity() + "', 'Y')";
    }

}
