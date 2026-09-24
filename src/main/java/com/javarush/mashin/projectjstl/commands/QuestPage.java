package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.entity.PlayerDTO;
import com.javarush.mashin.projectjstl.questParser.JsonQuestParser;
import com.javarush.mashin.projectjstl.questParser.QuestParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public class QuestPage implements Command {

	private QuestParser jsonQuestParser = BeanFactory.getBean(JsonQuestParser.class);

	@Override
	public String doGet(HttpServletRequest req) {
		HttpSession session = req.getSession();

		String questName = getOrDefaultQuestName(req);
		String currentStep = getOrDefaultQuestStep(req);
		PlayerDTO player = getOrCreatePlayer(req, session);

		Map<String, QuestionDTO> quest = jsonQuestParser.getQuest(questName);
		QuestionDTO questionDTO = quest.get(currentStep);

		checkWin(questionDTO, player);

		session.setAttribute("quest", questName);
		session.setAttribute("currentStep", currentStep);
		session.setAttribute("player", player);
		req.setAttribute("question", questionDTO);
		return null;
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
		if (player != null && playerName == null) {
			return player;
		} else if (player != null && playerName != null) {
			player.setName(playerName);
		}

		if (player == null) {
			player = new PlayerDTO();
			player.setName(playerName);
		}

		return player;
	}

	private void checkWin(QuestionDTO questionDTO, PlayerDTO player) {
		if (questionDTO.isTerminal() && questionDTO.isWin()) {
			player.addQuestWin();
			player.addQuestPlayed();
		} else if (questionDTO.isTerminal() && !questionDTO.isWin()) {
			player.addQuestLose();
			player.addQuestPlayed();
		}
	}


}
