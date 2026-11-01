package com.javarush.mashin.projectjstl.filter;

import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.util.Urls;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebFilter({ Urls.LOGOUT, Urls.STATISTIC, Urls.QUEST_PAGE, Urls.EDIT_USERS, Urls.EDIT_PROFILE})

public class AuthorizationFilter extends HttpFilter {
	private final Map<ROLE, List<String>> permissions = Map.of(ROLE.GUEST,
			List.of(Urls.HOME, Urls.LOGIN, Urls.SIGNUP, Urls.STATISTIC), ROLE.USER,
			List.of(Urls.HOME, Urls.LOGIN, Urls.SIGNUP, Urls.STATISTIC, Urls.EDIT_PROFILE, Urls.LOGOUT,
					Urls.QUEST_PAGE), ROLE.ADMIN,
			List.of(Urls.HOME, Urls.LOGIN, Urls.SIGNUP, Urls.STATISTIC, Urls.EDIT_PROFILE, Urls.LOGOUT, Urls.EDIT_USERS,
					Urls.QUEST_PAGE));

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpSession session = req.getSession();
		UserDTO currentUser = (UserDTO) session.getAttribute("user");
		if (currentUser == null) {
			res.sendRedirect(Urls.HOME);
			return;
		}

		String rootUrl = req.getRequestURI().substring(req.getContextPath().length());
		ROLE role = currentUser.getRole();

		List<String> perms = permissions.get(role);
		if (perms.contains(rootUrl)) {
			chain.doFilter(req, res);
		} else {
			res.sendRedirect(Urls.HOME);
		}
	}
}
