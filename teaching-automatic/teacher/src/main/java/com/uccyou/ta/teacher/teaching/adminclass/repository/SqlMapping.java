package com.uccyou.ta.teacher.teaching.adminclass.repository;

public class SqlMapping {

    public static final String ADD_ADMIN_CLASS = "INSERT INTO teaching.teachingadminclass(teachingClass_id,className) VALUES(?,?)";
    
    public static final String UPDATE_ADMIN_CLASS = "UPDATE teaching.teachingadminclass SET className = ? WHERE id = ?";

    public static final String FIND_ADMIN_CLASS_ID = "SELECT id FROM teaching.teachingadminclass WHERE teachingClass_id = ? AND className = ?";

    public static final String ADMIN_CLASS_EXISTS_STUDENT = "SELECT COUNT(1) FROM teaching.classstudent WHERE adminClass_id = ?";
    
    public static final String REMOVE_ADMIN_CLASS_BY_ID = "DELETE FROM teaching.teachingadminclass WHERE id = ?";

    public static final String CLEAN_STUDENT_ABSENT_RECORD = "DELETE FROM teaching.absentnoterecord WHERE student_id IN (SELECT id FROM teaching.classstudent WHERE adminClass_id = ?)";
    
    public static final String CLEAN_ADMIN_CLASS = "DELETE FROM teaching.classstudent WHERE adminClass_id = ?";


}
