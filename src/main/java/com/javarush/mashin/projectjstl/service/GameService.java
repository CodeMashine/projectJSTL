package com.javarush.mashin.projectjstl.service;

import com.javarush.mashin.projectjstl.entity.QuestDTO;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.entity.UserDTO;

public class GameService {
	private QuestService questService;
	private UserService userService;

	public GameService(QuestService questService, UserService userService) {
		this.questService = questService;
		this.userService = userService;
	}

	public void step(QuestDTO quest, QuestionDTO currentQuestion, UserDTO user) {
		int userId = user.getId();
		int questID = quest.getQuestId();
		userService.setGameInProgress(userId, questID, currentQuestion.getId());
			if (currentQuestion.isLose()) {
				userService.addLoseGame(user, questID);
			} else if (currentQuestion.isWin()){
				userService.addWinGame(user, questID);
			}
		}

}
