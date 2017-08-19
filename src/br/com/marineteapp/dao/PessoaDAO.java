package br.com.marineteapp.dao;

import java.sql.PreparedStatement;

import br.com.marineteapp.bean.Pessoa;

public class PessoaDAO extends ManagerDAO {

	// cadastrar usuario
	public String cadastrar(Pessoa pessoa) {
		Init();
		String retorno = null;

		try {
			// cria um preparedStatement
			String sql = "insert into pessoa (nome, cpf, nascimento, sexo) values (?,?,?,?)";
			pstmt = (PreparedStatement) currentCon.prepareStatement(sql);

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
			Close();
		}

		return retorno;
	}
	
	public Pessoa getPessoaById(int id) {
		Init();
		Pessoa pessoa = new Pessoa();

		String searchQuery = "select * from pessoa where id=" + id ;

		try {

			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
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
			Close();
		}

		return pessoa;
	}

	public Pessoa getPessoaByCpf(String cpf) {
		Init();
		Pessoa pessoa = new Pessoa();

		String searchQuery = "select * from pessoa where cpf='" + cpf + "'";

		try {

			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
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
			Close();
		}

		return pessoa;
	}

}
