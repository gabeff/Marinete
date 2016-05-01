package com.marinete.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws ClassNotFoundException {

		try {
			//Class.forName("com.mysql.jdbc.Driver");
			String dbUrl = System.getenv("JDBC_DATABASE_URL");
			return DriverManager.getConnection(dbUrl);
			//return DriverManager.getConnection("jdbc:mysql://localhost:3306/marinete?autoReconnect=true&useSSL=false", "root", "nathan07daredevilgoteyes");

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}

}
