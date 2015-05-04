package com.uccyou.ta.teacher.resource.response;

import java.util.Date;

public class AllResourceResponse {

    private Integer resId;

    private String resName;

    private Date date;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
