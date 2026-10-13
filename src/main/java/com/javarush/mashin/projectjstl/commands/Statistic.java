package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.entity.UserViewDTO;
import com.javarush.mashin.projectjstl.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class Statistic implements Command {
	UserService userService;
	public Statistic() {
		userService = BeanFactory.getBean(UserService.class);
	}

	@Override
	public String doGet(HttpServletRequest req) {
		List<UserViewDTO> users = userService.getUsersStatistic() ;
		req.setAttribute("usersStatistic", users);
		return getView();
	}
}
