package com.uccyou.ta.teacher.homework.request;

public class AddHomeworkRequest {

    private Integer teacherId;

    private Integer classId;

    private String workName;

    private String content;

    private String resLocation;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
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

    public String getResLocation() {
        return resLocation;
    }

    public void setResLocation(String resLocation) {
        this.resLocation = resLocation;
    }

}
