package com.ictcg.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

	private static String URL = "jdbc:h2:tcp://localhost/~/test";
	private static String USER = "sa";
	private static String PASSWORD = "";

	static {
		try {

			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static Connection getConnection() {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return connection;
	}

	public static void closeAll(ResultSet resultSet, Statement statement, Connection connection) {

		if(null != resultSet) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null != statement) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}