package com.javarush.mashin.projectjstl.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.entity.PlayerDTO;
import com.javarush.mashin.projectjstl.questParser.JsonQuestParser;
import com.javarush.mashin.projectjstl.questParser.QuestParser;

import java.util.Map;

public class QuestPage implements Command {

	private QuestParser questParser = BeanFactory.getBean(JsonQuestParser.class);

	@Override
	public String doGet(HttpServletRequest req) {
		HttpSession session = req.getSession();

		String questName = getOrDefaultQuestName(req);
		String nextStep = getOrDefaultQuestStep(req);
		String prevStep = (String) session.getAttribute("currentStep");
		PlayerDTO player = getOrCreatePlayer(req, session);

		Map<String, QuestionDTO> quest = questParser.getQuest(questName);
		QuestionDTO questionDTO = quest.get(nextStep);

		if (!nextStep.equals(prevStep)) {
			countOutcome(questionDTO, player);
		}

		session.setAttribute("quest", questName);
		session.setAttribute("currentStep", nextStep);
		session.setAttribute("player", player);
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

	private PlayerDTO getOrCreatePlayer(HttpServletRequest req, HttpSession session) {
		PlayerDTO player = (PlayerDTO) session.getAttribute("player");
		String playerName = req.getParameter("playerName");

		if (player == null) {
			player = new PlayerDTO();
			player.setName(playerName);
		}else{
			player.setName(playerName);
		}
		return player;
	}

	private void countOutcome(QuestionDTO questionDTO, PlayerDTO player) {
		if (questionDTO.isTerminal() && questionDTO.isWin()) {
			player.addQuestWin();
			player.addQuestPlayed();
		} else if (questionDTO.isTerminal() && !questionDTO.isWin()) {
			player.addQuestLose();
			player.addQuestPlayed();
		}
	}
}
