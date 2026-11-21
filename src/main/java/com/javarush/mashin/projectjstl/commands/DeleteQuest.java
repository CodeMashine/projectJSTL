package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.service.QuestService;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteQuest implements Command {

	QuestService questService;
	public DeleteQuest(QuestService questService) {
		this.questService = questService;
	}

	@Override
	public String doPost(HttpServletRequest req) {
		Integer id = Integer.parseInt(req.getParameter("id"));
		questService.deleteQuest(id);
		return "redirect:" + Urls.QUEST_MANAGEMENT;
	}
}
