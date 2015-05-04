package ccst.sh.system.post.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ccst.sh.common.constans.SessionConstans;
import ccst.sh.common.utils.PageModel;
import ccst.sh.system.post.domain.request.SysPostSearchRequest;
import ccst.sh.system.post.domain.response.SysCommentResponse;
import ccst.sh.system.post.domain.response.SysPostSearchResponse;
import ccst.sh.system.post.service.SysPostService;

@Controller
public class SysPostController {
    
    @Autowired
    private SysPostService sysPostService;
    
    @RequestMapping(value = "/system/post/all", method = RequestMethod.GET)
    public String postlist(HttpSession sesstion, Map<String, Object> map) {
        SysPostSearchRequest request = new SysPostSearchRequest();
        sesstion.setAttribute(SessionConstans.SYSTEM_POST_REQUEST, request);
        PageModel<SysPostSearchResponse> pageModel = sysPostService.post(request, 1, 10);
        map.put("pageModel", pageModel);
        map.put("pageUrl", "system/post/all");
        return "system/post/postlist";
    }
    
    @RequestMapping(value = "/system/post/all", method = RequestMethod.POST)
    public String postSearch(SysPostSearchRequest request, HttpSession sesstion, Map<String, Object> map) {
        sesstion.setAttribute(SessionConstans.SYSTEM_POST_REQUEST, request);
        PageModel<SysPostSearchResponse> pageModel = sysPostService.post(request, 1, 10);
        map.put("pageModel", pageModel);
        map.put("pageUrl", "system/post/all");
        return "system/post/postlist";
    }
    
    @RequestMapping(value = "/system/post/all/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public String postPage(HttpSession sesstion, Map<String, Object> map, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        SysPostSearchRequest request = (SysPostSearchRequest) sesstion.getAttribute(SessionConstans.SYSTEM_POST_REQUEST);
        PageModel<SysPostSearchResponse> pageModel = sysPostService.post(request, pageNo, pageSize);
        map.put("pageModel", pageModel);
        map.put("pageUrl", "system/post/all");
        return "system/post/postlist";
    }
    
    @RequestMapping(value = "/system/post/delete/{postId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean postDelete(@PathVariable("postId") Integer postId) {
        return sysPostService.postDelete(postId);
    }
    
    @RequestMapping(value = "/system/post/comment/{postId}", method = RequestMethod.GET)
    public String comment(Map<String, Object> map, @PathVariable("postId") Integer postId) {
        List<SysCommentResponse> comments = sysPostService.comment(postId);
        map.put("comments", comments);
        return "system/post/comments";
    }
    
    @RequestMapping(value = "/system/post/comment/delete/{commentId}", method = RequestMethod.GET)
    @ResponseBody
    public Boolean commentDelete(@PathVariable("commentId") Integer commentId) {
        return sysPostService.commentDelete(commentId); 
    }
}
