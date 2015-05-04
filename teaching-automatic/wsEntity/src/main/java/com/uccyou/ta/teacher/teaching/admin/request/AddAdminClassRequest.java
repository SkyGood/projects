package com.uccyou.ta.teacher.teaching.admin.request;

public class AddAdminClassRequest {

    private Integer teachingClassId;

    private String adminClassName;
    

    public Integer getTeachingClassId() {
        return teachingClassId;
    }

    public void setTeachingClassId(Integer teachingClassId) {
        this.teachingClassId = teachingClassId;
    }

    public String getAdminClassName() {
        return adminClassName;
    }

    public void setAdminClassName(String adminClassName) {
        this.adminClassName = adminClassName;
    }

}
