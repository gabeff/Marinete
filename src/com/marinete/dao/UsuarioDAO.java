package com.marinete.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import com.marinete.bean.Usuario;
import com.marinete.jdbc.ConnectionFactory;

public class UsuarioDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null;
	private String nome;
	private String senha;
	private Statement stmt;
	
	public String login(Usuario usuario) {
		String retorno = null;
		
		stmt = null;

		nome = usuario.getNome();
		senha = usuario.getSenha();

		String searchQuery = "select * from usuario where nome='" + nome
				+ "' AND senha='" + senha + "'";

		try {
			currentCon = ConnectionFactory.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean cadastrado = rs.next();

			if (!cadastrado) {
				retorno = "Login ou Senha inválidos";
			}

			else if (cadastrado) {
				retorno = "Bem vindo, "+nome;
			}
		}

		catch (Exception ex) {
			System.out.println("Log In falhou: Uma excessão ocorreu! " + ex);
		}

		finally {
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

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}
		
		return retorno;
	}

}
