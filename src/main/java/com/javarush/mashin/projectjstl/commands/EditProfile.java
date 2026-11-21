package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class EditProfile implements Command {
	private UserService userService;

	public EditProfile(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String doGet(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		UserDTO user = userService.getUserDTO(id);
		req.setAttribute("userToEdit", user);
		return getView();
	}

	@Override
	public String doPost(HttpServletRequest req) {
		HttpSession session = req.getSession();
		int idToEdit = Integer.parseInt(req.getParameter("id"));
		UserDTO currentUser = (UserDTO)session.getAttribute("user");
		int currentId = currentUser.getId();
		String nameToEdit = req.getParameter("name");
		userService.updateUser(idToEdit, nameToEdit);

		if(currentId == idToEdit) {
			currentUser.setName(nameToEdit);
//			session.setAttribute("user", editedUser);
		}
		req.setAttribute("newName", nameToEdit);
		return this.getView();
	}
}
