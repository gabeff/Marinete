package br.com.marineteapp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws ClassNotFoundException {

		try {
			String dbUrl = System.getenv("JDBC_DATABASE_URL");
			return DriverManager.getConnection(dbUrl);
//			Class.forName("com.mysql.jdbc.Driver");
//			return DriverManager.getConnection("jdbc:mysql://localhost:3306/marinete?autoReconnect=true&useSSL=false", "root", "root");

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}

}