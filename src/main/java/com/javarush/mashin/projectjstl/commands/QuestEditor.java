package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.entity.QuestEntity;
import com.javarush.mashin.projectjstl.service.QuestService;
import jakarta.servlet.http.HttpServletRequest;

public class QuestEditor implements Command {
	QuestService questService;
	public QuestEditor(QuestService questService) {
		this.questService = questService;
	}

	@Override
	public String doGet(HttpServletRequest req) {
		String questIdRaw = (String)req.getParameter("questId");
		if (questIdRaw != null) {
			Integer questId = Integer.parseInt(questIdRaw);
			QuestEntity questEntity = questService.getQuestEntity(questId);
			req.setAttribute("questId", questEntity.getId());
			req.setAttribute("questName", questEntity.getName());
			req.setAttribute("questXML", questEntity.getRawQuestXml());
		}
		return getView();
	}
}
