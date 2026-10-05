package com.javarush.mashin.projectjstl.commands;

import jakarta.servlet.http.HttpServletRequest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Command {
	default public String doGet(HttpServletRequest req) {
		return getView();
	}

	default public String doPost(HttpServletRequest req) {
		return getView();
	}

	default String getView() {
		String simpleName = this.getClass().getSimpleName();
		simpleName = convertCamelCaseToKebabStyle(simpleName);
		return simpleName;
	}

	default String convertCamelCaseToKebabStyle(String string) {
		String snakeName = string.chars().mapToObj(s -> String.valueOf((char) s)).flatMap(
				s -> s.matches("[A-Z]") ? Stream.of("-", s) : Stream.of(s)).collect(Collectors.joining()).toLowerCase();
		return snakeName.startsWith("-") ? snakeName.substring(1) : snakeName;
	}


}
