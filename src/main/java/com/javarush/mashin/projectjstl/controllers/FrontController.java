package com.javarush.mashin.projectjstl.controllers;

import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.commands.Command;
import com.javarush.mashin.projectjstl.service.UserService;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({Urls.ROOT, Urls.HOME, Urls.QUEST_PAGE, Urls.SIGNUP, Urls.LOGOUT, Urls.LOGIN, Urls.STATISTIC})
public class FrontController extends HttpServlet {
	private final HttpResolver httpResolver = BeanFactory.getBean(HttpResolver.class);

	@Override
	public void init() throws ServletException {
		super.init();
		BeanFactory.getBean(UserService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Command command = httpResolver.resolve(req);
		String componentPath = command.doGet(req);
		String jsp = getJsp(componentPath);
		req.getRequestDispatcher(jsp).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Command command = httpResolver.resolve(req);
		String componentPath = command.doPost(req);
		String jsp = getJsp(componentPath);
		req.getRequestDispatcher(jsp).forward(req, resp);
	}

	private String getJsp(String view) {
		return "WEB-INF/" + view + ".jsp";
	}

}
