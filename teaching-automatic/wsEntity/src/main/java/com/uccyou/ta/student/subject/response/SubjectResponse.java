package com.uccyou.ta.student.subject.response;

public class SubjectResponse {

    private Integer classId;

    private String courseName;

    private String teacherName;

    private String classRoom;

    private String courseType;

    private Double credit;
    
    private String teachingTime;

    private String notice;
    
    private String startWeek;

    private String endWeek;

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(String teachingTime) {
        this.teachingTime = teachingTime;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(String startWeek) {
        this.startWeek = startWeek;
    }

    public String getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(String endWeek) {
        this.endWeek = endWeek;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
    
    
}
