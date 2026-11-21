package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.config.BeanFactory;
import com.javarush.mashin.projectjstl.service.QuestService;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public class SaveQuest implements Command{

	QuestService questService;

	public SaveQuest(QuestService questService) {
		this.questService = questService;
	}

	@Override
	public String doGet(HttpServletRequest req) {
		return Urls.QUESTS ;
	}


	@Override
	public String doPost(HttpServletRequest req) {

		try{
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		String questIdRaw = (String)req.getParameter("questId");
		String questName = req.getParameter("questName");
		String questXml = req.getParameter("questXml");
		String questJson = req.getParameter("questJson");

		if (questIdRaw != null) {
			int questId = Integer.parseInt(questIdRaw);
			questService.updateQuest(questId,questName,questXml,questJson);
		}else{
			questService.saveQuest(questName,questXml,questJson);
		}


		return Urls.QUESTS ;
	}
}
