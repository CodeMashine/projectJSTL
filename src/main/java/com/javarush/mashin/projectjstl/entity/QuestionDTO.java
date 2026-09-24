package com.javarush.mashin.projectjstl.entity;

public class QuestionDTO {
	private String question;
	private ChoiceDTO[] choices;
	private boolean isTerminal;
	private boolean isWin;

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

	public boolean isTerminal() {
		return isTerminal;
	}

	public void setIsTerminal(boolean isTerminal) {
		this.isTerminal = isTerminal;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setIsWin(boolean isWin) {
		this.isWin = isWin;
	}
}
