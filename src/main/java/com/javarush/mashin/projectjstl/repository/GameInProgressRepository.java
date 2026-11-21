package com.javarush.mashin.projectjstl.repository;

import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GameInProgressRepository extends Repository {

	public GameInProgressRepository() {
		checkOrCreateTable();
	}

	private void checkOrCreateTable() {
		String createGameInProgressTable = "CREATE TABLE if NOT EXISTS game_in_progress(id serial PRIMARY KEY,user_id "
				+ "INT REFERENCES users(id) ON DELETE CASCADE ,quest_id INT NOT NULL REFERENCES quests(id)  ON DELETE "
				+ "CASCADE,step_id VARCHAR(10) NOT NULL,UNIQUE (user_id, " + "quest_id) );";

		executeMyQuery(createGameInProgressTable);
	}

	public void setGameInProgress(int userId, int questId, String stepId) {
		String sql = "INSERT INTO game_in_progress(user_id,quest_id,step_id) VALUES(?,?,?) ON CONFLICT (user_id," +
				"quest_id) DO UPDATE SET step_id = EXCLUDED.step_id;";
		executeMyQuery(sql, userId, questId, stepId);
	}

	public void removeGameInProgress(int userId, int questId) {
		String sql = "DELETE FROM game_in_progress WHERE user_id=? AND quest_id=?";
		executeMyQuery(sql, userId, questId);
	}

	public Map<Integer, String> getAllGameInProgressForUser(int userId) {
		String sql = "SELECT quest_id , step_id FROM game_in_progress WHERE user_id=?";
		Map<Integer, String> map = new HashMap<>();
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				map.put(rs.getInt("quest_id"), rs.getString("step_id"));
			}
			return map;
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

}
