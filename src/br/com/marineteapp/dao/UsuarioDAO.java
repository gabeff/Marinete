package br.com.marineteapp.dao;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

import java.sql.PreparedStatement;

import br.com.marineteapp.bean.Usuario;

public class UsuarioDAO extends ManagerDAO {

	// Logar Usuario
	public Usuario logar(Usuario u) {
		Init();
		Usuario usuario = new Usuario();
		PessoaDAO pessoaDAO = new PessoaDAO();
		
		// Buscar Usuario no banco por seu nome e senha
		String searchQuery = "select * from usuario where email='" + u.getEmail() + "' AND senha='" + u.getSenha() + "'";

		try {

			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
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
			String sql = "update usuario set token = ? where email = ?";
			pstmt = (PreparedStatement) currentCon.prepareStatement(sql);

			// gerar token
			token = gerarToken();
			
			// atribuir valores as variaveis ?
			pstmt.setString(1, token);
			pstmt.setString(2, usuario.getEmail());

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
		PessoaDAO pessoaDAO = new PessoaDAO();

		// buscar no banco se o token existe e a qual usuario ele esta atribuido
		String searchQuery = "select * from usuario where token='" + token + "'";

		try {

			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
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
			Close();
		}

		return usuario;
	}

	// cadastrar usuario
	public String cadastrar(Usuario usuario) {
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
				Init();
				
				// cria um preparedStatement
				String sql = "insert into usuario (email, senha, pessoa_id) values (?,?,?)";
				pstmt = (PreparedStatement) currentCon.prepareStatement(sql);

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
			Close();
		}

		return retorno;
	}
}
