package com.tianwen.commons.intranet.analyze.response;

import java.util.List;

public class AnalyzeQuestionResponse {

	private Integer id;

	private String title;

	private String questionType;

	private List<AnalyzeChooseResponse> choose;

	private List<String> contents;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AnalyzeChooseResponse> getChoose() {
		return choose;
	}

	public void setChoose(List<AnalyzeChooseResponse> choose) {
		this.choose = choose;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionType() {
		return questionType;
	}

	public List<String> getContents() {
		return contents;
	}

	public void setContents(List<String> contents) {
		this.contents = contents;
	}
}
