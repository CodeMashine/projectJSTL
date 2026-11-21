package com.javarush.mashin.projectjstl.entity;

public class QuestEntity implements Entity{
	int id;
	String name;
	String rawQuestXml;
	String rawQuestJson;

	public QuestEntity(int id, String name, String rawQuestXml, String rawQuestJson) {
		this.id = id;
		this.name = name;
		this.rawQuestJson = rawQuestJson;
		this.rawQuestXml = rawQuestXml;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRawQuestJson() {
		return rawQuestJson;
	}

	public String getRawQuestXml() {
		return rawQuestXml;
	}
}
