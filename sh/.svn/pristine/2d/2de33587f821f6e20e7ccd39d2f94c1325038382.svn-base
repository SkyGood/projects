package ccst.sh.system.post.dao;

public class SysPostSqlMapping {
    
    public static final String SELECT_POSTS = " SELECT t1.id AS postid, t1.create_date, t1.content, t2.username FROM sh.post t1 LEFT JOIN sh.user t2 ON t2.id = t1.create_by ";
    
    public static final String COUNT_POSTS = "SELECT COUNT(1) AS totalRecords FROM sh.post t1 LEFT JOIN sh.user t2 ON t2.id = t1.create_by ";
    
    public static final String SELECT_COMMENTS = " SELECT t1.id AS commentid, t1.content, t1.create_date, t2.username FROM sh.comment t1 LEFT JOIN sh.user t2 ON t1.user_id = t2.id WHERE post_id = ? ";
    
    public static final String DELETE_COMMENT_BY_POSTID = " DELETE FROM sh.comment WHERE post_id = ? ";
    
    public static final String DELETE_LIKES_BY_POSTID = " DELETE FROM sh.record WHERE post_id = ? ";
    
    public static final String DELETE_POST = " DELETE FROM sh.post WHERE id = ? ";
   
    public static final String DELETE_COMMENT = " DELETE FROM sh.comment WHERE id = ? ";
}
