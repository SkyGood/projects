package com.tianwen.commons.intranet.analyze.web;


/*
 * 接口状态：测试通过
 * 时间：2014年9月28日 23:00:22
 */
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tianwen.commons.intranet.analyze.response.AnalyzeFillAnswerResponse;
import com.tianwen.commons.intranet.analyze.response.AnalyzeQuestionResponse;
import com.tianwen.commons.intranet.analyze.response.AnalyzeQuestionnairesResponse;
import com.tianwen.commons.intranet.analyze.service.AnalyzeService;
import com.uccyou.core.page.PageModel;

@RestController
public class AnalyzeController {

    private AnalyzeService analyzeService;
    
    @Inject
    public void setAnalyzeService(AnalyzeService analyzeService) {
        this.analyzeService = analyzeService;
    }

    @RequestMapping(value = "/analyze/questionnaires/{id}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AnalyzeQuestionnairesResponse analyze(@PathVariable("id") Integer id,@PathVariable("pageSize") Integer pageSize) {
        return analyzeService.analyze(id, pageSize);
    }
    
    @RequestMapping(value = "/analyze/fillanswer/{id}/{pageNo}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageModel<AnalyzeFillAnswerResponse> fill(@PathVariable("id") Integer id, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
    	return analyzeService.fill(id, pageNo, pageSize);
    }
}
