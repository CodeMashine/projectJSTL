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
		HttpSession session = req.getSession();
		int userId = (Integer) session.getAttribute("loginId");
		UserDTO userDTO = userService.getUserDTO(userId);
		session.setAttribute("user", userDTO);
		return "redirect:" + Urls.HOME;
	}
}
