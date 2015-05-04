package com.tianwen.commons.intranet.analyze.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianwen.commons.intranet.analyze.repository.AnalyzeRepository;
import com.tianwen.commons.intranet.analyze.response.AnalyzeFillAnswerResponse;
import com.tianwen.commons.intranet.analyze.response.AnalyzeQuestionResponse;
import com.tianwen.commons.intranet.analyze.response.AnalyzeQuestionnairesResponse;
import com.uccyou.core.page.PageModel;

@Service
@Transactional(readOnly = true)
public class AnalyzeService {

    private AnalyzeRepository analyzeRepository;
    
    @Inject
    public void setAnalyzeRepository(AnalyzeRepository analyzeRepository) {
        this.analyzeRepository = analyzeRepository;
    }

    public AnalyzeQuestionnairesResponse analyze(Integer id, Integer pageSize) {
        return analyzeRepository.analyze(id, pageSize);
    }

	public PageModel<AnalyzeFillAnswerResponse> fill(Integer id, Integer pageNo, Integer pageSize) {
		return analyzeRepository.fill(id, pageNo, pageSize);
	}

}
