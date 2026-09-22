package com.javarush.mashin.projectjstl.commands;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
	private String message;

	public void init() {
		message = "Hello World! dude";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/hello-world.jsp").forward(request, response);
//		response.setContentType("text/html");
//
//		// Hello
//		PrintWriter out = response.getWriter();
//		out.println("<html><body>");
//		out.println("<h1>" + message + "</h1>");
//		out.println("</body></html>");
	}

	public void destroy() {
	}
}