package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.entity.UserStatDTO;
import com.javarush.mashin.projectjstl.entity.UserStatEntity;
import com.javarush.mashin.projectjstl.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class Statistic implements Command {
	UserService userService;
	public Statistic(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String doGet(HttpServletRequest req) {
		List<UserStatEntity> users = userService.getUsersStatistic() ;
		req.setAttribute("usersStatistic", users);
		return getView();
	}
}
