package com.javarush.mashin.projectjstl.service;
import com.javarush.mashin.projectjstl.entity.QuestDTO;
import com.javarush.mashin.projectjstl.entity.QuestEntity;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.questParser.QuestJsonParser;
import com.javarush.mashin.projectjstl.repository.QuestRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestService {
	QuestRepository questRepository;
	QuestJsonParser questJsonParser;
	public QuestService(QuestRepository questRepository, QuestJsonParser questJsonParser) {
		this.questRepository = questRepository;
		this.questJsonParser = questJsonParser;
	}

	public QuestDTO getQuest(int id){
		QuestEntity rawQuest = questRepository.getById(id);
		String questName = rawQuest.getName();
		int questId = rawQuest.getId();
		String questJson = rawQuest.getRawQuestJson();
		Map <String, QuestionDTO> questMap = questJsonParser.parse(questJson);
		return new QuestDTO(questId,questName,questMap);
	}

	public QuestEntity getQuestEntity(int id){
		return questRepository.getById(id);
	}

	public Map<Integer , String> getAllQuests()  {
		List<QuestEntity> rawQuestList = questRepository.getAll();
		Map<Integer , String> questMap = new HashMap<>();
		for (QuestEntity rawQuest : rawQuestList) {
			questMap.put(rawQuest.getId(), rawQuest.getName());
		}
		return questMap;
	}

	public void saveQuest(String questName,String questXml, String Json){
		questRepository.saveQuest(questName,questXml,Json);
	}
	public void updateQuest(int questId ,String questName,String questXml, String Json){
		questRepository.updateQuest(questId,questName,questXml,Json);
	}

	public void deleteQuest(Integer id) {
		questRepository.deleteQuest(id);
	}
}
