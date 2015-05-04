package com.uccyou.ta.teacher.teaching.admin.response;

import java.io.Serializable;

public class AdminClassResponse implements Serializable {

    private static final long serialVersionUID = 5655439530139467714L;

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
