package ccst.sh.system.user.dao;

public class SysUserSqlMapping {
    public static final String SELECT_USERS = " SELECT id AS userId,username ,tel,email, qq, alive FROM sh.user ";
    
    public static final String COUNT_USERS = " SELECT COUNT(1) AS totalRecords FROM sh.user ";
    
    public static final String USER_ALIVE = " UPDATE sh.user SET alive = ? WHERE id = ? ";
}
