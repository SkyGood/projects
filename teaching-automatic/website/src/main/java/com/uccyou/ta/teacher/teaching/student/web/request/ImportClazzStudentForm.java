package com.uccyou.ta.teacher.teaching.student.web.request;

public class ImportClazzStudentForm {

    private Integer userId;
    
    private Integer classId;
    
    private Integer adminClassId;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getAdminClassId() {
        return adminClassId;
    }

    public void setAdminClassId(Integer adminClassId) {
        this.adminClassId = adminClassId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
