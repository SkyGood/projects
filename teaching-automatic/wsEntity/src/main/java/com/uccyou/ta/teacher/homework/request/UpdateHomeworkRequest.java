package com.uccyou.ta.teacher.homework.request;

public class UpdateHomeworkRequest {

    private Integer workId;

    private String workName;

    private String content;

    private Integer teacherId;

    private String resLocation;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getResLocation() {
        return resLocation;
    }

    public void setResLocation(String resLocation) {
        this.resLocation = resLocation;
    }

}
