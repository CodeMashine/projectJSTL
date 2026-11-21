package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class EditUsers implements Command {
	UserService userService;
	public EditUsers(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String doGet(HttpServletRequest req) {
		List<UserDTO> users = userService.getAllUsers() ;

		req.setAttribute("editUsersList", users);
		return getView();
	}
}