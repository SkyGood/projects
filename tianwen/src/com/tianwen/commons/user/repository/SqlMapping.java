package com.tianwen.commons.user.repository;

public final class SqlMapping {

    public static final String CHECK_USERNAME_REPEATED = " SELECT COUNT(1) FROM tianwen.user WHERE userName = ?";
    
    public static final String CHECK_USERNAME_CODE_EXISTS = " SELECT COUNT(1) FROM tianwen.student WHERE CODE = ? ";
    
    public static final String CHECK_USERNAME_ALREADY_REGISTER = " SELECT COUNT(1) FROM tianwen.student t1 INNER JOIN tianwen.user t2 ON t1.id  = t2.student_id WHERE t1.CODE = ? ";
    
    public static final String GET_STUDENT_ID = " SELECT id FROM tianwen.student WHERE code = ?";
    
    public static final String REGISTER_STUDNET = " INSERT INTO tianwen.user(student_id, userName, passWord, alive, create_date) values(?, ?, ?, 'Y', ?)";
    
    public static final String LOGIN_CHECK_BY_USER = " SELECT t1.id, t2.code, t1.alive, t1.userName, t1.qq, t1.phone, t1.email, t1.create_date FROM tianwen.user t1 INNER JOIN tianwen.student t2 ON t2.id = t1.student_id WHERE userName = ? AND PASSWORD = ? AND alive = ? ";
    
    public static final String LOGIN_CHECK_BY_CODE = " SELECT tianwen.user.id, tianwen.user.alive, tianwen.student.code, tianwen.user.userName, tianwen.user.qq, tianwen.user.phone, tianwen.user.email, tianwen.user.create_date FROM tianwen.user INNER JOIN tianwen.`student` ON tianwen.user.`student_id` = tianwen.`student`.`id` WHERE tianwen.`student`.`CODE` = ? AND tianwen.`user`.passWord = ? AND tianwen.`user`.alive = ? ";
    
    public static final String GET_USER_ID_BY_REGISTER_INFO = " SELECT id FROM tianwen.user WHERE student_id = ? ";
    
    public static final String MODIFY_USER = " UPDATE tianwen.`user` SET tianwen.`user`.`qq` = ?, tianwen.`user`.`phone` = ?, tianwen.`user`.`email` = ? WHERE tianwen.`user`.`id` = ? ";
    
    public static final String MODIFY_PASSWORD = " UPDATE tianwen.`user` SET tianwen.`user`.`PASSWORD` = ? WHERE tianwen.`user`.`id` = ? AND tianwen.`user`.`PASSWORD` = ? ";
    
}
	