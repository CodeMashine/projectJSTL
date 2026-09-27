package com.javarush.mashin.projectjstl.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDTOTest {
	private static final int EXPECTED = 5;

	private PlayerDTO playerDTO;

	@BeforeEach
	void setUp() {
		playerDTO = new PlayerDTO();
	}

	@Test
	void checkStartState() {
		assertEquals(0 , playerDTO.getQuestWin());
		assertEquals(0 , playerDTO.getQuestLose());
		assertEquals(0 , playerDTO.getQuestPlayed());
	}

	@Test
	void getQuestLose() {
		for (int i = 0; i < EXPECTED; i++) {
			playerDTO.addQuestLose();
		}
		assertEquals(EXPECTED, playerDTO.getQuestLose());
	}

	@Test
	void getQuestPlayed() {
		for (int i = 0; i < EXPECTED; i++) {
			playerDTO.addQuestPlayed();
		}
		assertEquals(EXPECTED, playerDTO.getQuestPlayed());
	}

	@Test
	void getQuestWin() {
		for (int i = 0; i < EXPECTED; i++) {
			playerDTO.addQuestWin();
		}
		assertEquals(EXPECTED, playerDTO.getQuestWin());
	}

}