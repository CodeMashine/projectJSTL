package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.questParser.JsonQuestParser;
import com.javarush.mashin.projectjstl.questParser.QuestParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public class QuestPage implements Command {

	private JsonQuestParser jsonQuestParser = BeanFactory.getBean(JsonQuestParser.class);

	@Override
	public String doGet(HttpServletRequest req) {
		String questName = req.getParameter("quest");
		String currentStep = req.getParameter("currentStep");
		if (questName == null) {
			questName = "testQuest";
		}

		if (currentStep == null) {
			currentStep = "start";
		}

		Map<String, QuestionDTO> quest = jsonQuestParser.getQuest(questName);
		QuestionDTO questionDTO = quest.get(currentStep);

		HttpSession session = req.getSession();
		session.setAttribute("quest",questName );
		session.setAttribute("currentStep", currentStep);
		session.setAttribute("question", questionDTO);
		return null;
	}
}
