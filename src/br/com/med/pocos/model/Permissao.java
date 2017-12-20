package br.com.med.pocos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries(
		value = { @NamedQuery(name = "Permissao.findAll", query = "select p from Permissao p")
})
@Entity
@Table(name = "permissao")
public class Permissao implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_permissao", columnDefinition = "serial not null")
	private Long seqPermissao;

	@Column(name = "descricao", nullable = false, length = 50)
	private String descricao;

	public Long getSeqPermissao() {
		return seqPermissao;
	}

	public void setSeqPermissao(Long seqPermissao) {
		this.seqPermissao = seqPermissao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seqPermissao == null) ? 0 : seqPermissao.hashCode());
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
		Permissao other = (Permissao) obj;
		if (seqPermissao == null) {
			if (other.seqPermissao != null)
				return false;
		} else if (!seqPermissao.equals(other.seqPermissao))
			return false;
		return true;
	}

}
