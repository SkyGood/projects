package ccst.sh.post.dao;

public class PostSqlMapping {

    public static final String INSET_POST = "INSERT INTO sh.post (content,create_date,create_by) VALUES(?,?,?)";
    
    public static final String INSET_LIKE = "INSERT INTO sh.record (user_id, post_id) VALUES (?,?)";
    
    public static final String FIND_LIKE = "SELECT COUNT(1) as cnt FROM sh.record WHERE user_id = ? AND post_id = ?";
    
    public static final String DELETE_LIKE = " DELETE  FROM sh.record WHERE user_id = ? AND post_id = ?";
    
    public static final String INSERT_COMMENT = "INSERT INTO sh.comment (content, create_date, post_id, user_id) VALUES (?,?,?,?)";
    
    public static final String SELECT_POSTS_AND_USERS = "SELECT t1.id AS postid, t1.content, t1.create_date, t2.username, t2.tel, t2.email, t2.qq "
                                                            + "FROM sh.post t1  LEFT JOIN sh.user t2 ON t1.create_by = t2.id ORDER BY t1.create_date ";
    public static final String FIND_LIKENUM_BY_POSTID = "SELECT COUNT(1) FROM sh.record WHERE post_id = ?";

    public static final String FIND_COMMENTNUM_BY_POSTID = "SELECT COUNT(1) FROM sh.comment WHERE post_id = ?";

    public static final String FIND_USER_AVAILD = "SELECT COUNT(1) FROM sh.record WHERE post_id = ? AND user_id = ? ";

    public static final String SELECT_POST_COMMENTS = "SELECT t1.content, t1.create_date,t2.username, t2.email, t2.qq,t2.tel FROM sh.comment t1 "
                                                            + " LEFT JOIN  sh.user t2 ON t2.id = t1.user_id WHERE t1.post_id = ? ORDER BY t1.create_date ";
    public static final String SELECT_POSTS_BY_USER = "SELECT id as postid ,content , create_date FROM sh.post WHERE create_by = ? ORDER BY create_date";
    
}
