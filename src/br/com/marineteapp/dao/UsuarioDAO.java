package br.com.marineteapp.dao;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.google.gson.JsonObject;

import java.sql.PreparedStatement;

import br.com.marineteapp.bean.Usuario;
import br.com.marineteapp.jdbc.ConnectionFactory;

public class UsuarioDAO extends ManagerDAO {

	private String nome;
	private String senha;

	// Logar Usuario
	public Usuario logar(Usuario u) {
		Init();
		Usuario usuario = new Usuario();

		nome = u.getNome();
		senha = u.getSenha();
		
		// Buscar Usuario no banco por seu nome e senha
		String searchQuery = "select * from usuario where nome='" + nome + "' AND senha='" + senha + "'";

		try {

			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean cadastrado = rs.next();

			// Se o rs (result set) retornou alguma linha é porque o login e senha são válidos
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
	
	// Gerar um token para a sessão do usuario
	public String issueToken(Usuario usuario) {
		Init();
		String token = null;

		try {
			// cria um preparedStatement
			String sql = "update usuario set token = ? where nome = ?";
			pstmt = (PreparedStatement) currentCon.prepareStatement(sql);

			// gerar token
			token = gerarToken();
			
			// atribuir valores as variaveis ?
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
	
	// gerar token, string aleatorio de 32 caracteres
	private String gerarToken() {
		Random random = new SecureRandom();
		String token = null;
		token = new BigInteger(130, random).toString(32);
		return token;
	}
	
	// validar sessão do usuario através do token fornecido
	public Usuario getUsuarioByToken(String token) {
		Init();
		Usuario usuario = new Usuario();

		// buscar no banco se o token existe e a qual usuario ele esta atribuido
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

	// cadastrar usuario
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
