package com.javarush.mashin.projectjstl.util;

import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

public class Urls {

	private Urls() {
		throw new ProjectJSTLException("This class cannot be instantiated");
	}

	public static final String ROOT = "";
	public static final String HOME = "/home";

	public static final String SIGNUP = "/sign-up";
	public static final String STATISTIC = "/statistic";
	public static final String LOGIN = "/log-in";
	public static final String LOGOUT = "/log-out";
	public static final String QUEST_PAGE = "/quest-page";
}
