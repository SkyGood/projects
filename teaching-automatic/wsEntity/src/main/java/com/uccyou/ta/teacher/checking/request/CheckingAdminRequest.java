package com.uccyou.ta.teacher.checking.request;

public class CheckingAdminRequest {

    private Integer adminClassId;
    
    private Integer classId;
    
    public Integer getAdminClassId() {
        return adminClassId;
    }

    public void setAdminClassId(Integer adminClassId) {
        this.adminClassId = adminClassId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
    
}
