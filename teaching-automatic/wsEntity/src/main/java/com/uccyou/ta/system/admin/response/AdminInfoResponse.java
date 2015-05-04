package com.uccyou.ta.system.admin.response;

import java.io.Serializable;
import java.util.Date;

public class AdminInfoResponse implements Serializable {

    private static final long serialVersionUID = -456345616803354338L;

    private Integer adminId;

    private String username;

    private String alive;

    private Date createDate;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlive() {
        return alive;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}
