package com.javarush.mashin.projectjstl.config;

import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.repository.GameInProgressRepository;
import com.javarush.mashin.projectjstl.service.QuestService;
import com.javarush.mashin.projectjstl.service.UserService;

public class Config {
	private UserService userService;
	private QuestService questService;
	private GameInProgressRepository gameInProgressRepository;

	public Config(UserService userService, QuestService questService,GameInProgressRepository gameInProgressRepository) {
		this.userService = userService;
		this.questService = questService;
		this.gameInProgressRepository = gameInProgressRepository;
//		startFillUserRepository();
	}

	private void startFillUserRepository() {
		for (int i = 0; i < 3; i++) {
			userService.addUser("User " + i, "123", i % 2 == 0 ? ROLE.USER : ROLE.ADMIN);
		}
	}
}





