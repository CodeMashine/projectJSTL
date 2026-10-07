package com.javarush.mashin.projectjstl.questParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonQuestParser implements QuestParser {
	private final Map<String, Map<String, QuestionDTO>> questCache = new ConcurrentHashMap<>();

	public Map<String, QuestionDTO> getQuest(String questName) {
		Map<String, QuestionDTO> quest = questCache.computeIfAbsent(questName, k -> parseJsonQuest(k));
		return quest;
	}

	private Map<String, QuestionDTO> parseJsonQuest(String questName) {
		String jsonQuestName = questName + ".json";
		ObjectMapper objectMapper = new ObjectMapper();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream(jsonQuestName)) {
			if (input == null) {
				throw new ProjectJSTLException("Файл не найден в classpath: " + jsonQuestName);
			}
			Map<String, QuestionDTO> questDTO = objectMapper.readValue(input, new TypeReference<>() {});
			return questDTO;
		} catch (IOException e) {
			e.printStackTrace();
			throw new ProjectJSTLException("Ошибка чтения файла квеста " + jsonQuestName, e);
		}
	}
}
