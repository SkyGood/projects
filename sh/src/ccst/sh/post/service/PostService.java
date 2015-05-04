package ccst.sh.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ccst.sh.post.dao.PostDao;
import ccst.sh.post.domain.request.CommentCreateRequest;
import ccst.sh.post.domain.request.PostCreateRequest;
import ccst.sh.post.domain.response.PostByUserResponse;
import ccst.sh.post.domain.response.PostCommentListResponse;
import ccst.sh.post.domain.response.PostListResponse;

@Service
@Transactional(readOnly = true, rollbackFor = {Exception.class})
public class PostService {
    
    @Autowired
    private PostDao postdao;
    
    @Transactional
    public Boolean createPost(PostCreateRequest request) {
        return postdao.createPost(request);
    }
    
    @Transactional
    public Boolean opertaLike(Integer postId, Integer userId, String operate) {
        return postdao.operateLike( postId, userId, operate);
    }

    @Transactional
    public Boolean createComment(CommentCreateRequest request) {
        return postdao.createComment(request);
    }

    public List<PostListResponse> getPost(Integer userId, Integer pageNo, Integer pageSize) {
        return postdao.getPost(userId, pageNo, pageSize);
    }

    public List<PostCommentListResponse> getComment(Integer postId, Integer pageNo, Integer pageSize) {
        return postdao.getComment(postId, pageNo, pageSize);
    }

    public List<PostByUserResponse> geUsertPost(Integer userId, Integer pageNo, Integer pageSize) {
        return postdao.getUserPost(userId, pageNo, pageSize);
    }
    
    

}
