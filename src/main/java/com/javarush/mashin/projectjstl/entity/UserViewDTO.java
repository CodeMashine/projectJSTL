package com.javarush.mashin.projectjstl.entity;

public class UserViewDTO {

	private final String name;
	private final int questPlayed;
	private final int questWin;
	private final int questLose;
	private final int id;

	private UserViewDTO(UserDTO user) {
		this.name = user.getName();
		this.id = user.getId();
		this.questPlayed = user.getQuestPlayed();
		this.questWin = user.getQuestWin();
		this.questLose = user.getQuestLose();
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
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

	public static UserViewDTO from(UserDTO user) {
		return new UserViewDTO(user);
	}
}
