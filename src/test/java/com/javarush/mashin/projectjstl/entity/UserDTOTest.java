package com.javarush.mashin.projectjstl.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {
	private static final int EXPECTED = 5;

	private UserDTO userDTO;

	@BeforeEach
	void setUp() {
		userDTO = new UserDTO();
	}

	@Test
	void checkStartState() {
		assertEquals(0 , userDTO.getQuestWin());
		assertEquals(0 , userDTO.getQuestLose());
		assertEquals(0 , userDTO.getQuestPlayed());
	}

	@Test
	void getQuestLose() {
		for (int i = 0; i < EXPECTED; i++) {
			userDTO.addQuestLose();
		}
		assertEquals(EXPECTED, userDTO.getQuestLose());
	}

	@Test
	void getQuestPlayed() {
		for (int i = 0; i < EXPECTED; i++) {
			userDTO.addQuestPlayed();
		}
		assertEquals(EXPECTED, userDTO.getQuestPlayed());
	}

	@Test
	void getQuestWin() {
		for (int i = 0; i < EXPECTED; i++) {
			userDTO.addQuestWin();
		}
		assertEquals(EXPECTED, userDTO.getQuestWin());
	}

}