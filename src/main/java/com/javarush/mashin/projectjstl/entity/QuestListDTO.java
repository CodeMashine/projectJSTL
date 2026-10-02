package com.javarush.mashin.projectjstl.entity;

import java.util.HashMap;
import java.util.Map;

public class QuestListDTO {
	private Map<String ,QuestDTO > quests= new HashMap<>();

	public Map<String ,QuestDTO> getQuests() {
		return quests;
	}

	public void setQuests(String questName , Map<String ,QuestDTO> quest) {
		quests.put(questName,quest) ;
	}
}
