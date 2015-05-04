package com.tianwen.commons.questionnaire.request;

import java.util.List;

public class CreateQuestionRequest {

    private String title; //问题题目
    
    private String questionType; //问题类型 S/M/F
    
    private List<CreateChooseRequest> choose; //选项

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<CreateChooseRequest> getChoose() {
        return choose;
    }

    public void setChoose(List<CreateChooseRequest> choose) {
        this.choose = choose;
    }
}
