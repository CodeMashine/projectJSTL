package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.service.UserService;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogIn implements Command {
	UserService userService;

	public LogIn(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String doPost(HttpServletRequest req) {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();

		boolean isLogIn = userService.authenticate(name, password);
//		req.setAttribute("isLogin", isLogIn);

		if (isLogIn) {
			UserDTO userDTO = userService.getUserByName(name);
			session.setAttribute("user", userDTO);
		}

		return isLogIn ? Urls.HOME : Urls.LOGIN;
	}
}
