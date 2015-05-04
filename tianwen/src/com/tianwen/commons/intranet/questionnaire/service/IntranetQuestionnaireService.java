package com.tianwen.commons.intranet.questionnaire.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.tianwen.commons.intranet.questionnaire.repository.IntranetQuestionnaireRepository;
import com.tianwen.commons.intranet.questionnaire.request.QuestionnaireSearchRequest;
import com.tianwen.commons.intranet.questionnaire.response.QuestionnaireResponse;
import com.uccyou.core.page.PageModel;

@Service
public class IntranetQuestionnaireService {

    private static IntranetQuestionnaireRepository intranetQuestionnaireRepository;

    @Inject
    public void setIntranetQuestionnaireRepository(
            IntranetQuestionnaireRepository intranetQuestionnaireRepository) {
        this.intranetQuestionnaireRepository = intranetQuestionnaireRepository;
    }

	public PageModel<QuestionnaireResponse> questionnaire(
			QuestionnaireSearchRequest search, int pageNo, int pageSize) {
		return intranetQuestionnaireRepository.questionnaire(search, pageNo, pageSize);
	}

	public Integer questionnairesnumber() {
		return intranetQuestionnaireRepository.questionnairesnumber();
	}

	public List<QuestionnaireResponse> myQuestionnaires(Integer id) {
		return intranetQuestionnaireRepository.myQuestionnaires(id);
	}
    
}
