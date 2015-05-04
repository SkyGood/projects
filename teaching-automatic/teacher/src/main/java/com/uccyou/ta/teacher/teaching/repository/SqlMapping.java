package com.uccyou.ta.teacher.teaching.repository;

public final class SqlMapping {

    public static final String CREATE_TEACHING_CLASS = " INSERT INTO teaching.teachingclass (teacher_id,teacherCode,className,classRoom,courseName,courseType,startWeek,endWeek,teachingTime,credit,create_date) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
    
    public static final String GET_JUST_CREATE_TEACHING_CLASS_ID = " SELECT id FROM teaching.teachingclass WHERE create_date = ? AND teacher_id = ? ";
    
    public static final String GET_CLASS_BY_ID = " SELECT id,className,classRoom,courseName,courseType,startWeek,endWeek,teachingTime,credit,notice,create_date FROM teaching.teachingclass WHERE id = ? ";

    public static final String UPDATE_TEACHING_CLASS_INFO = " UPDATE teaching.teachingclass SET className = ?, classRoom = ?, courseName = ?, courseType = ?, startWeek = ?, endWeek = ?, teachingTime = ?, credit = ?, notice= ? WHERE id = ? AND alive = 'Y' ";

    public static final String GET_AMDIN_CLASS = " SELECT id, className FROM teaching.teachingadminclass WHERE teachingClass_id = ? ";
    
    /* remove teaching class sql begin */
    
    public static final String FIND_ALL_ADMIN_CLASS = "SELECT id FROM teaching.teachingadminclass WHERE teachingClass_id = ?";
    
    public static final String FIND_ALL_CLASS_STUDENT = "SELECT id FROM teaching.classstudent WHERE teachingClass_id = ? AND adminClass_id = ?";
    
    public static final String FIND_ALL_ABSENT_NOTE_RECORD = "SELECT id FROM teaching.absentnoterecord WHERE class_id = ? AND student_id = ?";
    
    public static final String FIND_REF_RESOURCES = "SELECT id FROM teaching.classresources WHERE class_id = ?";
    
    public static final String FIND_ONCE_REF_RESOURCES = "SELECT A.res_id AS res_id FROM teaching.classresources AS A WHERE (SELECT COUNT(*) FROM teaching.classresources AS B WHERE A.res_id=B.res_id) = 1 AND class_id = ?";
    
    public static final String FIND_ALL_HOMEWORK = "SELECT id FROM teaching.homework WHERE class_id = ?";
    
    public static final String FIND_RES_HOMEWORK = "SELECT res_id FROM teaching.homework WHERE class_id = ? AND res_id IS NOT NULL";
    
    public static final String FIND_STUDENT_HOMEWORK = "SELECT id FROM teaching.studenthomework WHERE work_id = ?";
    
    public static final String FIND_RES_STUDENT_HOMEWORK = "SELECT res_id FROM teaching.studenthomework WHERE work_id = ? AND res_id IS NOT NULL";
    
    public static final String DELETE_STUDENT_HOMEWORK = "DELETE FROM teaching.studenthomework WHERE id = ?";
    
    public static final String DELETE_HOMEWORK = "DELETE FROM teaching.homework WHERE id = ?";
    
    public static final String DELETE_RES_REF = "DELETE FROM teaching.classresources WHERE id = ?";
    
    public static final String DELETE_RES = "DELETE FROM teaching.resources WHERE id = ?";
    
    public static final String DELETE_ABSENT = "DELETE FROM teaching.absentnoterecord WHERE id = ?";
    
    public static final String DELETE_CLASS_STUDENT = "DELETE FROM teaching.classstudent WHERE id = ?";
    
    public static final String DELETE_ADMIN_CLASS = "DELETE FROM teaching.teachingadminclass WHERE id = ?";
    
    public static final String DELETE_TEACHING_CLASS = "DELETE FROM teaching.teachingclass WHERE id = ?";
    
    /* remove teaching class sql end */
    
}
