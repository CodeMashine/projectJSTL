package com.javarush.mashin.projectjstl.entity;

public class UserDTO {
	private int id;
	private String name;
	private String password;
	private ROLE role;
	private int questPlayed = 0;
	private int questWin = 0;
	private int questLose = 0;

	public UserDTO() {}
	public UserDTO(int id, String name, String password , ROLE role ) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
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

	public boolean checkPassword(String raw){
		return password.equals(raw);
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuestLose() {
		return questLose;
	}

	public void addQuestLose() {
		this.questLose += 1;
	}

	public int getQuestPlayed() {
		return questPlayed;
	}

	public void addQuestPlayed() {
		this.questPlayed += 1;
	}

	public int getQuestWin() {
		return questWin;
	}

	public void addQuestWin() {
		this.questWin += 1;
	}
}
