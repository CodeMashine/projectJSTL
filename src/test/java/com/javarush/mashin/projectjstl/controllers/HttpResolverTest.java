package com.javarush.mashin.projectjstl.controllers;

import com.javarush.mashin.projectjstl.commands.Command;
import com.javarush.mashin.projectjstl.commands.QuestPage;
import com.javarush.mashin.projectjstl.commands.Home;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HttpResolverTest {
	private static final String QUEST_URL = "/quest-page";
	private static final String ROOT_URL = "/";
	private static final String UNKNOWN_URL = "/unknown";
	private static final Class<QuestPage> CORRECT_COMMAND_RESPONSE = QuestPage.class;
	private static final Class<Home> CORRECT_DEFAULT_RESPONSE = Home.class;
	private HttpResolver resolver;
	private HttpServletRequest request;

	@BeforeEach
	void setUp() {
		resolver = new HttpResolver();
		request = mock(HttpServletRequest.class);
	}

	@Test
	void handleCorrectEmptyInputURL() {
		when(request.getRequestURI()).thenReturn(ROOT_URL);
		Command command = resolver.resolve(request);
		assertInstanceOf(CORRECT_DEFAULT_RESPONSE, command);
	}

	@Test
	void handleCorrectInputURL() {
		when(request.getRequestURI()).thenReturn(QUEST_URL);
		Command command = resolver.resolve(request);
		assertInstanceOf(CORRECT_COMMAND_RESPONSE, command);
	}

	@Test
	void cacheTest(){
		when(request.getRequestURI()).thenReturn(QUEST_URL);
		Command command0 = resolver.resolve(request);
		Command command1 = resolver.resolve(request);
		assertSame(command0, command1);
	}

	@Test
	void handleUnknownURL(){
		when(request.getRequestURI()).thenReturn(UNKNOWN_URL);
		assertThrows(ProjectJSTLException.class, () -> resolver.resolve(request));
	}
}