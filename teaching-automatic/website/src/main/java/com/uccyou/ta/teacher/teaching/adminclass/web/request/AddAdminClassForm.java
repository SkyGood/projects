package com.uccyou.ta.teacher.teaching.adminclass.web.request;

public class AddAdminClassForm {

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
