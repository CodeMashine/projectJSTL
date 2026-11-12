package com.javarush.mashin.projectjstl.entity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class QuestDTO {
	private Map<String, QuestionDTO> quest = new LinkedHashMap<>();
	private String questName;
	private int questId;

	public QuestDTO(int questId, String questName, Map<String, QuestionDTO> quest) {
		this.questId = questId;
		this.questName = questName;
		this.quest = quest;
	}


	public String getQuestName() {
		return questName;
	}

	public int getQuestId() {
		return questId;
	}

	public Map<String, QuestionDTO> getQuest() {
		return quest;
	}

	public QuestionDTO getQuestion(String stepId) {
		if (stepId == null) {
			stepId = getFirstQuestionId();
		}
		;
		QuestionDTO question = quest.get(stepId);
		return question;
	}

	public QuestionDTO getQuestion(int id) {
		QuestionDTO question = quest.get(id);
		return question;
	}

	public void setQuest(Map<String, QuestionDTO> quest) {
		this.quest = quest;
	}

	private String getFirstQuestionId() {
		return quest.keySet().iterator().next();
	}

}
