package com.javarush.mashin.projectjstl.repository;

import com.javarush.mashin.projectjstl.entity.Entity;
import com.javarush.mashin.projectjstl.entity.QuestEntity;
import com.javarush.mashin.projectjstl.exception.ProjectJSTLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class Repository {

	protected String url = "jdbc:postgresql://localhost:9999/postgres";
	protected String user = "postgres";
	protected String password = "postgres";

	protected void init() {
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

	protected Connection getConnection() {
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

	protected void executeMyQuery(String... sqls) {
		try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
			conn.setAutoCommit(false);
			for (String sql : sqls) {
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

	protected void executeMyQuery(String query, Object... params){
		try(Connection conn = getConnection();
		PreparedStatement stmt = conn.prepareStatement(query)) {
			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}
			stmt.execute();
		}catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}

	protected int executeInsertQueryAndReturnId(String query, Object... params){
		try(Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(query)) {
			for (int i = 0; i < params.length; i++) {
				stmt.setObject(i + 1, params[i]);
			}
			ResultSet result =  stmt.executeQuery();
			result.next();
			return result.getInt(1);

		}catch (SQLException e) {
			throw new ProjectJSTLException(e);
		}
	}



}
