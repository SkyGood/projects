package com.uccyou.ta.system.login.request;

import java.io.Serializable;

public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 6323538232177568245L;

    private String identityCode;
    
    private String passWord;
    
    private String identity;

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
