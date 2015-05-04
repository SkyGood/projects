package com.uccyou.ta.teacher.teaching.request;

public class ImportClazzStudentRequest {

    private Integer classId;
    
    private Integer adminClassId;
    
    private String filePath;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getAdminClassId() {
        return adminClassId;
    }

    public void setAdminClassId(Integer adminClassId) {
        this.adminClassId = adminClassId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
