package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.config.BeanFactory;
import com.javarush.mashin.projectjstl.service.QuestService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public class QuestManagement implements Command {

	@Override
	public String doGet(HttpServletRequest req) {
		QuestService questService = BeanFactory.getBean(QuestService.class);
		Map<Integer, String> quests = questService.getAllQuests();
		req.setAttribute("quests", quests);
		return getView();
	}
}
