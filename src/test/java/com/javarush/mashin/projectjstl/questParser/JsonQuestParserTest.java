package com.javarush.mashin.projectjstl.questParser;

import com.javarush.mashin.projectjstl.entity.QuestionDTO;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonQuestParserTest {
	private static final String TEST_QUEST = "testQuest";
	private static final String UNKNOWN_QUEST = "unknownQuest";
	private static final String START_QUEST = "start";

	private JsonQuestParser parser;


	@BeforeEach
	void setUp() {
		parser = new JsonQuestParser();
	}

	@Test
	void questIsParsedCorrectly() {
		Map<String, QuestionDTO> quest = parser.getQuest(TEST_QUEST);
		assertNotNull(quest);
		assertFalse(quest.isEmpty());
	}

	@Test
	void questHaveStart() {
		Map<String, QuestionDTO> quest = parser.getQuest(TEST_QUEST);
		assertTrue(quest.containsKey(START_QUEST));
	}

	@Test
	void questHaveWin() {
		Map<String, QuestionDTO> quest = parser.getQuest(TEST_QUEST);
		boolean hasWinStep = false;
		for (QuestionDTO questionDTO : quest.values()) {
			if (questionDTO.isTerminal()) {
				if (questionDTO.isWin()) {
					hasWinStep = true;
				}
			}
		}
		assertTrue(hasWinStep);
	}

	@Test
	void questHaveLose() {
		Map<String, QuestionDTO> quest = parser.getQuest(TEST_QUEST);
		boolean hasLoseStep = false;
		for (QuestionDTO questionDTO : quest.values()) {
			if (questionDTO.isTerminal()) {
				if (!questionDTO.isWin()) {
					hasLoseStep = true;
				}
			}
		}
		assertTrue(hasLoseStep);
	}

	@Test
	void noSuchQuestThrowsException() throws ProjectJSTLException {
		assertThrows(ProjectJSTLException.class, () -> {parser.getQuest(UNKNOWN_QUEST);});
	}

	@Test
	void getSameQuestObject() {
		Map<String, QuestionDTO> quest = parser.getQuest(TEST_QUEST);
		Map<String, QuestionDTO> questTwo = parser.getQuest(TEST_QUEST);
		assertSame(quest, questTwo);
	}

	@Test
	void noTerminalStepsHaveChoice() {
		Map<String, QuestionDTO> quest = parser.getQuest(TEST_QUEST);
		for (QuestionDTO questionDTO : quest.values()) {
			if (!questionDTO.isTerminal()) {
				assertTrue(questionDTO.getChoices().length > 0);
			}
		}
	}

	@Test
	void terminalStepsHaveNotChoice() {
		Map<String, QuestionDTO> quest = parser.getQuest(TEST_QUEST);
		for (QuestionDTO questionDTO : quest.values()) {
			if (questionDTO.isTerminal()) {
				assertEquals(0, questionDTO.getChoices().length);
			}
		}
	}

}