package com.marinete.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import com.marinete.bean.Usuario;
import com.marinete.jdbc.ConnectionFactory;

public class UsuarioDAO {

	private static Connection currentCon = null;
	private static ResultSet rs = null;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private String nome;
	private String senha;

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
		nome = null;
		senha = null;

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

	public String logar(Usuario usuario) {
		Init();
		String retorno = null;

		nome = usuario.getNome();
		senha = usuario.getSenha();

		String searchQuery = "select * from usuario where nome='" + nome + "' AND senha='" + senha + "'";

		try {
			
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean cadastrado = rs.next();

			if (cadastrado) {
				retorno = "1";
			} else {
				retorno = "Usuário e/ou senha inválidos!";
			}
		}

		catch (Exception ex) {
			retorno = "Login falhou: " + ex;
		}

		finally {
			Close();
		}

		return retorno;
	}

	public String cadastrar(Usuario usuario) {
		Init();
		String retorno = null;

		try {
			// cria um preparedStatement
			String sql = "insert into usuario (nome, senha) values (?,?)";
			pstmt = (PreparedStatement) currentCon.prepareStatement(sql);

			// atribuir valores as variaveis ?
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getSenha());

			// executa
			pstmt.execute();
			pstmt.close();
			
			// retorna sucesso
			retorno = "1";
		} catch (Exception ex) {
			// retorna falha no cadastro
			retorno = "Cadastro Falhou: " + ex;
		} finally {
			Close();
		}

		return retorno;
	}
}
