package com.uccyou.ta.system.register.web.request;

import java.io.Serializable;

public class RegisterForm implements Serializable {
    
    private static final long serialVersionUID = 3795882625259399688L;

    private String userName;
    
    private String identityCode;
    
    private String passWord;
    
    private String identity;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
    
}
