package com.tianwen.commons.questionnaire.web;

/*
 *接口状态: 全部接口调试通过
 *详细：修改一个BUG， 将QUESTIONNAIRE_DETAIL去掉了alive='y'的条件,使得关闭的问卷也可以进行查看
 *时间： 2014年9月28日 22:25:14 
 */


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.record.aggregates.PageSettingsBlock;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tianwen.commons.questionnaire.request.CreateAnswerSheetRequest;
import com.tianwen.commons.questionnaire.request.CreateQuestionnaireRequest;
import com.tianwen.commons.questionnaire.request.SearchQuestionnaireRequest;
import com.tianwen.commons.questionnaire.response.QuestionnaireDetailResponse;
import com.tianwen.commons.questionnaire.response.QuestionnaireResponse;
import com.tianwen.commons.questionnaire.service.QuestionnaireService;
import com.uccyou.core.page.PageModel;

@RestController
public class QuestionnaireController {

    private QuestionnaireService questionnaireService;

    //创建问卷
    @RequestMapping(value = "/addquestionnaire", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createQuestionnaire(@RequestBody CreateQuestionnaireRequest request) {
        return questionnaireService.create(request);
    }
    
    //提交问卷
    @RequestMapping(value = "/doquestionnaire", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean doquestionnaire(@RequestBody CreateAnswerSheetRequest request) {
        return questionnaireService.doquestionnaire(request);
    }
    
    //条件搜索问卷
    @RequestMapping(value = "/questionnaires/{userId}/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<QuestionnaireResponse> questionnaires(@RequestBody SearchQuestionnaireRequest search, @PathVariable("pageNo") Integer pageNo,
    													   @PathVariable("userId") Integer userId, @PathVariable("pageSize") Integer pageSize, HttpServletRequest request) {
    	return questionnaireService.questionnaires(userId, search, pageNo, pageSize);
    }
    
    /*//条件搜索问卷分页
    @RequestMapping(value = "/questionnaires/{pageNo}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<QuestionnaireResponse> questionnaires(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, HttpServletRequest request) {
        SearchQuestionnaireRequest search = (SearchQuestionnaireRequest) request.getSession().getAttribute("keyWordSearch");
        return questionnaireService.questionnaires(search, pageNo, pageSize);
    }
    */
    //根据问卷类型查询
    @RequestMapping(value = "/CategoryQnSearch/{userId}/{categoryId}/{pageNo}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<QuestionnaireResponse> SearchByCategoryId(@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId, 
    															@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
		return questionnaireService.SearchByCategoryId(userId, categoryId, pageNo, pageSize);
    }
    
    //问卷详情
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuestionnaireDetailResponse questionnaireDetail(@PathVariable("id") Integer id) {
        return questionnaireService.questionnaireDetail(id);
    }
    
    //关闭问卷
    @RequestMapping(value = "/close/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean close(@PathVariable("id") Integer id) {
    	return questionnaireService.closeQuestionnaire(id);
    }
    
    @Inject
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }
}
