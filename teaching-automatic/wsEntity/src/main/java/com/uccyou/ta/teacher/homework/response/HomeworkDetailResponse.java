package com.uccyou.ta.teacher.homework.response;

public class HomeworkDetailResponse {

    private String stuHomeworkId;

    private String studentCode;

    private String studentName;

    private String studentAdminClassName;

    private String level;

    public String getStuHomeworkId() {
        return stuHomeworkId;
    }

    public void setStuHomeworkId(String stuHomeworkId) {
        this.stuHomeworkId = stuHomeworkId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAdminClassName() {
        return studentAdminClassName;
    }

    public void setStudentAdminClassName(String studentAdminClassName) {
        this.studentAdminClassName = studentAdminClassName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
