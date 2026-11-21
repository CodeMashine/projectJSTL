package com.javarush.mashin.projectjstl.filter;

import com.javarush.mashin.projectjstl.config.BeanFactory;
import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.service.UserService;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({Urls.SIGNUP})
public class CreateUserFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		if (req.getMethod().equals("GET")) {
			chain.doFilter(req, res);
		} else {
			UserService userService = BeanFactory.getBean(UserService.class);
			HttpSession session = req.getSession();
			String username = req.getParameter("name");
			String password = req.getParameter("password");
			String role = req.getParameter("role");

			Integer loginId = userService.authenticate(username, password);
			if (loginId != null) {
				res.sendRedirect(Urls.LOGIN);
			} else {

				chain.doFilter(req, res);
			}
		}
	}
}
