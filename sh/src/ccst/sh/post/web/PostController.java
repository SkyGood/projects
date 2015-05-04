package ccst.sh.post.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ccst.sh.post.domain.request.CommentCreateRequest;
import ccst.sh.post.domain.request.PostCreateRequest;
import ccst.sh.post.domain.response.PostByUserResponse;
import ccst.sh.post.domain.response.PostCommentListResponse;
import ccst.sh.post.domain.response.PostListResponse;
import ccst.sh.post.service.PostService;

@RestController
public class PostController {
    
    @Autowired
    private PostService  postservice;
    
    @RequestMapping (value = "/post/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean createPost(@RequestBody PostCreateRequest request) {
        return postservice.createPost(request);
    }
    
    @RequestMapping (value = "/post/{postId}/{userId}/{operate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean opertaLike(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId, @PathVariable("operate") String operate) {
        return postservice.opertaLike(postId, userId, operate);
    }
    
    @RequestMapping (value = "/post/comment", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean createComment(@RequestBody CommentCreateRequest request) {
        return postservice.createComment(request);
    }
    
    @RequestMapping (value = "/posts/{userId}/{pageno}/{pagesize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostListResponse> getPost(@PathVariable("userId") Integer userId, @PathVariable("pageno") Integer pageNo, @PathVariable("pagesize") Integer pageSize) {
        return postservice.getPost(userId, pageNo, pageSize);
    }
    
    @RequestMapping (value = "/posts/user/{userId}/{pageno}/{pagesize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostByUserResponse> getUserPost(@PathVariable("userId") Integer userId, @PathVariable("pageno") Integer pageNo, @PathVariable("pagesize") Integer pageSize) {
        return postservice.geUsertPost(userId, pageNo, pageSize);
    }
    
    @RequestMapping (value = "/post/comments/{postId}/{pageNo}/{pageSize}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostCommentListResponse>  getComment(@PathVariable("postId") Integer postId, @PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize) {
        return postservice.getComment(postId, pageNo, pageSize);
    }
}
