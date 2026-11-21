package com.javarush.mashin.projectjstl.repository;

import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.entity.UserEntity;
import com.javarush.mashin.projectjstl.entity.UserStatEntity;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

import java.security.KeyStore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository {

	public UserRepository() {
		init();
		checkOrCreateTable();
	}

	private void checkOrCreateTable() {
		String createUserTable =
				"CREATE TABLE if NOT EXISTS users(id serial PRIMARY KEY,NAME VARCHAR(30) NOT NULL," + "password " +
						"VARCHAR(30) NOT NULL,role VARCHAR(10) CHECK ( role IN ('USER', 'ADMIN')));";
		executeMyQuery(createUserTable);
	}

	public int createUser(String username, String password, String role) {
		String createUser = "INSERT INTO users(name,password,role) VALUES(?,?,?) RETURNING id";
		int newUserId = executeInsertQueryAndReturnId(createUser, username, password, role);
		return newUserId;
	}

	public void deleteUser(int id) {
		String sql = "DELETE FROM users WHERE id = ?";
		executeMyQuery(sql, id);
	}

	public int updateUser(int id, String username) {
		String sql = "UPDATE users SET name = ? WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setInt(2, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new ProjectJSTLException(e.getMessage());
		}
	}

	public UserEntity getByName(String name) {
		String sql = "SELECT * FROM users WHERE name=?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rsToUserEntity(rs);
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

	public UserEntity getById(int id) {
		String sql = "SELECT * FROM users WHERE id=?";
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				UserEntity res = rsToUserEntity(rs);
				return res;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

	public List<UserStatEntity> getAllUserStat() {
		String sql = "WITH u_g_p AS (SELECT user_id, COUNT(*) AS total FROM game_in_progress GROUP BY user_id) SELECT "
				+ "users.id AS id, users.role as role ,users.name AS name,played_games.game_win AS game_win,played_games.game_lose   AS " + "game_lose,played_games.game_played AS game_played,u_g_p.total AS game_in_progress_total FROM users JOIN played_games" + " ON users.id = played_games.user_id LEFT JOIN u_g_p ON users.id = u_g_p.user_id;";

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			List<UserStatEntity> list = new ArrayList<>();
			while (rs.next()) {
				String name = rs.getString("name");
				String role = rs.getString("role");
				int gameWin = rs.getInt("game_win");
				int gameLose = rs.getInt("game_lose");
				int gameTotal = rs.getInt("game_played");
				int gameInProgresTotal = rs.getInt("game_in_progress_total");
				list.add(new UserStatEntity(name, role, gameWin, gameLose, gameTotal, gameInProgresTotal));
			}
			return list;
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

	public List<UserEntity> getAll() {
		String sql = "SELECT * FROM users";
		List<UserEntity> users = new ArrayList<>();

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				users.add(rsToUserEntity(rs));
			}
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
		return users;
	}


	private UserEntity rsToUserEntity(ResultSet rs) {
		try {
			int id = rs.getInt("id");
			String username = rs.getString("name");
			String password = rs.getString("password");
			String role = rs.getString("role");
			return new UserEntity(id, username, password, role);
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

}
