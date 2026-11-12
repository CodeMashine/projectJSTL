package com.javarush.mashin.projectjstl.service;

import com.javarush.mashin.projectjstl.entity.QuestDTO;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.entity.UserDTO;

public class GameService {
	private QuestService questService;
	public GameService(QuestService questService) {
		this.questService = questService;
	}


	public void step(QuestDTO quest, QuestionDTO currentQuestion, UserDTO loggedUser) {
		if(currentQuestion.isLose() || currentQuestion.isWin()){
			loggedUser.addQuestPlayed();
			if(currentQuestion.isLose()){
				loggedUser.addQuestLose();
			}else{
				loggedUser.addQuestWin();
			}
		}
	}
}
