package com.uccyou.ta.teacher.teaching.admin.request;

public class ChangeAdminClassNameRequest {

    private Integer adminClassId;

    private String adminClassName;

    public Integer getAdminClassId() {
        return adminClassId;
    }

    public void setAdminClassId(Integer adminClassId) {
        this.adminClassId = adminClassId;
    }

    public String getAdminClassName() {
        return adminClassName;
    }

    public void setAdminClassName(String adminClassName) {
        this.adminClassName = adminClassName;
    }

}
