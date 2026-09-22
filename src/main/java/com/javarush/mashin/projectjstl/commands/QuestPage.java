package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.questParser.JsonQuestParser;
import com.javarush.mashin.projectjstl.questParser.QuestParser;
import jakarta.servlet.http.HttpServletRequest;

public class QuestPage implements Command {

	private JsonQuestParser jsonQuestParser = BeanFactory.getBean(JsonQuestParser.class);

	@Override
	public String doGet(HttpServletRequest req) {
		String questName = req.getParameter("quest");
		jsonQuestParser.getQuest(questName);
		return null;
	}
}
