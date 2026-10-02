package com.javarush.mashin.projectjstl.entity;

import java.util.HashMap;
import java.util.Map;

public class QuestDTO {
	private Map <String , QuestionDTO> quest = new HashMap<>();

	public Map <String , QuestionDTO> getQuest() {
		return quest;
	}
	public void setQuest(Map <String , QuestionDTO> quest) {
		this.quest = quest;
	}
}
