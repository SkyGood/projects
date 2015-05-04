package com.uccyou.ta.teacher.resource.request;

public class ResourceSearchRequest {

    private Integer userId;

    private Integer classId;

    private String resName;

    /*
     * private Date startDate;
     * 
     * private Date endDate;
     */

    public String getResName() {
        return resName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    /*
     * public Date getStartDate() { return startDate; }
     * 
     * public void setStartDate(Date startDate) { this.startDate = startDate; }
     * 
     * public Date getEndDate() { return endDate; }
     * 
     * public void setEndDate(Date endDate) { this.endDate = endDate; }
     */

}
