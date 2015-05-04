package com.tianwen.commons.questionnaire.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.tianwen.commons.questionnaire.request.CreateAnswerDetailRequest;
import com.tianwen.commons.questionnaire.request.CreateAnswerSheetRequest;
import com.tianwen.commons.questionnaire.request.CreateChooseRequest;
import com.tianwen.commons.questionnaire.request.CreateQuestionAnswer;
import com.tianwen.commons.questionnaire.request.CreateQuestionRequest;
import com.tianwen.commons.questionnaire.request.CreateQuestionnaireRequest;
import com.tianwen.commons.questionnaire.request.SearchQuestionnaireRequest;
import com.tianwen.commons.questionnaire.response.ChooseResponse;
import com.tianwen.commons.questionnaire.response.QuestionResponse;
import com.tianwen.commons.questionnaire.response.QuestionnaireDetailResponse;
import com.tianwen.commons.questionnaire.response.QuestionnaireResponse;
import com.uccyou.core.database.jdbc.JDBCAccess;
import com.uccyou.core.database.jdbc.RowMapper;
import com.uccyou.core.page.PageModel;

@Repository
public class QuestionnaireRepository {

    private JDBCAccess jdbcAccess;

    public String create(CreateQuestionnaireRequest request) {
        //save questionnaire info
        String questionnaireCode = generateQuestionnaireCode();
        Date saveDate = new Date();
        jdbcAccess.execute(SqlMapping.SAVE_QUESTIONNAIRE_INFO, questionnaireCode, request.getTopic(), saveDate, request.getUserId(), request.getCategoryId());
        int questionnaireId = jdbcAccess.findInteger(SqlMapping.GET_QUESTIONNAIRE_ID, request.getTopic(), request.getUserId(), saveDate);
        //save questions
        List<CreateQuestionRequest> questions = request.getQuestions();
        for (CreateQuestionRequest question : questions) {
            jdbcAccess.execute(SqlMapping.SAVE_QUESTIONS, questionnaireId, question.getTitle(), question.getQuestionType());
            int questionId = jdbcAccess.findInteger(SqlMapping.GET_QUESTION_ID, question.getTitle(), question.getQuestionType(), questionnaireId);
            //save choose
            List<CreateChooseRequest> choose = question.getChoose();
            if (choose != null && !choose.isEmpty()) {
                for (CreateChooseRequest c : choose) {
                    jdbcAccess.execute(SqlMapping.SAVE_CHOOSE, questionId, c.getDetail());
                }
            }
        }
        //save operation record
        jdbcAccess.execute(SqlMapping.SAVE_CREATE_QUESTIONNAIRE_OPERATION_RECORD, request.getUserId(), questionnaireId, new Date());
        return questionnaireCode;
    }
    
    private String generateQuestionnaireCode() {
        String uuid = UUID.randomUUID().toString();
        jdbcAccess.execute(SqlMapping.SAVE_UUID, uuid);
        int insertId = jdbcAccess.findInteger(SqlMapping.GET_UUID_ID, uuid);
        return 10000 + insertId + "";
    }
    
    public boolean doquestionnaire(CreateAnswerSheetRequest request) {
    	//if user already answer,return false
    	int rows = jdbcAccess.findInteger(SqlMapping.USER_ALREADY_ANSWER, request.getUserId(), request.getQuestionnaireId());
    	if (0 < rows) {
    		return false;
    	}
        //save operation record
        jdbcAccess.execute(SqlMapping.SAVE_DO_QUESTIONNAIRE_OPERATION_RECORD, request.getUserId(), request.getQuestionnaireId(), new Date());
        //save answers
        List<CreateQuestionAnswer> questions = request.getQuestions();
        for (CreateQuestionAnswer question : questions) {
            List<CreateAnswerDetailRequest> answers = question.getAnswers();
            for (CreateAnswerDetailRequest answer : answers) {
                //different question type
                if (question.getQuestionType().equals("S") || question.getQuestionType().equals("M")) {
                    jdbcAccess.execute(SqlMapping.SAVE_SM_ANSWER, answer.getAnswerId(), question.getQuestionId(), request.getUserId());
                } else {
                    jdbcAccess.execute(SqlMapping.SAVE_FILL_ANSWER, question.getQuestionId(), request.getUserId(), answer.getContent());
                }
            }
        }
        return true;
    }
    
