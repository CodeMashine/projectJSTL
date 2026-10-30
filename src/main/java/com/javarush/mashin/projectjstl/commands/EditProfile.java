package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.service.UserService;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class EditProfile implements Command {
	private UserService userService;

	public EditProfile(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String doPost(HttpServletRequest req) {
		HttpSession session = req.getSession();
		int id = Integer.parseInt(req.getParameter("id"));
		String username = req.getParameter("name");
		UserDTO user = userService.updateUser(id,username);

		session.setAttribute("user", user);
		session.setAttribute("haveLoginUser", true);
		return this.getView();
	}
}
