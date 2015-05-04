package com.uccyou.ta.teacher.resource.request;

public class UploadRequest {

    private String resName;

    private Integer teacherId;

    private Integer classId;

    private String resLocation;

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

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

    public String getResLocation() {
        return resLocation;
    }

    public void setResLocation(String resLocation) {
        this.resLocation = resLocation;
    }

}
