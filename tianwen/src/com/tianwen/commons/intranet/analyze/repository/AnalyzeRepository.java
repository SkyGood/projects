package com.tianwen.commons.intranet.analyze.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.tianwen.commons.intranet.analyze.response.AnalyzeChooseResponse;
import com.tianwen.commons.intranet.analyze.response.AnalyzeFillAnswerResponse;
import com.tianwen.commons.intranet.analyze.response.AnalyzeQuestionResponse;
import com.tianwen.commons.intranet.analyze.response.AnalyzeQuestionnairesResponse;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.core.page.PageModel;

@Repository
public class AnalyzeRepository {

    private JDBCAccess jdbcAccess;
    
    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

    public AnalyzeQuestionnairesResponse analyze(Integer id, Integer pageSize) {
    	
    	String topic = jdbcAccess.findString(SqlMapping.QUESTIONNAIRES_TOPIC, id);
        List<AnalyzeQuestionResponse> response = jdbcAccess.find(SqlMapping.FIND_QUESTION_INFO, new RowMapper<AnalyzeQuestionResponse>() {
            @Override
            public AnalyzeQuestionResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                AnalyzeQuestionResponse a = new AnalyzeQuestionResponse();
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                a.setQuestionType(rs.getString("question_type"));
                return a;
            }
        }, id);
        
        for (AnalyzeQuestionResponse q : response) {
            if (q.getQuestionType().equals("F")) {
                continue;
            } else {
                List<AnalyzeChooseResponse> choose = jdbcAccess.find(SqlMapping.FIND_CHOOSE_INFO, new RowMapper<AnalyzeChooseResponse>() {
                    @Override
                    public AnalyzeChooseResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                        AnalyzeChooseResponse c = new AnalyzeChooseResponse();
                        c.setId(rs.getInt("id"));
                        c.setDetail(rs.getString("detail"));
                        return c;
                    }
                }, q.getId());
                q.setChoose(choose);
            }
        }
        
        for (AnalyzeQuestionResponse q : response) {
            List<AnalyzeChooseResponse> choose = q.getChoose();
            if (choose == null || choose.isEmpty()) {
                List<String> contents = jdbcAccess.find(SqlMapping.GET_FILL_ANSWER, new RowMapper<String>() {
                    @Override
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getString("content");
                    }
                }, q.getId(), pageSize);
                q.setContents(contents);
            } else {
                for (AnalyzeChooseResponse c : choose) {
                    int count = jdbcAccess.findInteger(SqlMapping.GET_CHOOSE_COUNT, c.getId(), q.getId());
                    c.setCount(count);
                }
            }
        }
        AnalyzeQuestionnairesResponse analyRes = new AnalyzeQuestionnairesResponse(); 
        analyRes.setTopic(topic);
        analyRes.setQuestions(response);
        return analyRes;
    }
	public PageModel<AnalyzeFillAnswerResponse> fill(Integer id, Integer pageNo, Integer pageSize) {
		List<AnalyzeFillAnswerResponse> list = jdbcAccess.find(SqlMapping.FILL_ANSWER_DETAIL, new RowMapper<AnalyzeFillAnswerResponse>() {
			@Override
			public AnalyzeFillAnswerResponse mapRow(ResultSet rs, int rows)
					throws SQLException {
				AnalyzeFillAnswerResponse response = new AnalyzeFillAnswerResponse();
				response.setContent(rs.getString("content"));
				return response;
			}
		}, id, (pageNo - 1) * pageSize, pageSize);
		
		int totalRecords = jdbcAccess.findInteger(SqlMapping.FILL_ANSWER_COUNT, id);
		
		PageModel<AnalyzeFillAnswerResponse> pageModel = new PageModel<AnalyzeFillAnswerResponse>();
		pageModel.setPageNo(pageNo);
		pageModel.setPageSize(pageSize);
		pageModel.setRecords(list);
		pageModel.setTotalRecords( (long) totalRecords);
		return pageModel;
	}

}
