package com.javarush.mashin.projectjstl.entity;

public class PlayerDTO {
	private String name;
	private int questPlayed = 0;
	private int questWin = 0;
	private int questLose = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuestLose() {
		return questLose;
	}

	public void addQuestLose() {
		this.questLose +=1;
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
