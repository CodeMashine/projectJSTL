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
//		Integer questId = (Integer) session.getAttribute("questId");

		int questId = Integer.parseInt(req.getParameter("questId"));
		String nextStep = req.getParameter("nextStep");
		String previousStep = (String) session.getAttribute("previousStep");

		QuestDTO quest = questService.getQuest(questId);
		QuestionDTO currentQuestion = quest.getQuestion(nextStep);

		gameService.step(quest, currentQuestion, user);

		req.setAttribute("questId", questId);
		req.setAttribute("questName", quest.getQuestName());
		req.setAttribute("currentQuestion", currentQuestion);

		return getView();
	}
}
