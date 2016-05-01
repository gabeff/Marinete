package com.marinete.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		new ConnectionFactory();
		Connection connection = ConnectionFactory.getConnection();
		System.out.println("Conexão Aberta");
		connection.close();

	}

}
