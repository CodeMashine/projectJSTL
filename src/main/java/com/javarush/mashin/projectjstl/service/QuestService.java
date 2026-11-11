package com.javarush.mashin.projectjstl.service;
import com.javarush.mashin.projectjstl.entity.QuestDTO;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;
import com.javarush.mashin.projectjstl.repository.QuestRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class QuestService {
	QuestRepository questRepository;
	public QuestService(QuestRepository questRepository) {
		this.questRepository = questRepository;
	}

	public QuestDTO getQuest(int id){
		ResultSet quest = questRepository.getById(id);
	}

	public Map<Integer , String> getAllQuests()  {
		ResultSet resultSet = questRepository.getQuests();
		Map<Integer , String> quests = new HashMap<>();
		try {
			while (resultSet.next()) {
				quests.put(resultSet.getInt(1), resultSet.getString(2));
			}
		}catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
		return quests;
	}

	public void saveQuest(String questName,String questXml, String Json){
		questRepository.saveQuest(questName,questXml,Json);
	}
}
