package com.javarush.mashin.projectjstl.questParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;
import com.javarush.mashin.projectjstl.settings.Constants;
import com.javarush.mashin.projectjstl.workers.AppConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class JsonQuestParser implements QuestParser {
	private final Map<String , Map<String, QuestionDTO>> questCash = new HashMap<>();
	public Map<String , QuestionDTO> getQuest(String questName) {
		if(questCash.containsKey(questName)) {
			return questCash.get(questName) ;
		}
		AppConfig config = BeanFactory.getBean(AppConfig.class);
		String questPath = config.getQuestPath();
		Map<String , QuestionDTO> quest = parseJsonQuest(questName);
		questCash.put(questName, quest);
		return quest;
	}

	private Map<String , QuestionDTO> parseJsonQuest(String questName) {
		String jsonQuestName = questName + ".json";
		ObjectMapper objectMapper = new ObjectMapper();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream(jsonQuestName)) {
			if (input == null) {
				throw new ProjectJSTLException("Файл не найден в classpath: " + jsonQuestName);
			}
			Map<String , QuestionDTO> questDTO = objectMapper.readValue(input, new TypeReference<>() {});
			return questDTO;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ProjectJSTLException("Ошибка чтения файла квеста " + jsonQuestName, e);
		}
	}


}
