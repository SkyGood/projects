package ccst.sh.user.dao;

public final class UserSqlMapping {

    public static final String USER_LOGIN = "SELECT id, alive FROM sh.user WHERE username = ? AND PASSWORD = ?";

    public static final String CHECK_NAME = "SELECT COUNT(1) AS cnt FROM sh.user WHERE username = ?";
    
    public static final String FIND_STUDENT_ID = "SELECT id AS student_id FROM sh.student WHERE CODE = ?";
    
    public static final String CHECK_CODE = "SELECT COUNT(1) AS cnt FROM sh.user WHERE student_id = ?";
    
    public static final String INSERT_USER = "INSERT INTO sh.user (username, PASSWORD, alive, create_date, student_id ) VALUES(?,?,?,?,?)";
    
    public static final String FIND_USER_ID = "SELECT id as userid FROM sh.user WHERE username = ?";
    
    public static final String CHANGE_USER_INFO = "UPDATE sh.user SET tel=?,email=?,qq=? WHERE id =?";
    
    public static final String CHECK_OLD_PASSWORD = "SELECT COUNT(1) AS cnt FROM sh.user WHERE PASSWORD = ? AND id=?";
    
    public static final String UPDATE_USER_PASSWORD = "UPDATE sh.user SET PASSWORD =? WHERE id=?";
    
    public static final String FIND_NOTICES = "SELECT t1.topic,t1.content ,t1.create_date FROM sh.notice t1" 
                                                    + " LEFT JOIN  sh.student t3 ON  t3.classId = t1.class_id"
                                                           + " LEFT JOIN sh.user t4 ON t4.student_id = t3.id WHERE t4.id = ? ";
}
