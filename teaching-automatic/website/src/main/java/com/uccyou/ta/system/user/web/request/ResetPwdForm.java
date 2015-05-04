package com.uccyou.ta.system.user.web.request;

public class ResetPwdForm {
    
    private Integer userId;

    private String oldPwd;

    private String newPwd;

    private String reNewPwd;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getReNewPwd() {
        return reNewPwd;
    }

    public void setReNewPwd(String reNewPwd) {
        this.reNewPwd = reNewPwd;
    }

}
