package com.uccyou.ta.system.admin.response;

import java.util.List;

public class UserRemoveResponse {

    private Boolean flag;

    private List<String> resLocation;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public List<String> getResLocation() {
        return resLocation;
    }

    public void setResLocation(List<String> resLocation) {
        this.resLocation = resLocation;
    }

}
