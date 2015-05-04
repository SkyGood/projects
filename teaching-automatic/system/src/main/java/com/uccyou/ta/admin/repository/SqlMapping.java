package com.uccyou.ta.admin.repository;

public final class SqlMapping {

/*    public static final String GET_INSERT_MAX_ID = " SELECT MAX(id) FROM teaching.user ";

    public static final String UPDATE_USER_TYPE = " UPDATE teaching.user SET identity = ? WHERE id > ? ";*/
    
    public static final String IMPORT_MAX_ID = "SELECT MAX(id) FROM teaching.user";
    
    public static final String HASH_USER_0 = "INSERT INTO teaching.user0 SELECT * FROM teaching.user WHERE identityCode%3 = 0 AND id > ?";
    
    public static final String HASH_USER_1 = "INSERT INTO teaching.user1 SELECT * FROM teaching.user WHERE identityCode%3 = 1 AND id > ?";
    
    public static final String HASH_USER_2 = "INSERT INTO teaching.user2 SELECT * FROM teaching.user WHERE identityCode%3 = 2 AND id > ?";
    
    public static final String ADMIN_LOGIN = " SELECT COUNT(1) FROM teaching.admin WHERE username = ? AND password = ? AND alive = 'Y' ";

    public static final String ADMIN_ALREADY_EXISTS = " SELECT COUNT(1) FROM teaching.admin WHERE username = ? ";

    public static final String ADMIN_COUNT = " SELECT COUNT(1) FROM teaching.admin ";

    public static final String ADD_ADMIN = " INSERT INTO teaching.admin(adminId, username, password, alive, create_date) VALUES(?, ?, ?, 'Y', ?) ";

    public static final String FIND_MAX_ID = " SELECT MAX(CAST(adminId AS UNSIGNED))+1 AS maxId FROM teaching.admin ";

    public static final String ALL_ADMIN_INFO = " SELECT adminId, username, alive, create_date FROM teaching.admin limit 1, 8 ";

    public static final String CHANGE_STATUS = " UPDATE teaching.admin SET alive = ? WHERE adminId = ? ";

    public static final String QUERY_ADMIN_STATUS = " SELECT alive FROM teaching.admin WHERE adminId = ? ";

    public static final String REMOVE_ADMIN = " DELETE FROM teaching.admin WHERE adminId = ? ";

    public static final String RESET_ADMIN_PASSWD = " UPDATE teaching.admin SET password = ? WHERE adminId = ? ";

    public static final String UPDATE_USER_ALIVE = " UPDATE teaching.user SET alive = ? WHERE identityCode = ? ";

    public static final String RESET_USER_PASSWORD = " UPDATE teaching.user SET PASSWORD = ? WHERE identityCode = ? ";

    public static final String FIND_IDENTITY_CODE_BY_ID = "SELECT identityCode FROM teaching.user WHERE id = ?";

    public static final String UPDATE_USER_INFO = "UPDATE teaching.user SET name = ?, phone = ?, email = ?, qq = ?, identity = ? WHERE id = ?";

    public static final String UPDATE_USER_NEW_CODE_INFO = "UPDATE teaching.user SET identityCode = ?, PASSWORD = ?, name = ?, phone = ?, email = ?, qq = ?, identity = ? WHERE id = ?";

    public static final String DELETE_USER = "DELETE FROM teaching.user WHERE identityCode = ?";

    public static final String USER_ALREADY_EXISTS = "SELECT COUNT(1) FROM teaching.user WHERE identityCode = ?";

    public static final String ADMIN_ADD_USER = "INSERT INTO teaching.user(identityCode, PASSWORD, email, NAME, phone, qq, identity, alive)  VALUES(?, ?, ?, ?, ?, ?, ?, 'Y')";

    public static final String FIND_USER_MAX_ID = "SELECT id FROM teaching.user ORDER BY id DESC LIMIT 1";

    public static final String FIND = "SELECT MIN(adminId) FROM teaching.admin";

    public static final String FIND_MIN_ID = "SELECT MIN(adminId) FROM teaching.admin";

    public static final String UPDATE_SYSTEM_NOTICE = "UPDATE teaching.systemnotice SET admin_id = ? WHERE admin_id = ?";

    public static final String FIND_STUDENT_ID = "SELECT id FROM teaching.classstudent WHERE studentCode = ?";

    public static final String REMOVE_ABSENT_NOTE_RECORD = "DELETE FROM teaching.absentnoterecord WHERE student_id = ?";

    public static final String REMOVE_USER_IN_CLASS_STUDENT = "DELETE FROM teaching.classstudent WHERE studentCode = ?";

    public static final String REMOVE_USER_STUDENT_HOMEWORK = "DELETE FROM teaching.studenthomework WHERE studentCode = ?";

    public static final String REMOVE_USER_RESOURCES = "DELETE FROM teaching.resources WHERE user_id = ?";

    public static final String REMOVE_RANDOM = "DELETE FROM teaching.tempcheckingrandom WHERE classStudent_id = ?";

    public static final String FIND_RESOURCES = "SELECT resLocation FROM teaching.resources WHERE user_id = ?";

    public static final String REMOVE_USER = "DELETE FROM teaching.user WHERE id = ?";

}
