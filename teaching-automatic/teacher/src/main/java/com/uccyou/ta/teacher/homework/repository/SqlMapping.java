package com.uccyou.ta.teacher.homework.repository;

public final class SqlMapping {

    public static final String CHANGE_HOMEWORK_ALIVE = "UPDATE teaching.homework SET alive = ? WHERE id = ?";
    
    public static final String FIND_HOMEWORK_BY_ID = "SELECT workName, content FROM teaching.homework WHERE id = ?";
    
    public static final String UPDATE_HOMEWORK = "UPDATE teaching.homework SET workName = ?, content = ?  WHERE id = ?";
    
    public static final String ADD_HOMEWORK_ATTACHMENT = "INSERT INTO teaching.resources(user_id, resName, resLocation, type, alive, create_date) VALUES(?, ?, ?, 'A', 'Y', ?)";
    
    public static final String FIND_HOMEWORK_ATTACHMENT_ID = "SELECT id FROM teaching.resources WHERE resLocation = ? AND create_date = ?";
    
    public static final String ADD_HOMEWORK_EXISTS_ATTACHMENT = "INSERT INTO teaching.homework(workName, class_id, content, res_id, alive, create_date) VALUES(?, ?, ?, ?, 'Y', ?)";
    
    public static final String ADD_HOMEWORK_NOT_EXISTS_ATTACHMENT = "INSERT INTO teaching.homework(workName, class_id, content, alive, create_date) VALUES(?, ?, ?, 'Y', ?)";
    
    public static final String FIND_COMMIT_HOMEWORK = "SELECT t2.id, t2.LEVEL, t1.studentCode, t1.studentName, t3.className FROM teaching.classstudent t1 INNER JOIN teaching.studenthomework t2 ON t2.studentCode = t1.studentCode INNER JOIN teaching.teachingadminclass t3 ON t3.id = t1.adminClass_id WHERE t2.work_id = ? LIMIT ?, ?";

    public static final String HOMEWORK_COMMIT_COUNT = "SELECT COUNT(1) FROM teaching.classstudent t1 INNER JOIN teaching.studenthomework t2 ON t2.studentCode = t1.studentCode INNER JOIN teaching.teachingadminclass t3 ON t3.id = t1.adminClass_id WHERE t2.work_id = ?";

    public static final String FIND_UNCOMMIT_HOMEWORK = "SELECT t1.studentCode, t1.studentName,  t2.className FROM teaching.classstudent t1 INNER JOIN teaching.teachingadminclass t2 ON t2.id = t1.adminClass_id WHERE t1.teachingClass_id = ? AND t1.studentCode NOT IN (SELECT t.studentCode FROM teaching.studenthomework t WHERE t.work_id = ?) LIMIT ?, ?";

    public static final String HOMEWORK_UNCOMMIT_COUNT = "SELECT COUNT(1) FROM teaching.classstudent t1 INNER JOIN teaching.teachingadminclass t2 ON t2.id = t1.adminClass_id WHERE t1.teachingClass_id = ? AND t1.studentCode NOT IN (SELECT t.studentCode FROM teaching.studenthomework t WHERE t.work_id = ?)";

    public static final String CHECK_HOMEWORK_BY_ID = "SELECT t2.studentCode, t2.studentName, t3.className, t1.content, t1.create_date, t1.LEVEL, t4.resName, t4.resLocation FROM teaching.studenthomework t1 INNER JOIN teaching.classstudent t2 ON t1.studentCode = t2.studentCode INNER JOIN teaching.teachingadminclass t3 ON t2.adminClass_id = t3.id INNER JOIN teaching.resources t4 ON t4.id = t1.res_id WHERE t1.id = ?";

    public static final String UPDATE_HOMEWORK_LEVEL = "UPDATE teaching.studenthomework SET LEVEL = ? WHERE id = ?";

    public static final String DELETE_STUDENT_HOMEWORK = "DELETE FROM teaching.studenthomework WHERE work_id = ?";

    public static final String DELETE_HOMEWORK = "DELETE FROM teaching.homework WHERE id = ?";

    public static final String FIND_STUDENT_HOMEWORK_RESID = "SELECT res_id FROM teaching.studenthomework WHERE work_id = ?";

    public static final String FIND_STUDENT_ATTACHMENT_RESLOCATION = "SELECT t1.resLocation FROM teaching.resources t1 INNER JOIN teaching.studenthomework t2 ON t1.id = t2.res_id WHERE t2.work_id = ?";
    
    public static final String FIND_TEACHER_ATTACHMENT_RESLOCATION = "SELECT t1.resLocation FROM teaching.resources t1 INNER JOIN teaching.homework t2 ON t1.id = t2.res_id WHERE t2.id = ?";
    
    public static final String FIND_STUDENT_ATTACHMENT_ID = "SELECT t1.id FROM teaching.resources t1 INNER JOIN teaching.studenthomework t2 ON t1.id = t2.res_id WHERE t2.work_id = ?";

    public static final String REMOVE_STUDENT_ATTACHMENT = "DELETE FROM teaching.resources WHERE id = ?";

    public static final String REMOVE_STUDENT_HOMEWOK = "DELETE FROM studenthomework WHERE work_id = ?";

    public static final String FIND_TEACHER_ATTACHMENT_ID = "SELECT t1.id FROM teaching.resources t1 INNER JOIN teaching.homework t2 ON t1.id = t2.res_id WHERE t2.id = ?";

    public static final String DELETE_TEACHER_ATTACHMENT = "DELETE FROM teaching.resources WHERE id = ?";

    public static final String REMOVE_HOMEWORK = "DELETE FROM teaching.homework WHERE id = ?";

    public static final String HOMEWORK_EXISTS_ATTACHMENT = "SELECT COUNT(1) FROM teaching.studenthomework WHERE id = ? AND res_id IS NOT NULL";

    public static final String CHECK_HOMEWORK_NO_ATTACHMENT_BY_ID = "SELECT t1.studentCode, t1.content, t3.studentName, t1.create_date, t1.LEVEL, t4.className FROM teaching.studenthomework t1 INNER JOIN teaching.homework t2 ON t1.work_id  = t2.id INNER JOIN teaching.classstudent t3 ON t1.studentCode = t3.studentCode INNER JOIN teaching.teachingadminclass t4 ON t3.adminClass_id = t4.id WHERE t1.id = ?";

    public static final String OLD_ATTACHMENT_EXISTS = "SELECT COUNT(1) FROM teaching.homework WHERE id = ? AND res_id IS NOT NULL";

    public static final String ADD_NEW_ATTACHMENT = "INSERT INTO teaching.resources(user_id, resName, resLocation, type, alive, create_date) VALUE(?, ?, ?, 'A', 'Y', ?);";

    public static final String FIND_RESINFO_BY_WORKID = "SELECT t1.id, t1.resLocation FROM teaching.resources t1 INNER JOIN teaching.homework t2 ON t1.id = t2.res_id WHERE t2.id = ?";

    public static final String UPDATE_HOMEWOK_RESOURCES = "UPDATE teaching.resources SET resLocation = ?, resName = ?, create_date = ? WHERE id = ?";

    public static final String UPDATE_HOMEWORK_EXISTS_ATTACHMENT = "UPDATE teaching.homework SET workName = ?, content = ? WHERE id = ?";

    public static final String FIND_RESID = "SELECT id FROM teaching.resources WHERE user_id = ? AND resLocation = ? AND create_date = ?";

    public static final String UPDATE_HOMEWORK_NOT_EXISTS_ATTACHMENT = "UPDATE teaching.homework SET workName = ?, content = ?, res_id = ? WHERE id = ?";

}
