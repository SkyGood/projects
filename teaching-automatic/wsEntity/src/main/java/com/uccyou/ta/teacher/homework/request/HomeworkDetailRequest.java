package com.uccyou.ta.teacher.homework.request;

public class HomeworkDetailRequest {

    private Integer classId;

    private Integer workId;
    
    private String status;
    
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
