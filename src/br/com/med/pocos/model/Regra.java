package br.com.med.pocos.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries(value = {
		@NamedQuery(name = "Regra.buscaRegrasByUser", query = "select r from Regra r inner join r.usuario u inner join r.permissao p"
				+ " where u.seqUsuario = :seqUsuario "),
		@NamedQuery(name = "Regra.buscaRegraDuplicada", query = "select r from Regra r inner join r.usuario u inner join r.permissao p "
				+ " where u.seqUsuario = :seqUsuario and p.seqPermissao = :seqPermissao")

})
@Entity
@Table(name = "regra")
public class Regra implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_regra", columnDefinition = "serial not null")
	private Long seqRegra;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	private Usuario usuario;

	@OneToOne(fetch = FetchType.EAGER)
	private Permissao permissao;

	public Long getSeqRegra() {
		return seqRegra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public void setSeqRegra(Long seqRegra) {
		this.seqRegra = seqRegra;
	}

}
