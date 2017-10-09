package br.com.med.pocos.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 * Entity implementation class for Entity: Empreendimento
 *
 */
@Entity

public class Empreendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	public Empreendimento() {
		super();
	}

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "hibernate_sequence")
	@SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence")
	@Column(name = "seq_empreendimento", columnDefinition = "serial not null")
	private Long seqEmpreendimento;

	@Column(name = "descricao")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "empreendimento_proprietario", nullable = false)
	private List<Proprietario> proprietarios;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "empreendimento_poco")
	private List<Poco> lstPocos;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	private boolean isAtivo;

	public Long getSeqEmpreendimento() {
		return seqEmpreendimento;
	}

	public void setSeqEmpreendimento(Long seqEmpreendimento) {
		this.seqEmpreendimento = seqEmpreendimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Proprietario> getProprietarios() {
		return proprietarios;
	}

	public void setProprietarios(List<Proprietario> proprietarios) {
		this.proprietarios = proprietarios;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public List<Poco> getLstPocos() {
		return lstPocos;
	}

	public void setLstPocos(List<Poco> lstPocos) {
		this.lstPocos = lstPocos;
	}

}
