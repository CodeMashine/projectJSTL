package com.javarush.mashin.projectjstl.entity;

import java.util.HashMap;
import java.util.Map;

public class UserDTO {
	private int id;
	private String name;
	private ROLE role;
	private Map<Integer, String> gamesInProgress = new HashMap<>();

	public UserDTO() {
	}

	public UserDTO(int id, String name, ROLE role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}

	public Map<Integer, String> getGamesInProgress() {
		return gamesInProgress;
	}

	public void setGameInProgress(Map<Integer, String> gameInProgress) {
		this.gamesInProgress = gameInProgress;
	}

	public void removeGameInProgress(int gameId) {
		gamesInProgress.remove(gameId);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ROLE getRole() {
		return role;
	}


	public void setName(String name) {
		this.name = name;
	}
}
