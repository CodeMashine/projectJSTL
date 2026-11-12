package com.javarush.mashin.projectjstl.questParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

import java.util.Map;

public class QuestJsonParser implements QuestParser {
	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Map<String, QuestionDTO> getQuest(String questName) {
		return null;
	}

	public Map<String, QuestionDTO> parse(String rawJson) {
		try {
			Map<String, QuestionDTO> quest = objectMapper.readValue(rawJson, new TypeReference<>() {});
			return quest;
		}catch (Exception e) {
			throw new ProjectJSTLException(e);
		}
	}




}
