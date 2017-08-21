package br.com.marineteapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import br.com.marineteapp.bean.Marinete;

public class MarineteDAO extends Utils {

	// Retornar lista de marinetes por ordem decrescente de avaliação
	public List<Marinete> listarMarinetes() {
		Connection conn = null;
		String searchQuery = "select p.*, m.avaliacao from marinete m, pessoa p "
				+ "where m.id = p.id "
				+ "order by avaliacao desc";
		Marinete m;
		List<Marinete> marinetes = new LinkedList<>();

		try {
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchQuery);

			while (rs.next()) {
				m = new Marinete();
				m.setAvaliacao(rs.getDouble("avaliacao"));
				m.setAvalPercent((rs.getDouble("avaliacao")*100)/5);
				m.setId(rs.getInt("id"));
				m.setNome(rs.getString("nome"));
				m.setCpf(rs.getString("cpf"));
				m.setNascimento(rs.getDate("nascimento"));
				m.setSexo(rs.getString("sexo"));
				
				LocalDate nascimento = new LocalDate (rs.getDate("nascimento"));
				LocalDate now = new LocalDate();
				Years idade = Years.yearsBetween(nascimento, now);
				m.setIdade(idade.getYears());
				
				marinetes.add(m);
			}
		}

		catch (Exception ex) {
			System.out.println("Uma excessão ocorreu! " + ex);
		}

		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return marinetes;
	}
}
