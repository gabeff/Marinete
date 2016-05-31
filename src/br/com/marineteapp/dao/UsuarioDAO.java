package br.com.marineteapp.dao;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.sql.PreparedStatement;

import br.com.marineteapp.bean.Usuario;
import br.com.marineteapp.jdbc.ConnectionFactory;

public class UsuarioDAO {

	private static Connection currentCon = null;
	private static ResultSet rs = null;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private String nome;
	private String senha;

	private void Init() {
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

	public Usuario logar(Usuario u) {
		Init();
		Usuario usuario = new Usuario();

		nome = u.getNome();
		senha = u.getSenha();

		String searchQuery = "select * from usuario where nome='" + nome + "' AND senha='" + senha + "'";

		try {

			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean cadastrado = rs.next();

			if (cadastrado) {
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
			} else {
				usuario = null;
			}
		}

		catch (Exception ex) {
			System.out.println(ex.getMessage());
			usuario = null;
		}

		finally {
			Close();
		}

		return usuario;
	}

	public String issueToken(Usuario usuario) {
		Init();
		String token = null;

		try {
			// cria um preparedStatement
			String sql = "update usuario set token = ? where nome = ?";
			pstmt = (PreparedStatement) currentCon.prepareStatement(sql);

			// atribuir valores as variaveis ?
			token = gerarToken();
			pstmt.setString(1, token);
			pstmt.setString(2, usuario.getNome());

			// executa
			pstmt.execute();
			pstmt.close();
		} catch (Exception ex) {
			// retorna falha na geração do token
			token = null;
			System.out.println("Erro ao gerar token: " + ex.getMessage());
		} finally {
			Close();
		}

		return token;
	}
	
	private String gerarToken() {
		Random random = new SecureRandom();
		String token = null;
		token = new BigInteger(130, random).toString(32);
		return token;
	}
	
	public Usuario getUsuarioByToken(String token) {
		Init();
		Usuario usuario = new Usuario();

		String searchQuery = "select * from usuario where token='" + token + "'";

		try {

			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean cadastrado = rs.next();

			if (cadastrado) {
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
			} else {
				usuario = null;
			}
		}

		catch (Exception ex) {
			System.out.println(ex.getMessage());
			usuario = null;
		}

		finally {
			Close();
		}

		return usuario;
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
