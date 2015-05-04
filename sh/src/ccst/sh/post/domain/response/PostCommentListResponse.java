package ccst.sh.post.domain.response;

import java.util.Date;

import ccst.sh.post.domain.CommentUser;

public class PostCommentListResponse {
    
    private String content;

    private CommentUser commentUser; 
    
    private Date createDate;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CommentUser getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(CommentUser commentUser) {
        this.commentUser = commentUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
