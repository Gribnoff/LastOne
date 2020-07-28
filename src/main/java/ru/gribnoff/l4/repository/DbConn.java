package ru.gribnoff.l4.repository;

import java.sql.*;

public class DbConn {
	private Connection connection;

	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:main.db");
	}

	public int execute(String query, Object... args) throws SQLException {
		try (PreparedStatement ps = connection.prepareStatement(query)) {
			for (int i = 0; i < args.length; i++) {
				ps.setObject(i + 1, args[i]);
			}
			 return ps.executeUpdate();
		}
	}

	public ResultSet select(String query, Object... args) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(query);
		for (int i = 0; i < args.length; i++) {
			ps.setObject(i + 1, args[i]);
		}
		return ps.executeQuery();
	}

	public void disconnect() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}
}
