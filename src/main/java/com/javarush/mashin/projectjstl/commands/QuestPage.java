package com.javarush.mashin.projectjstl.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.questParser.JsonQuestParser;
import com.javarush.mashin.projectjstl.questParser.QuestParser;

import java.util.Map;

public class QuestPage implements Command {

	private QuestParser questParser;

	public QuestPage() {
		this.questParser = BeanFactory.getBean(JsonQuestParser.class);
	}

	@Override
	public String doGet(HttpServletRequest req) {
		HttpSession session = req.getSession();

		String questName = getOrDefaultQuestName(req);
		String nextStep = getOrDefaultQuestStep(req);
		String prevStep = (String) session.getAttribute("currentStep");
		UserDTO player = getOrCreatePlayer(req, session);

		Map<String, QuestionDTO> quest = questParser.getQuest(questName);
		QuestionDTO questionDTO = quest.get(nextStep);

		if (!nextStep.equals(prevStep)) {
			countOutcome(questionDTO, player);
		}

		session.setAttribute("quest", questName);
		session.setAttribute("currentStep", nextStep);
		session.setAttribute("user", player);
		req.setAttribute("question", questionDTO);
		return getView();
	}

	private String getOrDefaultQuestStep(HttpServletRequest req) {
		String currentStep = req.getParameter("currentStep");
		if (currentStep == null) {
			currentStep = "start";
		}
		return currentStep;
	}

	// form in HTML is select , and this data is required
	private String getOrDefaultQuestName(HttpServletRequest req) {
		String questName = req.getParameter("quest");
		if (questName == null) {
			questName = "testQuest";
		}
		return questName;
	}

	private UserDTO getOrCreatePlayer(HttpServletRequest req, HttpSession session) {
		UserDTO player = (UserDTO) session.getAttribute("user");
		String playerName = req.getParameter("playerName");

		if (player == null) {
			player = new UserDTO();
			player.setName(playerName);
		} else {
			player.setName(playerName);
		}
		return player;
	}

	private void countOutcome(QuestionDTO questionDTO, UserDTO player) {
		if (questionDTO.isTerminal() && questionDTO.isWin()) {
			player.addQuestWin();
			player.addQuestPlayed();
		} else if (questionDTO.isTerminal() && !questionDTO.isWin()) {
			player.addQuestLose();
			player.addQuestPlayed();
		}
	}
}
