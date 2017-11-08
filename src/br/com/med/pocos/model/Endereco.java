package br.com.med.pocos.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	@Column(name = "logradouro", length = 80)
	private String logradouro;

	@Column(name = "bairro", length = 60)
	private String bairro;

	@Column(name = "municipio", length = 50)
	private String municipio;

	@Column(name = "estado", length = 2)
	private String estado;

	@Column(name = "cep", length = 8)
	private String cep;

	@Column(name = "complemento", length = 80)
	private String complemento;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
