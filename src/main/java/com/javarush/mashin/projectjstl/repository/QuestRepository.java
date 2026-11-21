package com.javarush.mashin.projectjstl.repository;

import com.javarush.mashin.projectjstl.entity.QuestEntity;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestRepository extends Repository {

	public QuestRepository() {
		checkOrCreateQuestTable();
	}

	public List<QuestEntity> getAll() {
		try (Connection conn = getConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from quests");
			ResultSet resultSet = ps.executeQuery();
			List<QuestEntity> quests = new ArrayList<>();
			try {
				while (resultSet.next()) {
					quests.add(parseToEntity(resultSet));
				}
			} catch (SQLException e) {
				throw new ProjectJSTLException(e);
			}
			return quests;
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

	public QuestEntity getById(int id) {
		try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(
				"select * from quests  where id = ?")) {
			pstmt.setInt(1, id);
			ResultSet rawQuest = pstmt.executeQuery();
			if (rawQuest.next()) {
				return parseToEntity(rawQuest);
			} else {
				throw new ProjectJSTLException("No quest found with id " + id);
			}
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

	public void saveQuest(String questName, String questXML, String questJson) {
		try (Connection conn = getConnection()) {
			String saveQuestSQL = "INSERT INTO  quests(quest_name, quest_xml , quest_json) VALUES (?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(saveQuestSQL);
			stmt.setString(1, questName);
			stmt.setString(2, questXML);
			stmt.setString(3, questJson);
			stmt.execute();
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

	public void updateQuest(int questId , String questName, String questXML, String questJson) {
		try (Connection conn = getConnection()) {
			String saveQuestSQL = "UPDATE quests SET quest_name = ?, quest_xml = ?, quest_json = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(saveQuestSQL);
			stmt.setString(1, questName);
			stmt.setString(2, questXML);
			stmt.setString(3, questJson);
			stmt.setInt(4, questId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

	private void checkOrCreateQuestTable() {
		String createQuestTable = "CREATE TABLE IF NOT EXISTS quests(" + "id SERIAL PRIMARY KEY" + ",quest_name "
				+ "VARCHAR(30) NOT NULL" + ",quest_xml TEXT NOT NULL, quest_json TEXT NOT NULL)";

		executeMyQuery(createQuestTable);
	}

	private QuestEntity parseToEntity(ResultSet rawQuest) {
		try {
			int questId = rawQuest.getInt("id");
			String questName = rawQuest.getString("quest_name");
			String questXml = rawQuest.getString("quest_xml");
			String questJson = rawQuest.getString("quest_json");
			return new QuestEntity(questId, questName, questXml, questJson);
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

	public void deleteQuest(Integer id) {
		try (Connection conn = getConnection()) {
			String saveQuestSQL = "DELETE from quests WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(saveQuestSQL);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}
}
