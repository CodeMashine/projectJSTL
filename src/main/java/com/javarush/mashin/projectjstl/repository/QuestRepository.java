package com.javarush.mashin.projectjstl.repository;

import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class QuestRepository implements Repository {

	private String url = "jdbc:postgresql://localhost:9999/postgres";
	private String user = "postgres";
	private String password = "postgres";

	public QuestRepository() {
		init();
		checkOrCreateQuestTable();
	}

	private void init() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection conn = getConnection()) {

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void add(Object o) {

	}

	@Override
	public List getAll() {
		return List.of();
	}

	@Override
	public Object getById(int id) {
		return null;
	}

	@Override
	public Object remove(int id) {
		return null;
	}

	public ResultSet getQuests() {
		try (Connection conn = getConnection()) {
			PreparedStatement ps = conn.prepareStatement("select id,quest_name from quests");
			return ps.executeQuery();
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
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}

	}

	private void checkOrCreateQuestTable() {
		String createQuestTable = "CREATE TABLE IF NOT EXISTS quests("
				+ "id SERIAL PRIMARY KEY" + ",quest_name VARCHAR(30) NOT NULL" + ",quest_xml TEXT NOT NULL, quest_json TEXT NOT NULL)";

		try (Connection conn = getConnection(); Statement stmt = conn.createStatement();) {
			stmt.execute(createQuestTable);
			System.out.println("Table ready");
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}


	private Connection getConnection() {
		String url = "jdbc:postgresql://localhost:9999/postgres";
		String user = "postgres";
		String password = "postgres";

		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			if (conn != null) {
				System.out.println("Connection successful");
				return conn;
			}
			throw new ProjectJSTLException("Connection failed");
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}
}
