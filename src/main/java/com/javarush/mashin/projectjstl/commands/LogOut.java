package com.javarush.mashin.projectjstl.commands;

import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogOut implements Command {
	@Override
	public String doGet(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return Urls.HOME;
	}
}
