package br.com.marineteapp.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3001175389728651713L;
	private String email;
	private String senha;
	private Pessoa pessoa;

	public Usuario() {
	}

	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
