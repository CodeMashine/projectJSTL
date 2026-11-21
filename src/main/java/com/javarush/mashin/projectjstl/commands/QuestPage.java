package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.entity.QuestDTO;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.service.GameService;
import com.javarush.mashin.projectjstl.service.QuestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class QuestPage implements Command {
	QuestService questService;
	GameService gameService;

	public QuestPage(QuestService questService, GameService gameService) {
		this.questService = questService;
		this.gameService = gameService;
	}

	@Override
	public String doGet(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");

		Integer questId = getOrDefaultQuestId(req);
		String nextStep = getOrDefaultQuestStep(req);
		String previousStep = (String) session.getAttribute("previousStep");

		QuestDTO quest = questService.getQuest(questId);
		QuestionDTO currentQuestion = quest.getQuestion(nextStep);

		if (!nextStep.equals(previousStep)) {
			gameService.step(quest, currentQuestion, user );
			session.setAttribute("previousStep", nextStep);
		}

		req.setAttribute("questId", questId);
		req.setAttribute("questName", quest.getQuestName());
		req.setAttribute("currentQuestion", currentQuestion);

		return getView();
	}

	private String getOrDefaultQuestStep(HttpServletRequest req) {
		String currentStep = req.getParameter("nextStep");
		if (currentStep == null) {
			currentStep = "2";
		}
		return currentStep;
	}

	// need maybe
	private Integer getOrDefaultQuestId(HttpServletRequest req) {
		Integer questName = Integer.valueOf(req.getParameter("questId"));
		if (questName == null) {
			questName = 1;

		}
		return questName;
	}


}
