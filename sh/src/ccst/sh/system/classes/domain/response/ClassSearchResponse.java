package ccst.sh.system.classes.domain.response;

import ccst.sh.system.classes.domain.Administrator;

public class ClassSearchResponse {

    private Integer adminClassId;

    private String adminClassName;
    
    private int adminClassNum;
    
    private Administrator admin;
    
    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    public int getAdminClassNum() {
        return adminClassNum;
    }

    public void setAdminClassNum(int adminClassNum) {
        this.adminClassNum = adminClassNum;
    }

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
