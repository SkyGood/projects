package com.tianwen.commons.user.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.rmi.CORBA.Stub;

import org.springframework.stereotype.Repository;
import com.tianwen.commons.user.request.PasswordModifyRequest;
import com.tianwen.commons.user.request.UserLoginRequest;
import com.tianwen.commons.user.request.UserModifyRequest;
import com.tianwen.commons.user.request.UserRegisterRequest;
import com.tianwen.commons.user.response.UserInfoResponse;
import com.uccyou.core.crypto.MD5;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;

@Repository
public class UserRepository {

	private MD5 md5;

	private JDBCAccess jdbcAccess;

	/* injections */
	@Inject
	public void setMd5(MD5 md5) {
		this.md5 = md5;
	}

	@Inject
	public void setJdbcAccess(JDBCAccess jdbcAccess) {
		this.jdbcAccess = jdbcAccess;
	}

	/* checkUsername */
	public boolean checkUserName(String userName) {
		int num = jdbcAccess.findInteger(SqlMapping.CHECK_USERNAME_REPEATED, userName);
		if (0 < num)
			return false;

		return true;
	}

	public UserInfoResponse login(UserLoginRequest request) {
		String userName = request.getUserName();
		String passWord = md5.encrypt(request.getPassWord());

		List<UserInfoResponse> list = jdbcAccess.find(SqlMapping.LOGIN_CHECK_BY_USER,
				new RowMapper<UserInfoResponse>() {

					@Override
					public UserInfoResponse mapRow(ResultSet rs, int rows)
							throws SQLException {

						UserInfoResponse userInfoResponse = new UserInfoResponse();
						setUserInfo(rs, userInfoResponse);
						return userInfoResponse;
					}
				}, userName, passWord, "Y");

		if (0 < list.size()) {
			return list.get(0);
		}
		
		list = jdbcAccess.find(SqlMapping.LOGIN_CHECK_BY_CODE,
				new RowMapper<UserInfoResponse>() {

					@Override
					public UserInfoResponse mapRow(ResultSet rs, int rows)
							throws SQLException {

						UserInfoResponse userInfoResponse = new UserInfoResponse();
						setUserInfo(rs, userInfoResponse);
						return userInfoResponse;
					}
				}, userName, passWord, "Y");

		if (0 < list.size()) {
			return list.get(0);
		}
		return new UserInfoResponse();
	}

	/* check student code is ok */
	public boolean checkStudentCode(String studentCode) {
		int num = jdbcAccess.findInteger(SqlMapping.CHECK_USERNAME_CODE_EXISTS, studentCode);
		if (num == 0) return false;
		num = jdbcAccess.findInteger(SqlMapping.CHECK_USERNAME_ALREADY_REGISTER, studentCode);
		if (num > 0) return false;
		return true;
	}

	/* register user */
	public UserInfoResponse register(UserRegisterRequest request) {
		
		if (!checkStudentCode(request.getStudentCode())) {  
			return new UserInfoResponse();
		}
		if (!checkUserName(request.getUserName())) {
			return new UserInfoResponse();
		}
		
		String code = request.getStudentCode();
		String passWord = md5.encrypt(request.getPassWord());
		String userName = request.getUserName();
		
		/*find out student_id by code*/
		List<Integer> idList = jdbcAccess.find(SqlMapping.GET_STUDENT_ID, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rows)
					throws SQLException {
				return rs.getInt("id");
			}
		}, code);
		if (0 == idList.size()) {
			return new UserInfoResponse();
		}
		/*register user*/
		int student_id = idList.get(0);
		int rows = jdbcAccess.execute(SqlMapping.REGISTER_STUDNET, student_id, userName,
				passWord, new Date());
		if (0 == rows) {
			return new UserInfoResponse();
		}
		
		/*select user_id*/
		List<Integer> userList = jdbcAccess.find(SqlMapping.GET_USER_ID_BY_REGISTER_INFO, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rows) throws SQLException {
				return rs.getInt("id");
			}
		}, student_id);
		if (0 == userList.size()) {
			return new UserInfoResponse();
		}
		int userId = userList.get(0); 
		UserInfoResponse response = new UserInfoResponse();
		response.setUserName(userName);
		response.setCreateDate(new Date());
		response.setId(userId);
		response.setAlive(true);
		
		return response;
	}
	
	/*update user basic message*/
	public UserInfoResponse updateUserMessage(
			UserModifyRequest userModifyRequest) {
		int rows = jdbcAccess.execute(SqlMapping.MODIFY_USER, userModifyRequest.getQq(), userModifyRequest.getPhone(), userModifyRequest.getEmail(), userModifyRequest.getId());
		if (0 == rows) {
			return new UserInfoResponse();
		}
		UserInfoResponse userInfoResponse = new UserInfoResponse();
		userInfoResponse.setQq(userModifyRequest.getQq());
		userInfoResponse.setPhone(userModifyRequest.getPhone());
		userInfoResponse.setEmail(userModifyRequest.getEmail());
		return userInfoResponse;
	}

	/*update password*/
	public boolean updatePassword(PasswordModifyRequest passwordModifyRequest) {

		int rows = jdbcAccess.execute( SqlMapping.MODIFY_PASSWORD, md5.encrypt(passwordModifyRequest.getNewPassword()) , passwordModifyRequest.getId(), md5.encrypt(passwordModifyRequest.getOldPassword()) );
		
		if( 0 == rows)
		{
			return false;
		}
		return true;
	}
	
	private void setUserInfo(ResultSet rs,
			UserInfoResponse userInfoResponse)
			throws SQLException {
		userInfoResponse.setId(rs.getInt("id"));
		userInfoResponse.setAlive(rs.getString("alive").equalsIgnoreCase("Y") ? true : false);
		userInfoResponse.setCreateDate(rs
				.getTimestamp("create_date"));
		userInfoResponse.setEmail(rs.getString("email"));
		userInfoResponse.setPhone(rs.getString("phone"));
		userInfoResponse.setQq(rs.getString("qq"));
		userInfoResponse.setUserName(rs.getString("userName"));
		userInfoResponse.setStudentCode(rs.getString("code"));
	}
}
