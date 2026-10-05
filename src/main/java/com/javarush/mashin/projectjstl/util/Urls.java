package com.javarush.mashin.projectjstl.util;

import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

public class Urls {

	private Urls() {
		throw new ProjectJSTLException("This class cannot be instantiated");
	}

	public static final String INDEX = "";
	public static final String HOME = "/home";

	public static final String SIGNUP = "/sign-up";
	public static final String LOGIN = "/log-in";
	public static final String LOGOUT = "/logout";
	public static final String PROFILE = "/profile";
	public static final String LIST_USER = "/list-user";
	public static final String EDIT_USER = "/edit-user";

	public static final String CREATE = "/create-quest";
	public static final String QUEST = "/quest";
	public static final String STAT = "/stat";
	public static final String PLAY_GAME = "/play-game";
}
