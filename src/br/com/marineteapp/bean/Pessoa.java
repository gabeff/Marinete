package br.com.marineteapp.bean;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Pessoa {

	private Integer id;
	private String nome;
	private String cpf;
	private Date nascimento;
	private String sexo;
	private Integer idade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public Integer getIdade() {
		return idade;
	}
	
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

}
