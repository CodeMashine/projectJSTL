package com.javarush.mashin.projectjstl.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Class maybe needed in work with on Data Base
public class QuestCatalogDTO {
	private Map<String ,QuestDTO > quests= new HashMap<>();

	public Map<String ,QuestDTO> getQuests() {
		return quests;
	}
	public QuestDTO getQuest(String questName) {
		return quests.get(questName);
	}

	public void addQuest(QuestDTO quest) {
		quests.put(quest.getQuestName(),quest) ;
	}

	public List<String> getQuestNames() {
		return new ArrayList<>(quests.keySet());
	}

}
