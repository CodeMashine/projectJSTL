package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;
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
		UserDTO player = getUser(req, session);

		Map<String, QuestionDTO> quest = questParser.getQuest(questName);
		QuestionDTO questionDTO = quest.get(nextStep);

		if (!nextStep.equals(prevStep)) {
			countOutcome(questionDTO, player);
		}

		session.setAttribute("quest", questName);
		session.setAttribute("currentStep", nextStep);
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

	// need maybe
	private String getOrDefaultQuestName(HttpServletRequest req) {
		String questName = req.getParameter("quest");
		if (questName == null) {
			questName = "elfQuest";
		}
		return questName;
	}

	// maybe need send exception on front
	private UserDTO getUser(HttpServletRequest req, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			throw new ProjectJSTLException("User not found");
		}
		return user;
	}

	private void countOutcome(QuestionDTO questionDTO, UserDTO user) {
		if (questionDTO.isTerminal() && questionDTO.isWin()) {
			user.addQuestWin();
			user.addQuestPlayed();
		} else if (questionDTO.isTerminal() && !questionDTO.isWin()) {
			user.addQuestLose();
			user.addQuestPlayed();
		}
	}
}
