package com.uccyou.ta.register.repository;

public final class SqlMapping {

    public static final String CHECK_USER_REGISTERED = "SELECT COUNT(1) FROM teaching.user WHERE identityCode = ? AND identity = ? AND registered = 'N'";
    
    public static final String USER_REGISTER = "UPDATE teaching.user SET userName = ?,PASSWORD = ?,registered = 'Y',create_date = ? WHERE identityCode = ?";
    
    public static final String GET_USER_REGISTER_ID = "SELECT id FROM teaching.user WHERE userName = ? AND identityCode = ?";
    
    public static final String CHECK_REGISTER_USERNAME = "SELECT COUNT(1) FROM teaching.user WHERE userName = ?";
}
