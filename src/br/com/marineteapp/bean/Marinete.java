package br.com.marineteapp.bean;

import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.LocalDate;
import org.joda.time.Years;

@XmlRootElement
public class Marinete {

	private Integer id;
	private String nome;
	private String nascimento;
	private String cidade;
	private String estado;
	private Double avaliacao;
	private Double avalPercent;
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

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Integer getIdade() {
		setIdade();
		return idade;
	}
	
	public void setIdade() {
		LocalDate nascimento = new LocalDate (this.nascimento);
		LocalDate now = new LocalDate();
		Years idade = Years.yearsBetween(nascimento, now);
		this.idade = idade.getYears();
	}

	public Double getAvalPercent() {
		setAvalPercent(this.avaliacao);
		return avalPercent;
	}

	public void setAvalPercent(Double avalPercent) {
		this.avalPercent = (avalPercent*100)/5;
	}
	
	
}
