package br.com.marineteapp.dao;

import java.sql.Statement;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.marineteapp.bean.Usuario;

public class UsuarioDAO extends Utils {

	// Logar Usuario
	public Usuario logar(Usuario u) {
		Connection conn = null;
		Usuario usuario = new Usuario();
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		// Buscar Usuario no banco por seu nome e senha
		String searchQuery = "select * from usuario where email='" + u.getEmail() + "' AND senha='" + u.getSenha() + "'";

		try {
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchQuery);
			boolean cadastrado = rs.next();

			// Se o rs (result set) retornou alguma linha é porque o login e senha são válidos
			if (cadastrado) {
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPessoa(pessoaDAO.getPessoaById(rs.getInt("pessoa_id")));
			} else {
				usuario = null;
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
			usuario = null;
		}

		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return usuario;
	}
	
	// Gerar um token para a sessão do usuario
	public String issueToken(Usuario usuario) {
		Connection conn = null;
		String token = null;

		try {
			conn = getConnection();
			
			// cria um preparedStatement
			String sql = "update usuario set token = ? where email = ?";
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			// gerar token
			token = gerarToken();
			
			// atribuir valores as variaveis
			pstmt.setString(1, token);
			pstmt.setString(2, usuario.getEmail());

			// executa
			pstmt.execute();
			pstmt.close();
		} catch (Exception ex) {
			// retorna falha na geração do token
			token = null;
			System.out.println("Erro ao gerar token: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		Connection conn = null;
		Usuario usuario = new Usuario();
		PessoaDAO pessoaDAO = new PessoaDAO();

		// buscar no banco se o token existe e a qual usuario ele esta atribuido
		String searchQuery = "select * from usuario where token='" + token + "'";

		try {
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchQuery);
			boolean cadastrado = rs.next();

			if (cadastrado) {
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setPessoa(pessoaDAO.getPessoaById(rs.getInt("pessoa_id")));
			} else {
				usuario = null;
			}
		}

		catch (Exception ex) {
			System.out.println(ex.getMessage());
			usuario = null;
		}

		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return usuario;
	}

	// cadastrar usuario
	public String cadastrar(Usuario usuario) {
		Connection conn = null;
		String retorno = null;
		PessoaDAO pessoaDAO = new PessoaDAO();

		try {
			// cadastra pessoa
			retorno = pessoaDAO.cadastrar(usuario.getPessoa());
			
			// se pessoa cadastrada com sucesso, cadastrar usuario
			if (retorno.equals("1")) {
				
				// pegar pessoa cadastrada para pegar o id dela
				usuario.setPessoa(pessoaDAO.getPessoaByCpf(usuario.getPessoa().getCpf()));

				// iniciar conexão com o banco e instanciar variaveis de manutenção
				conn = getConnection();
				
				// cria um preparedStatement
				String sql = "insert into usuario (email, senha, pessoa_id) values (?,?,?)";
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

				// atribuir valores as variaveis ?
				pstmt.setString(1, usuario.getEmail());
				pstmt.setString(2, usuario.getSenha());
				pstmt.setInt(3, usuario.getPessoa().getId());

				// executa
				pstmt.execute();
				pstmt.close();
			}
			
			
		} catch (Exception ex) {
			// retorna falha no cadastro
			retorno = "Cadastro Falhou: " + ex;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return retorno;
	}
}
