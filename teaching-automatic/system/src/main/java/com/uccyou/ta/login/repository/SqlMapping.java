package com.uccyou.ta.login.repository;

public final class SqlMapping {

    public static final String ADMIN_LOGIN = "SELECT adminId,userName,importData FROM teaching.admin WHERE userName = ? AND PASSWORD = ? AND alive = 'Y'";
    
}
