package ccst.sh.post.domain.response;

import ccst.sh.post.domain.Post;

public class PostByUserResponse {
    private Post post;
   
    private Boolean availd; //点赞是否可用
    
    private int likeNums;
    
    private int commentNums;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
