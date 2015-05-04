package com.tianwen.commons.intranet.questionnaire.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.tianwen.commons.intranet.questionnaire.request.QuestionnaireSearchRequest;
import com.tianwen.commons.intranet.questionnaire.response.QuestionnaireResponse;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.core.page.PageModel;

@Repository
public class IntranetQuestionnaireRepository {

    private JDBCAccess jdbcAccess;

    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

	public PageModel<QuestionnaireResponse> questionnaire (
			QuestionnaireSearchRequest search, int pageNo, int pageSize) {
			
			final String selectSql = " SELECT t1.id, t1.code, t1.topic, t1.create_date AS createDate, t3.NAME AS cateGoryName, t1.alive, t2.userName AS createBy  " +
									 " FROM tianwen.questionnaire t1 INNER JOIN tianwen.user t2 ON t2.id = t1.create_by INNER JOIN tianwen.category t3 ON t3.id = t1.category_id ";
			
			final String searchSql = createUserSearchSql(search);
			final String limitSql = " ORDER BY createDate DESC LIMIT ?, ? ";
			
			
			List<QuestionnaireResponse> list = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<QuestionnaireResponse>() {

				@Override
				public QuestionnaireResponse mapRow(ResultSet rs, int rows)
						throws SQLException {
					QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
					questionnaireResponse.setAlive(rs.getString("alive").equalsIgnoreCase("Y") ? true : false );
					questionnaireResponse.setCategoryName(rs.getString("cateGoryName"));
					questionnaireResponse.setCode(rs.getString("code"));
					questionnaireResponse.setCreateBy(rs.getString("createBy"));
					questionnaireResponse.setCreateDate(rs.getDate("createDate"));
					questionnaireResponse.setTopic(rs.getString("topic"));
					questionnaireResponse.setId(rs.getInt("id"));
					int count = jdbcAccess.findInteger(QustionSqlMpping.QUESTIONNIRE_COUNT, questionnaireResponse.getId());
					questionnaireResponse.setJoincount(count);
					
					return questionnaireResponse;
				}
			}, (pageNo-1)*pageSize, pageSize);
			
			//count
			String cntSql =  " SELECT COUNT(1) FROM tianwen.questionnaire t1 INNER JOIN tianwen.user t2 ON t2.id = t1.create_by INNER JOIN tianwen.category t3 ON t3.id = t1.category_id ";
			
			Long totalRecoders = (long) jdbcAccess.findInteger(cntSql + searchSql);
			PageModel<QuestionnaireResponse> pageModel = new PageModel<QuestionnaireResponse>();
			pageModel.setRecords(list);
			pageModel.setPageNo(pageNo);
			pageModel.setPageSize(pageSize);
			pageModel.setTotalRecords(totalRecoders);
			
		return pageModel;
	}

	private String createUserSearchSql(QuestionnaireSearchRequest search) {

		if (null == search) {
			return "";
		}
		StringBuilder sql = new StringBuilder(" WHERE 1 = 1 ");
		if (null != search.getCategoryId()) {
			sql.append(" AND t3.category_id = " + search.getCategoryId());
		}
		if (StringUtils.hasText(search.getTopic())) {
			sql.append(" AND t1.topic LIKE '%" + search.getTopic() + "%' ");
		}
		if (StringUtils.hasText(search.getAlive())) {
			sql.append(" AND t1.alive = '" + search.getAlive() + "' ");
		}
		if(null != search.getUserId()) {
			sql.append(" AND t2.id = "+search.getUserId());
		}
		return sql.toString();
	}

	public Integer questionnairesnumber() {
		String sql = " SELECT COUNT(1) FROM tianwen.questionnaire ";
		return jdbcAccess.findInteger(sql);
	}

	public List<QuestionnaireResponse> myQuestionnaires(final Integer id) {
		
		List<String> listUser = jdbcAccess.find(QustionSqlMpping.QUESTIONNAIRE_CREATE_BY, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rows) throws SQLException {
				return rs.getString("userName");
			}
		}, id);
		
		if(listUser.isEmpty()) {
			return new ArrayList<QuestionnaireResponse>();
		}
		final String userName = listUser.get(0);
		
		List<QuestionnaireResponse> list = jdbcAccess.find(QustionSqlMpping.USER_QUSTIONNAIRE, new RowMapper<QuestionnaireResponse>() {
			@Override
			public QuestionnaireResponse mapRow(ResultSet rs, int rows)
					throws SQLException {
				QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
				questionnaireResponse.setId(rs.getInt("id"));
				questionnaireResponse.setCode(rs.getString("code"));
				questionnaireResponse.setTopic(rs.getString("topic"));
				questionnaireResponse.setCreateDate(rs.getTimestamp("createDate"));
				questionnaireResponse.setCreateBy(userName);
				questionnaireResponse.setCategoryName(rs.getString("categoryName"));
				questionnaireResponse.setAlive(rs.getString("alive").equalsIgnoreCase("Y") ? true : false);
				int joincount = jdbcAccess.findInteger(QustionSqlMpping.JOIN_COUNT, rs.getInt("id"));
				questionnaireResponse.setJoincount(joincount);
				return questionnaireResponse;
			}
		}, id);
		
		return list;
	}
}

