package br.com.marineteapp.dao;

import java.util.LinkedList;
import java.util.List;

import br.com.marineteapp.bean.Marinete;

public class MarineteDAO extends ManagerDAO {

	// Retornar lista de marinetes por ordem decrescente de avaliação
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
