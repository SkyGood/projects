package com.uccyou.ta.system.admin.request;

import java.io.Serializable;

public class NewAdminRequest implements Serializable {

    private static final long serialVersionUID = -7096015840087455768L;
    
    private String adminname;

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }
}
