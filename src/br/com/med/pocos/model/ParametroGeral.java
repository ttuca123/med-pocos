package br.com.med.pocos.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Entity
@Table(name = "parametro_geral")
public class ParametroGeral implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_parametro_geral", columnDefinition = "serial not null")
	private Long seqParametroGeral;

	@Column(name = "chave", nullable = false, length = 100)
	private String chave;
	
	@Column(name = "valor", nullable = false, length = 256)
	private String valor;

	public Long getSeqParametroGeral() {
		return seqParametroGeral;
	}

	public void setSeqParametroGeral(Long seqParametroGeral) {
		this.seqParametroGeral = seqParametroGeral;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chave == null) ? 0 : chave.hashCode());
		result = prime * result + ((seqParametroGeral == null) ? 0 : seqParametroGeral.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParametroGeral other = (ParametroGeral) obj;
		if (chave == null) {
			if (other.chave != null)
				return false;
		} else if (!chave.equals(other.chave))
			return false;
		if (seqParametroGeral == null) {
			if (other.seqParametroGeral != null)
				return false;
		} else if (!seqParametroGeral.equals(other.seqParametroGeral))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	
}
