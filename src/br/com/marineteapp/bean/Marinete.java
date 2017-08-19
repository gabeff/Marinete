package br.com.marineteapp.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Marinete extends Pessoa {

	private Double avaliacao;
	private Double avalPercent;

	public Double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Double getAvalPercent() {
		return avalPercent;
	}

	public void setAvalPercent(Double avalPercent) {
		this.avalPercent = avalPercent;
	}
	
}
