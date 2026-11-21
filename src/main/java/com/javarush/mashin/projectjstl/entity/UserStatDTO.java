package com.javarush.mashin.projectjstl.entity;

public class UserStatDTO {
	private final String name;
	private final int questPlayed;
	private final int questWin;
	private final int questLose;
	private final int gameInProgress;

	public UserStatDTO(int gameInProgress, String name, int questPlayed, int questWin, int questLose) {
		this.gameInProgress = gameInProgress;
		this.name = name;
		this.questPlayed = questPlayed;
		this.questWin = questWin;
		this.questLose = questLose;
	}

	public int getGameInProgress() {
		return gameInProgress;
	}

	public String getName() {
		return name;
	}

	public int getQuestLose() {
		return questLose;
	}

	public int getQuestPlayed() {
		return questPlayed;
	}

	public int getQuestWin() {
		return questWin;
	}
}
