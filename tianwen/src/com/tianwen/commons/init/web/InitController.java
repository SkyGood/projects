package com.tianwen.commons.init.web;


/*
 *接口状态： 全部测试通过
 *时间： 2014年9月28日 23:01:30
 */

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tianwen.commons.init.request.CategoryRequest;
import com.tianwen.commons.init.response.CategoryResponse;
import com.tianwen.commons.init.response.OrganizationResponse;
import com.tianwen.commons.init.response.QuestionnaireResponse;
import com.tianwen.commons.init.service.InitService;

@RestController
public class InitController {

    private InitService initService;

    //获取所有问卷类别
    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryResponse> categories() {
        return initService.categories();
    }

    /*//获取指定类别下组织方   (请求方式原为POST)
    @RequestMapping(value = "/orgs/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizationResponse> orgs(@PathVariable("id") Integer id) {
        return initService.orgs(id);
    }*/

    //请求最新15张问卷
    @RequestMapping(value = "/news/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuestionnaireResponse> news(@PathVariable("userId") Integer userId) {
    	return initService.news(userId);
    }

    //增添问卷类型
    @RequestMapping(value = "/addcategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean addCategory(@RequestBody CategoryRequest categoryRequest) {
    	return initService.addCategory(categoryRequest);
    }

    @Inject
    public void setInitService(InitService initService) {
        this.initService = initService;
    }
}
