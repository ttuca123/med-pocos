package br.com.med.pocos.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@NamedQueries(value = {
		@NamedQuery(name = "Regra.buscaRegrasByUser", query = "select r from Regra r inner join r.usuario u inner join r.permissao p"
				+ " where r.usuario.seqUsuario = :seqUsuario ")
		 })
@Entity
@Table(name = "regra")
public class Regra implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_regra", columnDefinition = "serial not null")
	private Long seqRegra;	

	@OneToMany(fetch = FetchType.EAGER)
	private List<Usuario> usuario;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Permissao> permissao;

	public Long getSeqRegra() {
		return seqRegra;
	}

	public void setSeqRegra(Long seqRegra) {
		this.seqRegra = seqRegra;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public List<Permissao> getPermissao() {
		return permissao;
	}

	public void setPermissao(List<Permissao> permissao) {
		this.permissao = permissao;
	}

	

}
