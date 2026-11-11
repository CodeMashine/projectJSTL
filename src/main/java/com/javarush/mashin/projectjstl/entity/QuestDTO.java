package com.javarush.mashin.projectjstl.entity;

import java.util.HashMap;
import java.util.Map;

public class QuestDTO {
	private Map<String, QuestionDTO> questJson = new HashMap<>();
	private String questName;
	private int questId;

	public String getQuestName() {
		return questName;
	}

	public int getQuestId() {
		return questId;
	}

	public Map<String, QuestionDTO> getQuestJson() {
		return questJson;
	}

	public QuestionDTO getQuestion(String stepName) {
		QuestionDTO question = questJson.get(stepName);
		return question;
	}
}
