package ccst.sh.post.domain.response;

import ccst.sh.post.domain.Post;
import ccst.sh.post.domain.PostUser;

public class PostListResponse {
    
    private Post post;
    
    private PostUser user;
   
    private Boolean availd; //点赞是否可用
    
    private int likeNums;
    
    private int commentNums;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public PostUser getUser() {
        return user;
    }

    public void setUser(PostUser user) {
        this.user = user;
    }

    public Boolean getAvaild() {
        return availd;
    }

    public void setAvaild(Boolean availd) {
        this.availd = availd;
    }

    public int getLikeNums() {
        return likeNums;
    }

    public void setLikeNums(int likeNums) {
        this.likeNums = likeNums;
    }

    public int getCommentNums() {
        return commentNums;
    }

    public void setCommentNums(int commentNums) {
        this.commentNums = commentNums;
    }
}
