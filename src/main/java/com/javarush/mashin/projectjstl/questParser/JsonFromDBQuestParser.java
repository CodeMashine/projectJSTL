package com.javarush.mashin.projectjstl.questParser;

import com.javarush.mashin.projectjstl.entity.QuestionDTO;

import java.util.Map;

public class JsonFromDBQuestParser implements QuestParser {
	@Override
	public Map<String, QuestionDTO> getQuest(String questName) {
		return Map.of();
	}
}
