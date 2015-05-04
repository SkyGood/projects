package com.tianwen.commons.questionnaire.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianwen.commons.questionnaire.repository.QuestionnaireRepository;
import com.tianwen.commons.questionnaire.request.CreateAnswerSheetRequest;
import com.tianwen.commons.questionnaire.request.CreateQuestionnaireRequest;
import com.tianwen.commons.questionnaire.request.SearchQuestionnaireRequest;
import com.tianwen.commons.questionnaire.response.QuestionnaireDetailResponse;
import com.tianwen.commons.questionnaire.response.QuestionnaireResponse;
import com.uccyou.core.page.PageModel;

@Service
@Transactional(readOnly = true)
public class QuestionnaireService {

    private QuestionnaireRepository questionnaireRepository;

    @Transactional
    public String create(CreateQuestionnaireRequest request) {
        return questionnaireRepository.create(request);
    }
    
    @Transactional
    public boolean doquestionnaire(CreateAnswerSheetRequest request) {
        return questionnaireRepository.doquestionnaire(request);
    }
    
    public PageModel<QuestionnaireResponse> questionnaires(
            Integer userId, SearchQuestionnaireRequest request, Integer pageNo, Integer pageSize) {
        return questionnaireRepository.questionnaires(userId, request, pageNo, pageSize);
    }
    
    @Inject
    public void setQuestionnaireRepository(
    		QuestionnaireRepository questionnaireRepository) {
    	this.questionnaireRepository = questionnaireRepository;
    }

	public QuestionnaireDetailResponse questionnaireDetail(Integer id) {
		return questionnaireRepository.questionnaireDetail(id);
	}

	@Transactional
	public boolean closeQuestionnaire(Integer id) {
		return questionnaireRepository.closeQuestionnaire(id);
	}

	public PageModel<QuestionnaireResponse> SearchByCategoryId(
			Integer userId, Integer categoryId, Integer pageNo, Integer pageSize) {
		return questionnaireRepository.SearchByCategoryId(userId, categoryId, pageNo, pageSize);
	}
}
