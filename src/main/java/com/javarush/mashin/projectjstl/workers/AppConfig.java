package com.javarush.mashin.projectjstl.workers;

import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;
import com.javarush.mashin.projectjstl.settings.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
	private final String SETTING_PATH = "application.properties";
	private final Properties props = new Properties();

	public AppConfig() {
		initDefault();
	}

	private void initDefault() {
		try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(SETTING_PATH)) {
			if (input == null) {
				throw new ProjectJSTLException("Файл не найден в classpath: " + SETTING_PATH);
			}
			props.load(input);
			String start = props.getProperty(Constants.START);
			String quest = props.getProperty(Constants.QUEST_PATH);
			System.out.println("start: " + start);
			System.out.println("quest: " + quest);

		} catch (IOException e) {
			throw new ProjectJSTLException("Ошибка чтения файла конфигурации", e);
		}
	}

	public String getStartPoint() {
		return props.getProperty(Constants.START);
	}

	public String getQuestPath() {
		return props.getProperty(Constants.QUEST_PATH);
	}



}
