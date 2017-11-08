package br.com.med.pocos.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RazaoSocial {

	
	@Column(name = "razao_social")
	private String razaoSocial;
	
	@Column(name = "cpf", length=11, nullable=false, unique = true )
	private String cpf;
	
	@Column(name = "cnpj", nullable = false, length = 14, unique = true)
	private String cnpj;

	public String getRazaoSocial() {
		return razaoSocial;
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

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	
}
