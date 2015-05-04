package com.uccyou.ta.student.homework.repository;

public final class SqlMapping {

    public static final String FIND_HOMEWORK = "SELECT t1.workName, t2.resLocation, t2.resName, t1.content FROM teaching.homework t1 INNER JOIN teaching.resources t2 ON t1.res_id = t2.id WHERE t1.id = ?";
    
    public static final String COMMIT_EXISTS_ATTACHMENT = "INSERT INTO teaching.studenthomework(work_id, content, studentCode, res_id, create_date) VALUES(?, ?, ?, ?, ?)";
    
    public static final String COMMIT_NOT_EXISTS_ATTACHMENT = "INSERT INTO teaching.studenthomework(work_id, content, studentCode, create_date) VALUES(?, ?, ?, ?)";

    public static final String COMMIT_ATTACHMENT = "INSERT INTO teaching.resources(user_id, resName, resLocation, type, alive, create_date) VALUES(?, ?, ?, 'A', 'Y', ?)";

    public static final String FIND_RES_ID = "SELECT id FROM teaching.resources WHERE create_date = ? AND resLocation = ?";

    public static final String STUDENT_ATTACHMENT_IS_NULL = "SELECT COUNT(1)  FROM teaching.homework WHERE id = ? AND res_id IS NULL";

    public static final String FIND_HOMEWORK_ATT_NULL = "SELECT workName, content FROM teaching.homework WHERE id = ?";
    
    
}