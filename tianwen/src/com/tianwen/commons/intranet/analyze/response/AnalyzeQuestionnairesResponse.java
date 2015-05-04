package com.tianwen.commons.intranet.analyze.response;

import java.util.List;

public class AnalyzeQuestionnairesResponse {

	private String topic;

	private List<AnalyzeQuestionResponse> questions;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<AnalyzeQuestionResponse> getQuestions() {
		return questions;
	}

	public void setQuestions(List<AnalyzeQuestionResponse> questions) {
		this.questions = questions;
	}
}
