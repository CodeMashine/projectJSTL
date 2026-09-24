package com.javarush.mashin.projectjstl.questParser;

import com.javarush.mashin.projectjstl.entity.QuestDTO;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;

import java.util.Map;

public interface QuestParser {
	//	QuestDTO getQuest(String questName) ;
	Map<String, QuestionDTO> getQuest(String questName);
}
