package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.service.UserService;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class DeleteProfile implements Command {
	UserService userService;

	public DeleteProfile(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String doPost(HttpServletRequest req) {
		HttpSession session = req.getSession();
		UserDTO userDTO = (UserDTO) session.getAttribute("user");

		int idToDel = Integer.valueOf(req.getParameter("id"));
		userService.deleteUser(idToDel);

		if (userDTO.getId() == idToDel) {
			session.invalidate();
		}

		return Urls.EDIT_USERS;
	}
}
