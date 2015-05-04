package com.tianwen.commons.init.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.tianwen.commons.init.request.CategoryRequest;
import com.tianwen.commons.init.response.CategoryResponse;
import com.tianwen.commons.init.response.OrganizationResponse;
import com.tianwen.commons.init.response.QuestionnaireResponse;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;

@Repository
public class InitRepository {

    private JDBCAccess jdbcAccess;

    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

	public List<CategoryResponse> categories() {
		List <CategoryResponse> list = jdbcAccess.find(SqlMapping.QUESTIONNAIRE_CATEGORY_QUERY, new RowMapper<CategoryResponse>() {
			@Override
			public CategoryResponse mapRow(ResultSet rs, int rows) throws SQLException {
				CategoryResponse categoryResponse = new CategoryResponse();
				categoryResponse.setId(rs.getInt("id"));
				categoryResponse.setName(rs.getString("name"));
				return categoryResponse;
			}
		});
		return list;
	}
	/*
	public List<OrganizationResponse> orgs(Integer id) {
		List<OrganizationResponse> list = jdbcAccess.find(SqlMapping.ORGANIZATION_QUERY_BY_QUESTIONNAIREID, new RowMapper<OrganizationResponse>() {
			@Override
			public OrganizationResponse mapRow(ResultSet rs, int rows) throws SQLException {
				OrganizationResponse organizationResponse = new OrganizationResponse();
				organizationResponse.setId(rs.getInt("id"));
				organizationResponse.setName(rs.getString("NAME"));
				organizationResponse.setCategoryId(rs.getInt("category_id"));
				return organizationResponse;
			}
		}, id);
		return list;
	}*/

	public List<QuestionnaireResponse> news(final Integer userId) {
		List<QuestionnaireResponse> list = jdbcAccess.find(SqlMapping.NEWS_QUESTIONNAIRE, new RowMapper<QuestionnaireResponse>() {
			@Override
			public QuestionnaireResponse mapRow(ResultSet rs, int rows)
					throws SQLException {
				Integer qnId = rs.getInt("id");
				int cnt = jdbcAccess.findInteger(SqlMapping.QUESTION_ALREADY_COMMIT, userId, qnId);
				QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
				questionnaireResponse.setId(qnId);
				questionnaireResponse.setCode(rs.getString("code"));
				questionnaireResponse.setTopic(rs.getString("topic"));
				questionnaireResponse.setDate(rs.getDate("date"));
				if (0 == cnt){
					questionnaireResponse.setDone(true);
				} else {
					questionnaireResponse.setDone(false);
				}
				return questionnaireResponse;
			}
		});
		return list;
	}

	public boolean addCategory(CategoryRequest categoryRequest) {
		String name = categoryRequest.getName();
		int cnt = jdbcAccess.findInteger(SqlMapping.CATEGORY_IS_EXISTS, name);
		if(0 < cnt) {
			return false;
		}
		int rows = jdbcAccess.execute(SqlMapping.ADD_CATEGORY, name, new Date());
		if(0 == rows) {
			return false;
		}
		
		return true;
		
	}

}
