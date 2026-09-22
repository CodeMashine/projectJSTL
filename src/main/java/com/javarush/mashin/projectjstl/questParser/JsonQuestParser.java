package com.javarush.mashin.projectjstl.questParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.entity.QuestDTO;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;
import com.javarush.mashin.projectjstl.settings.Constants;
import com.javarush.mashin.projectjstl.workers.AppConfig;
import org.apache.taglibs.standard.extra.spath.Step;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class JsonQuestParser implements QuestParser {
	@Override
	public void getQuest(String questName) {
		AppConfig config = BeanFactory.getBean(AppConfig.class);
		String questPath = config.getQuestPath();
		Map<String , QuestionDTO> quest = parseJsonQuest(questName);
		System.out.println(quest);
//		return null;
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
