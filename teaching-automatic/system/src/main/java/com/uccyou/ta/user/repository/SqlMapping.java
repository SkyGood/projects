package com.uccyou.ta.user.repository;

public final class SqlMapping {

    public static final String GET_USER_INFO = "SELECT userName,identityCode,email,NAME,phone,qq,identity,create_date FROM teaching.user WHERE id = ?";
    
    public static final String UPDATE_USER_INFO = "UPDATE teaching.user SET email = ?,phone = ?,qq = ? WHERE id = ?";

}