package br.com.marineteapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import br.com.marineteapp.bean.Marinete;
import br.com.marineteapp.jdbc.ConnectionFactory;

public class MarineteDAO {
	
	private static Connection currentCon = null;
	private static ResultSet rs = null;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	
	private void Init(){
		Close();
		try {
			if (currentCon == null || currentCon.isClosed()) {
				currentCon = ConnectionFactory.getConnection();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void Close() {

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

	public List<Marinete> listarMarinetes() {
		Init();
		String searchQuery = "select * from marinete order by avaliacao desc";
		Marinete m;
		List<Marinete> marinetes = new LinkedList<>();

		try {
			
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);

			while (rs.next()) {
				m = new Marinete();
				m.setAvaliacao(rs.getDouble("avaliacao"));
				m.setCidade(rs.getString("cidade"));
				m.setEstado(rs.getString("estado"));
				m.setId(rs.getInt("id"));
				m.setNascimento(rs.getString("nascimento"));
				m.setNome(rs.getString("nome"));
				marinetes.add(m);
			}
		}

		catch (Exception ex) {
			System.out.println("Uma excessão ocorreu! " + ex);
		}

		finally {
			Close();
		}

		return marinetes;
	}
}
