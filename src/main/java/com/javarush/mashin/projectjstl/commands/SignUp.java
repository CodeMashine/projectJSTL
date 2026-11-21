package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.service.UserService;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SignUp implements Command {

	private UserService userService;

	public SignUp(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String doPost(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String username = req.getParameter("name");
		String password = req.getParameter("password");
		String rawRole = req.getParameter("role");
		ROLE role = ROLE.valueOf(rawRole.toUpperCase());
		int id = userService.addUser(username, password, role);
		UserDTO userDTO = userService.getUserDTO(id);
		session.setAttribute("user", userDTO);
		return "redirect:" + Urls.HOME;
	}
}
