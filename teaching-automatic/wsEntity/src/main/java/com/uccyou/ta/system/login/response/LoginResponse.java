package com.uccyou.ta.system.login.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private static final long serialVersionUID = 2379061927567287378L;

    private Integer userId;
    
    private String name;
    
    private Integer tableNum;
    
    private Integer adminId;
    
    private Boolean importData;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImportData() {
        return importData;
    }

    public void setImportData(Boolean importData) {
        this.importData = importData;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }
}
