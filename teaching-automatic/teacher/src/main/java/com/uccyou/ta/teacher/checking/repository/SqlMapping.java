package com.uccyou.ta.teacher.checking.repository;

public final class SqlMapping {

    public static final String CHECKING_ALL = "SELECT id,studentCode,studentName,studentPhone,monitor,learner,absentTime,noteTime FROM teaching.classstudent WHERE teachingClass_id = ? LIMIT ?,?";
    
    public static final String CHECKING_ALL_COUNT = "SELECT COUNT(1) FROM teaching.classstudent WHERE teachingClass_id = ?";
    
    public static final String CHECKING_ADMIN = "SELECT id,studentCode,studentName,studentPhone,monitor,learner,absentTime,noteTime FROM teaching.classstudent WHERE teachingClass_id = ? AND adminClass_id = ? LIMIT ?,?";
    
    public static final String CHECKING_ADMIN_COUNT = "SELECT COUNT(1) FROM teaching.classstudent WHERE teachingClass_id = ? AND adminClass_id = ?";
    
    public static final String CHECKING_RANDOM_RESULT = "SELECT id FROM teaching.classstudent WHERE teachingClass_id = ? ORDER BY RAND() LIMIT ?";
    
    public static final String CHECKING_RANDOM_SAVE_TEMP = "INSERT INTO teaching.tempcheckingrandom (teachingClass_id,classStudent_id,create_date) VALUES (?,?,?)";
    
    public static final String FIND_STUDENT_INFO = "SELECT id,studentCode,studentName,studentPhone,monitor,learner,absentTime,noteTime FROM teaching.classstudent WHERE id = ?";
    
    public static final String FIND_RANDOM_PAGING_IDS = "SELECT classStudent_id AS id FROM teaching.tempcheckingrandom WHERE teachingClass_id = ? LIMIT ?,?";
    
    public static final String FIND_RANDOM_COUNT = "SELECT COUNT(1) FROM teaching.tempcheckingrandom WHERE teachingClass_id = ?";
    
    public static final String DELETE_RANDOM_TEMP = "DELETE FROM teaching.tempcheckingrandom WHERE teachingClass_id = ?";
    
    public static final String ADD_ABSENT_TIME = "UPDATE teaching.classstudent SET absentTime = absentTime + 1 WHERE id = ? AND teachingClass_id = ?";
    
    public static final String ADD_ABSENT_RECORD = "INSERT INTO teaching.absentnoterecord (student_id,class_id,TYPE,create_date) VALUES (?,?,'A',?)";
    
    public static final String ADD_NOTE_TIME = "UPDATE teaching.classstudent SET noteTime = noteTime + 1 WHERE id = ? AND teachingClass_id = ?";
    
    public static final String ADD_NOTE_RECORD = "INSERT INTO teaching.absentnoterecord (student_id,class_id,TYPE,create_date) VALUES (?,?,'N',?)";

    public static final String FINDADMINCLASSNAMEBYID = "SELECT className FROM teaching.teachingadminclass WHERE id = ?";
    
}
