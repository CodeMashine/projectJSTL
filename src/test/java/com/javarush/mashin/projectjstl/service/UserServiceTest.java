package com.javarush.mashin.projectjstl.service;

import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.entity.UserViewDTO;
import com.javarush.mashin.projectjstl.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
	private static final String TEST_NAME = "testName";
	private static final String TEST_PASSWORD = "testPassword";
	private static final String TEST_PASSWORD_WRONG = "testPasswordWrong";
	private static final ROLE TEST_ROLE = ROLE.USER;

	private UserService userService;
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		this.userRepository = new UserRepository();
		this.userService = new UserService(userRepository);
	}

	@Test
	void shouldSavedUserIsSameUser() {
		UserDTO userFromService = userService.createUser(TEST_NAME, TEST_PASSWORD, TEST_ROLE);
		UserDTO userFromRepo = userRepository.getByName(TEST_NAME);
		assertSame(userFromService, userFromRepo, "user should be the same");
	}

	@Test
	void getByNameUnknownUserShouldReturnNull() {
		assertNull(userRepository.getByName(TEST_NAME));
	}

	@Test
	void authenticateCorrectlyTrue() {
		UserDTO userFromService = userService.createUser(TEST_NAME, TEST_PASSWORD, TEST_ROLE);
		assertTrue(userService.authenticate(TEST_NAME, TEST_PASSWORD));
	}

	@Test
	void authenticateCorrectlyFalse() {
		UserDTO userFromService = userService.createUser(TEST_NAME, TEST_PASSWORD, TEST_ROLE);
		assertFalse(userService.authenticate(TEST_NAME, TEST_PASSWORD_WRONG));
	}

	@Test
	void authenticateUnknownUserShouldReturnFalse(){
		assertFalse(userService.authenticate(TEST_NAME, TEST_PASSWORD));
	}

	@Test
	void createUserTwoUsersShouldHaveNotTheSame(){
		UserDTO userFromServiceOne = userService.createUser(TEST_NAME, TEST_PASSWORD, TEST_ROLE);
		UserDTO userFromServiceTwo = userService.createUser(TEST_NAME, TEST_PASSWORD, TEST_ROLE);
		assertNotEquals(userFromServiceOne, userFromServiceTwo);
	}

	@Test
	void statisticUsersShouldHaveAnotherClass(){
		assertInstanceOf(UserViewDTO.class, userService.getUsersStatistic().get(0));

	}

}