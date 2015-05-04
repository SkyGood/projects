package ccst.sh.system.classes.dao;

public class ClassSqlMapping {
    public static final String SELECT_ADMINCLASS_LIST = "SELECT t1.id AS classId,t1.className,t2.NAME, t2.id AS administratorId FROM sh.adminclass t1 " 
                                            + " LEFT JOIN sh.administrator t2 ON t2.class_id = t1.id ";
    
    public static final String COUNT_CLASS_NUM = " SELECT COUNT(1) FROM sh.student WHERE classId = ?";

    public static final String STUDENT_BY_CLASSID = " SELECT  t1.CODE, t1.NAME, t1.gender,t1.id AS studentid ,t3.username "
                                                    + " FROM  sh.student t1  LEFT JOIN sh.adminclass t2 ON t1.classId = t2.id "
                                                        + " LEFT JOIN sh.user t3 ON t1.id  = t3.student_id  WHERE t2.id = ?";
    
    public static final String COUNT_CLASS = "  SELECT COUNT(1) FROM sh.adminclass "; 
    
    public static final String FIND_STUDNETSID_BY_CLASSID = "SELECT id AS studentid FROM sh.student WHERE classId = ? ";
    
    public static final String DELETE_NOTICE_BY_CLASSID = " DELETE FROM sh.notice WHERE class_id = ? "; 
    
    public static final String DELETE_ADMIN_BY_CLASSID = " DELETE FROM sh.administrator WHERE class_id = ? "; 
    
    public static final String DELETE_CLASS = " DELETE FROM sh.adminclass WHERE id= ? ";
    
    /*administrator*/
    public static final String INSERT_ADMIN = "INSERT INTO sh.administrator (NAME, PASSWORD, class_id) VALUES (?,?,?)";
    
    /*student*/
    public static final String SELECT_CNT_STUDENT = " SELECT COUNT(1) FROM sh.student";
    
    public static final String STUDENT_CHECK_CODE = " SELECT COUNT(1) AS cnt FROM sh.student WHERE CODE = ?";
    
    public static final String INSERT_STUDENT = " INSERT INTO sh.student (CODE,NAME,gender,classId) VALUES (?,?,?,?)";
    
    public static final String UPDATA_STUDENT = " UPDATE sh.student SET NAME = ?, CODE = ?, gender=?  WHERE id =?";
    
    public static final String FIND_USERID_BY_STUDENTID = " SELECT id AS userId FROM sh.user WHERE  student_id = ? "; 
    
    public static final String DELETE_STUDENT = " DELETE FROM sh.student WHERE id = ? ";
    
    

}
