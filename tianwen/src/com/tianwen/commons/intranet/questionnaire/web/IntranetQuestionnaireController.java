package com.tianwen.commons.intranet.questionnaire.web;

/*
 *接口状态：全部测试通过
 *时间：2014年9月28日 22:58:38 
 */


import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tianwen.commons.intranet.questionnaire.request.QuestionnaireSearchRequest;
import com.tianwen.commons.intranet.questionnaire.response.QuestionnaireResponse;
import com.tianwen.commons.intranet.questionnaire.service.IntranetQuestionnaireService;
import com.uccyou.core.page.PageModel;

@RestController
public class IntranetQuestionnaireController {

    private IntranetQuestionnaireService intranetQuestionnaireService;

    //分页条件搜索问卷
    @RequestMapping(value = "/intranetquestionnaires/{pageNo}/{pageSize}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<QuestionnaireResponse> questionnaires(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, @RequestBody QuestionnaireSearchRequest search, HttpServletRequest request) {
        request.getSession().setAttribute("qnsearch", search);
        return intranetQuestionnaireService.questionnaire(search, pageNo, pageSize);
    }
    
    /*//分页按钮条件搜索问卷
    @RequestMapping(value = "/intranetquestionnaires/{pageNo}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<QuestionnaireResponse> questionnaires(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, HttpServletRequest request) {
        QuestionnaireSearchRequest search = (QuestionnaireSearchRequest) request.getSession().getAttribute("qnsearch");
        return intranetQuestionnaireService.questionnaire(search, pageNo, pageSize);
    }*/
    
    //查询问卷
    @RequestMapping(value = "/intranetquestionnaires/{pageNo}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<QuestionnaireResponse> questionnaires(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, HttpServletRequest request) {
        request.getSession().setAttribute("qnsearch", null);
        return intranetQuestionnaireService.questionnaire(null, pageNo, pageSize);
    }
    
    //问卷总数
    @RequestMapping(value = "/questionnairesnumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer questionnairesnumber() {
    	return intranetQuestionnaireService.questionnairesnumber();
    }
    
    //根据用户的ID获取到其所创建的问卷
    @RequestMapping(value = "/userquestionnaires/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuestionnaireResponse> myQuestionnaires(@PathVariable("userId") Integer id) {
    	return intranetQuestionnaireService.myQuestionnaires(id);
    } 
    
    @Inject
    public void setIntranetQuestionnaireService(
            IntranetQuestionnaireService intranetQuestionnaireService) {
        this.intranetQuestionnaireService = intranetQuestionnaireService;
    }
}
