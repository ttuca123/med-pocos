package br.com.med.pocos.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Empresa {

	
	@Column(name = "razao_social", nullable = false, length = 80)
	private String razaoSocial;
}