    public PageModel<QuestionnaireResponse> questionnaires(
            final Integer userId, SearchQuestionnaireRequest request, Integer pageNo, Integer pageSize) {
        String selectSql = " SELECT id,CODE,topic,create_date FROM tianwen.questionnaire ";
        String searchSql = createSerachSql(request);
        String limitSql = " LIMIT ?,? ";
        List<QuestionnaireResponse> response = jdbcAccess.find(selectSql + searchSql + limitSql, new RowMapper<QuestionnaireResponse>() {
            @Override
            public QuestionnaireResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                QuestionnaireResponse q = new QuestionnaireResponse();
                int qn_id = rs.getInt("id");
                q.setId(qn_id);
                q.setCode(rs.getString("code"));
                q.setTopic(rs.getString("topic"));
                q.setDate(rs.getDate("create_date"));
                int cnt = jdbcAccess.findInteger(SqlMapping.USER_ALREADY_ANSWER, userId, qn_id);
                if (0 == cnt) {
					q.setDone(true);
				} else {
					q.setDone(false);
				}
                return q;
            }
        }, (pageNo - 1) * pageSize, pageSize);
        String countSql = " SELECT COUNT(1) FROM tianwen.questionnaire ";
        Integer totalRecords = jdbcAccess.findInteger(countSql + searchSql);
        PageModel<QuestionnaireResponse> pageModel = new PageModel<QuestionnaireResponse>();
        pageModel.setPageNo(pageNo);
        pageModel.setPageSize(pageSize);
        pageModel.setRecords(response);
        pageModel.setTotalRecords((long) totalRecords);
        return pageModel;
    }
    
    public PageModel<QuestionnaireResponse> SearchByCategoryId(final Integer userId, Integer categoryId, Integer pageNo, Integer pageSize) {
    	PageModel<QuestionnaireResponse> pageModel = new PageModel<QuestionnaireResponse>();
    	pageModel.setTotalRecords((long) jdbcAccess.findInteger(SqlMapping.QNSEARCH_COUNT_BY_CATEGORY_ID, categoryId));
    	pageModel.setPageNo(pageNo);
    	pageModel.setPageSize(pageSize);
    	
    	List<QuestionnaireResponse> list = jdbcAccess.find(SqlMapping.QNSEARCH_BY_CATEGORY_ID, new RowMapper<QuestionnaireResponse>() {
			@Override
			public QuestionnaireResponse mapRow(ResultSet rs, int rows)
					throws SQLException {
				QuestionnaireResponse questionnaireResponse = new QuestionnaireResponse();
				questionnaireResponse.setId(rs.getInt("id"));
				questionnaireResponse.setCode(rs.getString("code"));
				questionnaireResponse.setTopic(rs.getString("topic"));
				questionnaireResponse.setDate(rs.getDate("create_date"));
				int cnt = jdbcAccess.findInteger(SqlMapping.USER_ALREADY_ANSWER, userId, questionnaireResponse.getId());
				if (0 == cnt) {
					questionnaireResponse.setDone(true);
				} else {
					questionnaireResponse.setDone(false);
				}
				
				return questionnaireResponse;
			}
		}, categoryId, (pageNo-1)*10, pageSize);
    	pageModel.setRecords(list);
    	return pageModel;
    }
    
    private String createSerachSql(SearchQuestionnaireRequest request) {
        StringBuilder sql = new StringBuilder(" WHERE alive = 'Y' ");
        if (request.getSearchType().equals("C")) {
            sql.append(" AND code = " + request.getCode() + " ");
        } else {
            sql.append(" AND topic LIKE '%" + request.getKeyWord() + "%' ");
        }
        return sql.toString();
    }
    
    @Inject
    public void setJdbcAccess(JDBCAccess jdbcAccess) {
        this.jdbcAccess = jdbcAccess;
    }

	public QuestionnaireDetailResponse questionnaireDetail(Integer id) {

	
		int rows = jdbcAccess.findInteger(SqlMapping.ID_IS_EXISTS, id);
		if(0 == rows) {
			return new QuestionnaireDetailResponse();
		}
		
		QuestionnaireDetailResponse questionnaireDetailResponse = jdbcAccess.findUniqueResult(SqlMapping.QUESTIONNAIRE_DETAIL, new RowMapper<QuestionnaireDetailResponse>() {

			@Override
			public QuestionnaireDetailResponse mapRow(ResultSet rs, int rows)
					throws SQLException {
				QuestionnaireDetailResponse questionnaireDetailResponse = new QuestionnaireDetailResponse();
				questionnaireDetailResponse.setId(rs.getInt("id"));
				questionnaireDetailResponse.setCode(rs.getString("code"));
				questionnaireDetailResponse.setTopic(rs.getString("topic"));
				return questionnaireDetailResponse;
			}
			
		}, id);
		
		List<QuestionResponse> list = jdbcAccess.find(SqlMapping.QUESTION_LIST, new RowMapper<QuestionResponse>() {

			@Override
			public QuestionResponse mapRow(ResultSet rs, int rows)
					throws SQLException {
				QuestionResponse questionResponse = new QuestionResponse();
				questionResponse.setId(rs.getInt("id"));
				questionResponse.setTitle(rs.getString("title"));
				questionResponse.setQuestionType(rs.getString("type"));
				return questionResponse;
			}
		}, id);
		
		for (QuestionResponse questionItem : list) {
			if (!"F".equalsIgnoreCase(questionItem.getQuestionType())) {
				List<ChooseResponse> chooseList = jdbcAccess.find(SqlMapping.CHOOSE_LIST, new RowMapper<ChooseResponse>() {

					@Override
					public ChooseResponse mapRow(ResultSet rs, int rows)
							throws SQLException {
						ChooseResponse chooseResponse = new ChooseResponse();
						chooseResponse.setId(rs.getInt("id"));
						chooseResponse.setDetail(rs.getString("detail"));
						return chooseResponse;
					}
				}, questionItem.getId());
				
				questionItem.setChoose(chooseList);
				
			}
		}
		
		questionnaireDetailResponse.setQuestions(list);
		
		return questionnaireDetailResponse;
	}

	public boolean closeQuestionnaire(Integer id) {
		
		int rows = jdbcAccess.execute(SqlMapping.CLOSE_QUESTIONNAIRE, id);
		
		if (0 == rows) {
			return false;
		}
		return true;
	}
}
