package com.uccyou.ta.teacher.homework.response;

import java.util.List;

public class HomeworkRemoveResponse {

    private Boolean flag;

    private String teaAttLocation;

    private List<String> stuAttLocation;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getTeaAttLocation() {
        return teaAttLocation;
    }

    public void setTeaAttLocation(String teaAttLocation) {
        this.teaAttLocation = teaAttLocation;
    }

    public List<String> getStuAttLocation() {
        return stuAttLocation;
    }

    public void setStuAttLocation(List<String> stuAttLocation) {
        this.stuAttLocation = stuAttLocation;
    }

}
