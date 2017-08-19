package br.com.marineteapp.jdbc;

import java.sql.Connection;

public class TesteConexao {
		
		public static void main(String[] argv) throws ClassNotFoundException {

	        System.out.println("-------- Connection Testing ------");

	        try {

	            Class.forName("org.postgresql.Driver");

	        } catch (ClassNotFoundException e) {

	            System.out.println("Where is your Postgres Driver?");
	            e.printStackTrace();
	            return;

	        }

	        System.out.println("Postgres Driver Registered!");

	        Connection connection = null;

	        connection = ConnectionFactory.getConnection();

	        if (connection != null) {
	            System.out.println("You made it, take control your database now!");
	        } else {
	            System.out.println("Failed to make connection!");
	        }
	    }

	}
