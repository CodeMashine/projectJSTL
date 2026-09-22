package com.javarush.mashin.projectjstl.entity;

public class QuestionDTO {
	private String question;
	private ChoiceDTO[] choices;

	public ChoiceDTO[] getChoices() {
		return choices;
	}

	public void setChoices(ChoiceDTO[] choices) {
		this.choices = choices;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
