package com.tianwen.commons.init.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.tianwen.commons.init.repository.InitRepository;
import com.tianwen.commons.init.request.CategoryRequest;
import com.tianwen.commons.init.response.CategoryResponse;
import com.tianwen.commons.init.response.OrganizationResponse;
import com.tianwen.commons.init.response.QuestionnaireResponse;

@Service
@Transactional(readOnly = true)
public class InitService {

    private InitRepository initRepository;

    @Inject
    public void setInitRepository(InitRepository initRepository) {
        this.initRepository = initRepository;
    }

	public List<CategoryResponse> categories() {
		return initRepository.categories();
	}
/*
	public List<OrganizationResponse> orgs(Integer id) {
		return initRepository.orgs(id);
	}*/

	public List<QuestionnaireResponse> news(Integer userId) {
		return initRepository.news(userId);
	}

	@Transactional
	public boolean addCategory(CategoryRequest categoryRequest) {
		return initRepository.addCategory(categoryRequest);
	}

}
