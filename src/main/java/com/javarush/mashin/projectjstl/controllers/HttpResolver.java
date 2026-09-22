package com.javarush.mashin.projectjstl.controllers;

import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.commands.Command;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class HttpResolver {

	private final Map<String, Command> componentCash = new HashMap<>();

	public Command resolve(HttpServletRequest req) {
		String rawComponentName = req.getRequestURI();
		if(componentCash.containsKey(rawComponentName)) {
			return componentCash.get(rawComponentName);
		}
		String componentName = rawComponentName.equals("/") ? "/start-page" : rawComponentName;
		componentName = componentName.split("[/?#]")[1].replace("/", "");
		String simpleName = convertKebabStyleToCamelCase(componentName);
		String componentPackage = Command.class.getPackageName();
		String componentPath = componentPackage + "." + simpleName;
		try {
			Class<?> aClass = Class.forName(componentPath);
			Command command = (Command) BeanFactory.getBean(aClass);
			componentCash.put(rawComponentName,command);
			return command;
		} catch (ClassNotFoundException e) {
			throw new ProjectJSTLException(e);
		}
	}

	private String convertKebabStyleToCamelCase(String input) {
		StringBuilder result = new StringBuilder();
		boolean capitalizeNext = true;
		for (char c : input.toCharArray()) {
			if (c == '-') {
				capitalizeNext = true;
			} else {
				if (capitalizeNext) {
					result.append(Character.toUpperCase(c));
					capitalizeNext = false;
				} else {
					result.append(Character.toLowerCase(c));
				}
			}
		}
		return result.toString();
	}

}
