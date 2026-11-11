package com.javarush.mashin.projectjstl.config;

import com.javarush.mashin.projectjstl.service.QuestService;
import com.javarush.mashin.projectjstl.service.UserService;

public class Config {
	private UserService userService;
	private QuestService questService;
	public Config(UserService userService , QuestService questService) {
		this.userService = userService;
		this.questService = questService;
	}

}
