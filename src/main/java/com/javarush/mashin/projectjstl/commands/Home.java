package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.config.BeanFactory;
import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.service.QuestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Map;

public class Home implements Command {

	@Override
	public String doGet(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserDTO userDTO = (UserDTO)session.getAttribute("user");

		if (userDTO != null) {
			QuestService questService = BeanFactory.getBean(QuestService.class);
			Map<Integer, String> quests = questService.getAllQuests();
			req.setAttribute("quests", quests);
		}


		return getView();
	}
}
