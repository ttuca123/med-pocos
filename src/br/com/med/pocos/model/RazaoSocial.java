package br.com.med.pocos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RazaoSocial implements Serializable {

	
	
	public RazaoSocial() {
		super();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -3442444175146410567L;

	@Column(name = "razao_social")
	private String razaoSocial;
	
	@Column(name = "cpf", length=11, nullable=true )
	private String cpf;
	
	@Column(name = "cnpj", length = 14, nullable=true)
	private String cnpj;

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	
	
	
}
