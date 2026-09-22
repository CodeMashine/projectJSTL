package com.javarush.mashin.projectjstl.controllers;

import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.commands.Command;
import com.javarush.mashin.projectjstl.workers.AppConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/", "", "/quest-page"})
public class FrontController extends HttpServlet {
	private final HttpResolver httpResolver = BeanFactory.getBean(HttpResolver.class);

	@Override
	public void init() throws ServletException {
		super.init();
		AppConfig config = BeanFactory.getBean(AppConfig.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Command command = httpResolver.resolve(req);
		command.doGet(req);
		String componentPath = command.getView();
		String jsp = getJsp(componentPath);
		req.getRequestDispatcher(jsp).forward(req, resp);
	}

	private String getJsp(String view) {
		return "WEB-INF/" + view + ".jsp";
	}

}
