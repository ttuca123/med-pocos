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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: Empreendimento
 *
 */
@Entity
@Table(name="empreendimento")
public class Empreendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	public Empreendimento() {
		super();
	}

	@Id
	 //@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "seq_empreendimento", columnDefinition = "serial not null")
	private Long seqEmpreendimento;

	@Column(name = "descricao")
	private String descricao;

	@OneToOne	
	private Responsavel responsavel;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "empreendimento_poco")
	private List<Poco> lstPocos;	
	
	@Transient
	private String logradouro;

	@Transient
	private String bairro;

	@Transient
	private String municipio;

	@Transient
	private String estado;
	
	@Column(name = "cep", length=8)
	private String cep;

	@Column(name = "complemento", length=80)
	private String complemento;

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

	

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Poco> getLstPocos() {
		return lstPocos;
	}

	public void setLstPocos(List<Poco> lstPocos) {
		this.lstPocos = lstPocos;
	}

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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	

	

}
