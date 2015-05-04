package ccst.sh.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ccst.sh.common.utils.MD5;
import ccst.sh.user.domain.request.CheckCodeRequest;
import ccst.sh.user.domain.request.CheckNameRequest;
import ccst.sh.user.domain.request.UserChangeInfoRequest;
import ccst.sh.user.domain.request.UserChangePasswordRequest;
import ccst.sh.user.domain.request.UserLoginRequest;
import ccst.sh.user.domain.request.UserRegisterRequest;
import ccst.sh.user.domain.response.UserLoginResponse;
import ccst.sh.user.domain.response.UserNoticesResponse;
import ccst.sh.user.domain.response.UserRegisterResponse;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserLoginResponse login(UserLoginRequest request) {
        String password = MD5.getMD5Code(request.getUserPassWord());
        List<UserLoginResponse> list = jdbcTemplate.query(
                UserSqlMapping.USER_LOGIN, new RowMapper<UserLoginResponse>() {
                    @Override
                    public UserLoginResponse mapRow(ResultSet rs, int rows) throws SQLException {
                        UserLoginResponse response = new UserLoginResponse();
                        response.setUserId(rs.getInt("id"));
                        response.setAlive(rs.getString("alive"));
                        return response;
                    }
                }, request.getUserName(), password);
        if (list.isEmpty()) {
            UserLoginResponse response = new UserLoginResponse();
            response.setUserId(0);
            return response;
        }
        return list.get(0);
    }

    public Boolean checkName(CheckNameRequest request) {
        Integer isAccess = jdbcTemplate.queryForObject(UserSqlMapping.CHECK_NAME,
                new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rows) throws SQLException {
                        return rs.getInt("cnt");
                    }
                }, request.getUserName());
        return 0 == isAccess;
    }

    public Boolean checkCode(CheckCodeRequest request) {
        if (request.getCode().length() != 11) {
            return false;
        }
        String requestStudentId = jdbcTemplate.queryForObject(
                UserSqlMapping.FIND_STUDENT_ID, new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int rows) throws SQLException {
                        return rs.getString("student_id");
                    }
                }, request.getCode());
        if (requestStudentId.isEmpty()) {
            return false;
        }
        Integer isAccess = jdbcTemplate.queryForObject(UserSqlMapping.CHECK_CODE,
                new RowMapper<Integer>() {
                    @Override
                    public Integer mapRow(ResultSet rs, int rows) throws SQLException {
                        return rs.getInt("cnt");
                    }
                }, requestStudentId);
        return 0 == isAccess;
    }

    public UserRegisterResponse register(final UserRegisterRequest request) {
        List<String> studentIdList = jdbcTemplate.query(
                UserSqlMapping.FIND_STUDENT_ID, new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int rows) throws SQLException {
                        return rs.getString("student_id");
                    }
                }, request.getCode());
        if (studentIdList.isEmpty()) {
            return new UserRegisterResponse();
        }
        String studentId = studentIdList.get(0);
        String passWordMd5 = MD5.getMD5Code(request.getUserPassWord());
        Integer rows = jdbcTemplate.update(UserSqlMapping.INSERT_USER, request.getUserName(), passWordMd5, true, new Date(), studentId);
        if (0 == rows)  return new UserRegisterResponse();
        
        UserRegisterResponse response =  jdbcTemplate.queryForObject( UserSqlMapping.FIND_USER_ID,  new RowMapper<UserRegisterResponse>() {
            @Override
            public UserRegisterResponse mapRow(ResultSet rs, int rows) throws SQLException {
                UserRegisterResponse response = new UserRegisterResponse();
                response.setUserId(rs.getInt("userid"));
                response.setUserName(request.getUserName());
                return response;
            }
        }, request.getUserName());
        return response;
    }

    public Boolean chanegInfo(UserChangeInfoRequest request) {
        Integer rows =  jdbcTemplate.update(UserSqlMapping.CHANGE_USER_INFO, request.getTel(), request.getEmail(), request.getQq(), request.getUserId());
        return 0 != rows;
    }

    public Boolean checkOldPassword(UserChangePasswordRequest request) {
        String password = MD5.getMD5Code( request.getUserPassWord());
        Integer cnt = jdbcTemplate.queryForObject(UserSqlMapping.CHECK_OLD_PASSWORD, new RowMapper<Integer>() {
                @Override
                public Integer mapRow(ResultSet rs, int rows) throws SQLException {
                    return rs.getInt("cnt");
                }
            }, password, request.getUserId());
        return 0 != cnt;
    }
    
    public Boolean changePassword(UserChangePasswordRequest request) {
        String password = MD5.getMD5Code( request.getUserPassWord());
        Integer rows = jdbcTemplate.update(UserSqlMapping.UPDATE_USER_PASSWORD, password, request.getUserId());
        return 0 != rows;
    }

    public List<UserNoticesResponse> getNotices(Integer userId, Integer pageNo, Integer pageSize) {
        String limitSql = "LIMIT ?,?";
        List<UserNoticesResponse> list = jdbcTemplate.query(UserSqlMapping.FIND_NOTICES + limitSql, 
                new RowMapper<UserNoticesResponse>() {
                    @Override
                    public UserNoticesResponse mapRow(ResultSet rs, int rows) throws SQLException {
                        UserNoticesResponse response = new UserNoticesResponse();
                        response.setTopic(rs.getString("topic"));
                        response.setContent(rs.getString("content"));
                        response.setCreateDate(rs.getDate("create_date"));
                        return response;
                    } 
                }, userId, ( pageNo - 1) * pageSize, pageSize);
        return list;
    }
}
