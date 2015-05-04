package ccst.sh.post.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ccst.sh.post.domain.CommentUser;
import ccst.sh.post.domain.Post;
import ccst.sh.post.domain.PostUser;
import ccst.sh.post.domain.request.CommentCreateRequest;
import ccst.sh.post.domain.request.PostCreateRequest;
import ccst.sh.post.domain.response.PostByUserResponse;
import ccst.sh.post.domain.response.PostCommentListResponse;
import ccst.sh.post.domain.response.PostListResponse;

@Repository
public class PostDao {

    @Autowired
    private JdbcTemplate jdbctemplate;

    public Boolean createPost(PostCreateRequest request) {
        Integer rows = jdbctemplate.update(PostSqlMapping.INSET_POST,
                request.getContent(), new Date(), request.getUserId());
        return 0 != rows;
    }

    public Boolean operateLike(Integer postId, Integer userId, String operate) {
        if ("A".equals(operate)) { //add
            Integer rs = jdbctemplate.queryForObject(PostSqlMapping.FIND_LIKE, Integer.class,  userId, postId);
            if (0 != rs) {
                return false;
            } else {
                return 0 != jdbctemplate.update(PostSqlMapping.INSET_LIKE, userId, postId);
            }
        } else { //cancel
            return 0 != jdbctemplate.update(PostSqlMapping.DELETE_LIKE,  userId, postId);
        } 
    }

    public Boolean createComment(CommentCreateRequest request) {
        Integer rows = jdbctemplate.update(PostSqlMapping.INSERT_COMMENT,
                request.getContent(), new Date(), request.getPostId(), request.getUserId());
        return 0 != rows;
    }
    
    public List<PostListResponse> getPost(Integer userId, Integer pageNo, Integer pageSize) {
        String limitSql = " LIMIT ?, ? ";
        List<PostListResponse> list = jdbctemplate.query(PostSqlMapping.SELECT_POSTS_AND_USERS + limitSql,
                new RowMapper<PostListResponse>() {
                    @Override
                    public PostListResponse mapRow(ResultSet rs, int rows) throws SQLException {
                        PostListResponse response = new PostListResponse();
                        PostUser postUser = new PostUser();               
                        Post post = new Post();
                        postUser.setUserName(rs.getString("username"));   postUser.setTel(rs.getString("tel"));
                        postUser.setQq(rs.getString("qq"));               postUser.setEmail(rs.getString("email"));
                        post.setCreateDate(rs.getDate("create_date"));    post.setPostContent(rs.getString("content"));
                        post.setPostId(rs.getInt("postid"));             
                        response.setUser(postUser);                       response.setPost(post);
                        return response;
                    }
                }, (pageNo - 1) * pageSize, pageSize);
        
        for (PostListResponse response : list) {
            //1，找到post_id，放在response->post中
            Integer postId = response.getPost().getPostId();
            //2，对respponse的likeNum
            Integer likeNums = jdbctemplate.queryForObject(PostSqlMapping.FIND_LIKENUM_BY_POSTID, Integer.class, postId);
            //3，对commentnUM
            Integer commentNums = jdbctemplate.queryForObject(PostSqlMapping.FIND_COMMENTNUM_BY_POSTID, Integer.class, postId);
            //4，设置可不可用
            Integer availdLike = jdbctemplate.queryForObject(PostSqlMapping.FIND_USER_AVAILD, Integer.class, postId, userId);
            response.setLikeNums(likeNums);
            response.setCommentNums(commentNums);
            response.setAvaild(0 == availdLike ? true : false);
        }
        return list;
    }
    
    public List<PostByUserResponse> getUserPost(Integer userId, Integer pageNo, Integer pageSize) {
        String limitSql = " LIMIT ?, ? ";
        List<PostByUserResponse> list = jdbctemplate.query(PostSqlMapping.SELECT_POSTS_BY_USER + limitSql,
                new RowMapper<PostByUserResponse>() {
                    @Override
                    public PostByUserResponse mapRow(ResultSet rs, int rows) throws SQLException {
                        PostByUserResponse response = new PostByUserResponse();
                        Post post = new Post();
                        post.setCreateDate(rs.getDate("create_date"));    post.setPostContent(rs.getString("content"));
                        post.setPostId(rs.getInt("postid"));             
                        response.setPost(post);
                        return response;
                    }
                }, userId, (pageNo - 1) * pageSize, pageSize);
        for (PostByUserResponse response : list) {
            //1，找到post_id，放在response->post中
            Integer postId = response.getPost().getPostId();
            //2，对respponse的likeNum
            Integer likeNums = jdbctemplate.queryForObject(PostSqlMapping.FIND_LIKENUM_BY_POSTID, Integer.class, postId);
            //3，对commentnUM
            Integer commentNums = jdbctemplate.queryForObject(PostSqlMapping.FIND_COMMENTNUM_BY_POSTID, Integer.class, postId);
            //4，设置可不可用
            Integer availdLike = jdbctemplate.queryForObject(PostSqlMapping.FIND_USER_AVAILD, Integer.class, postId, userId);
            response.setLikeNums(likeNums);
            response.setCommentNums(commentNums);
            response.setAvaild(0 == availdLike ? true : false);
        }
        return list;
    }
    
    public List<PostCommentListResponse> getComment(Integer postId, Integer pageNo, Integer pageSize) {
        String limitSql = " LIMIT ?,? ";
        List<PostCommentListResponse> list = jdbctemplate.query(
                PostSqlMapping.SELECT_POST_COMMENTS + limitSql,
                new RowMapper<PostCommentListResponse>() {
                    @Override
                    public PostCommentListResponse mapRow(ResultSet rs, int rows) throws SQLException {
                        PostCommentListResponse response = new PostCommentListResponse();
                        CommentUser user = new CommentUser();
                        user.setUserName(rs.getString("username")); user.setEmail(rs.getString("email"));
                        user.setQq(rs.getString("qq"));             user.setTel(rs.getString("tel"));
                        response.setCommentUser(user);              response.setContent(rs.getString("content"));   
                        response.setCreateDate(rs.getDate("create_date"));
                        return response;
                    }
                }, postId, (pageNo - 1) * pageSize, pageSize);
        return list;
    }
}
