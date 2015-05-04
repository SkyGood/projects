package com.uccyou.ta.teacher.homework.response;

import java.util.Date;

public class HomeworkLevelResponse {

    private String studentCode;

    private String content;

    private String studentName;

    private String adminClassName;

    private Date createDate;

    private String level;

    private String resName;

    private String resLocation;

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAdminClassName() {
        return adminClassName;
    }

    public void setAdminClassName(String adminClassName) {
        this.adminClassName = adminClassName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResLocation() {
        return resLocation;
    }

    public void setResLocation(String resLocation) {
        this.resLocation = resLocation;
    }

}
