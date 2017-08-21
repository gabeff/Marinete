package br.com.marineteapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.marineteapp.bean.Pessoa;

public class PessoaDAO extends Utils {

	// cadastrar usuario
	public String cadastrar(Pessoa pessoa) {
		Connection conn = null;
		String retorno = null;

		try {
			conn = getConnection();
			
			// cria um preparedStatement
			String sql = "insert into pessoa (nome, cpf, nascimento, sexo) values (?,?,?,?)";
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);

			// atribuir valores as variaveis ?
			pstmt.setString(1, pessoa.getNome());
			pstmt.setString(2, pessoa.getCpf());
			pstmt.setDate(3, pessoa.getNascimento());
			pstmt.setString(4, pessoa.getSexo());

			// executa
			pstmt.execute();
			pstmt.close();

			// retorna sucesso
			retorno = "1";
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
	
	public Pessoa getPessoaById(int id) {
		Connection conn = null;
		Pessoa pessoa = new Pessoa();

		String searchQuery = "select * from pessoa where id=" + id ;

		try {
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchQuery);
			boolean cadastrado = rs.next();

			if (cadastrado) {
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNascimento(rs.getDate("nascimento"));
				pessoa.setSexo(rs.getString("sexo"));
			} else {
				pessoa = null;
			}
		}

		catch (Exception ex) {
			System.out.println(ex.getMessage());
			pessoa = null;
		}

		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pessoa;
	}

	public Pessoa getPessoaByCpf(String cpf) {
		Connection conn = null;
		Pessoa pessoa = new Pessoa();

		String searchQuery = "select * from pessoa where cpf='" + cpf + "'";

		try {
			
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(searchQuery);
			boolean cadastrado = rs.next();

			if (cadastrado) {
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNascimento(rs.getDate("nascimento"));
				pessoa.setSexo(rs.getString("sexo"));
			} else {
				pessoa = null;
			}
		}

		catch (Exception ex) {
			System.out.println(ex.getMessage());
			pessoa = null;
		}

		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pessoa;
	}

}
