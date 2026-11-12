package com.javarush.mashin.projectjstl.entity;

public class QuestionDTO {
	private String id ;
	private String stepName;
	private String questionText;
	private ChoiceDTO[] choices;
	private boolean isLose;
	private boolean isWin;

	public ChoiceDTO[] getChoices() {
		return choices;
	}

	public void setChoices(ChoiceDTO[] choices) {
		this.choices = choices;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public boolean isLose() {
		return isLose;
	}

	public void setIsLose(boolean isTerminal) {
		this.isLose = isTerminal;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setIsWin(boolean isWin) {
		this.isWin = isWin;
	}

	public String getId() {
		return id;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public void setId(String id) {
		this.id = id;
	}
}
