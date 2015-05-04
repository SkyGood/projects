package com.uccyou.ta.teacher.homework.response;

import java.util.Date;

public class HomeworkSearchResponse {

    private Integer workId;

    private String workName;

    private Date createDate;

    private String alive;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAlive() {
        return alive;
    }

    public void setAlive(String alive) {
        this.alive = alive;
    }

}
