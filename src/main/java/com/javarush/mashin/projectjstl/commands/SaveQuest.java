package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.config.BeanFactory;
import com.javarush.mashin.projectjstl.service.QuestService;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;

public class SaveQuest implements Command{

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

		String questName = req.getParameter("questName");
		String questXml = req.getParameter("questXml");
		String questJson = req.getParameter("questJson");

		System.out.println(questXml);
		System.out.println(questJson);
		QuestService questService = BeanFactory.getBean(QuestService.class);
		questService.saveQuest(questName,questXml,questJson);

		return Urls.QUESTS ;
	}
}
