package com.uccyou.ta.teacher.checking.response;

import java.io.Serializable;

public class ClassStudentResponse implements Serializable {

    private static final long serialVersionUID = 8768071231324112266L;

    private Integer studentId;
    
    private String studentName;
    
    private String studentCode;
    
    private String studentPhone;
    
    private String monitor;
    
    private String learner;
    
    private Integer absentTime;
    
    private Integer noteTime;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getLearner() {
        return learner;
    }

    public void setLearner(String learner) {
        this.learner = learner;
    }

    public Integer getAbsentTime() {
        return absentTime;
    }

    public void setAbsentTime(Integer absentTime) {
        this.absentTime = absentTime;
    }

    public Integer getNoteTime() {
        return noteTime;
    }

    public void setNoteTime(Integer noteTime) {
        this.noteTime = noteTime;
    }
}
