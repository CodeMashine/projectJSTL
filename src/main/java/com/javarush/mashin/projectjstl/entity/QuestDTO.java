package com.javarush.mashin.projectjstl.entity;

import java.util.HashMap;
import java.util.Map;

public class QuestDTO {
	private Map<String, QuestionDTO> quest = new HashMap<>();
	private String questName = "Quest";

	public void setQuestName(String questName){
		this.questName = questName;
	}

	public String getQuestName(){
		return questName;
	}

	public Map<String, QuestionDTO>  getQuest() {
		return quest;
	}

	public void setQuest(Map<String, QuestionDTO>  quest) {
		this.quest = quest;
	}

	public QuestionDTO getQuestion(String stepName) {
		QuestionDTO question = quest.get(stepName);
		return question ;
	}
}
