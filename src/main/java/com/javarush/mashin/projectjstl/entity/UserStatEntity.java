package com.javarush.mashin.projectjstl.entity;

public class UserStatEntity {
	String name;
	String role;
	int gamePlayed;
	int gameWin;
	int gameLose;
	int gameInProgress;

	public UserStatEntity(String name, String role, int gameWin, int gameLose, int gamePlayed, int gameInProgress) {
		this.name = name;
		this.role = role;
		this.gameWin = gameWin;
		this.gameLose = gameLose;
		this.gamePlayed = gamePlayed;
		this.gameInProgress = gameInProgress;
	}

	public int getGameInProgress() {
		return gameInProgress;
	}

	public int getGameLose() {
		return gameLose;
	}

	public int getGamePlayed() {
		return gamePlayed;
	}

	public int getGameWin() {
		return gameWin;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}
}
