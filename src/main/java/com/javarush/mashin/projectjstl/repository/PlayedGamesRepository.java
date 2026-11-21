package com.javarush.mashin.projectjstl.repository;

import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

public class PlayedGamesRepository extends Repository {

	public PlayedGamesRepository() {
		checkOrCreateTable();
	}

	private void checkOrCreateTable() {
		String createPlayedGamesTable =
				"CREATE TABLE IF NOT EXISTS played_games(id serial PRIMARY KEY,user_id INT " + "UNIQUE REFERENCES " +
						"users(id) ON DELETE CASCADE ,game_win INT DEFAULT 0,game_lose INT DEFAULT 0,game_played INT "
						+ "GENERATED " + "ALWAYS AS(game_win + game_lose) stored);";

		executeMyQuery(createPlayedGamesTable);
	}

	public void addUser(int userId) {
		String sql = "INSERT INTO played_games(user_Id ) VALUES (?)";
		executeMyQuery(sql, userId);
	}

	public void addWin(int userId) {
		String sql = "UPDATE played_games SET game_win = game_win + 1 WHERE user_id = ?";
		executeMyQuery(sql, userId);
	}

	public void addLose(int userId) {
		String sql = "UPDATE played_games SET game_lose = game_lose + 1 WHERE user_id = ?";
		executeMyQuery(sql, userId);
	}
}
