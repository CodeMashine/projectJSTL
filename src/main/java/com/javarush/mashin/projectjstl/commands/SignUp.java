package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.service.UserService;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SignUp implements Command {

	private UserService userService;

	public SignUp() {
		userService = BeanFactory.getBean(UserService.class);
	}

	@Override
	public String doPost(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String username = req.getParameter("name");
		String password = req.getParameter("password");
		String rawRole = req.getParameter("role");
		ROLE role = ROLE.valueOf(rawRole.toUpperCase());
		UserDTO user = userService.createUser(username, password, role);

		session.setAttribute("user", user);
		session.setAttribute("haveLoginUser", true);
		return Urls.HOME;
	}
}
