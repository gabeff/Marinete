package br.com.marineteapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.marineteapp.jdbc.ConnectionFactory;

public class ManagerDAO {

	protected static Connection currentCon = null;
	protected static ResultSet rs = null;
	protected static Statement stmt;
	protected static PreparedStatement pstmt;

	// Inicializar conexão com o banco
	protected void Init() {
		Close();
		try {
			if (currentCon == null || currentCon.isClosed()) {
				currentCon = ConnectionFactory.getConnection();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// Fechar conexão com o banco
	protected void Close() {

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
			}
			rs = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
			stmt = null;
		}

		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
			pstmt = null;
		}

		if (currentCon != null) {
			try {
				currentCon.close();
			} catch (Exception e) {
			}

			currentCon = null;
		}

	}

}
