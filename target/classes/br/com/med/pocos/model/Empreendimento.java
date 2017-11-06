package br.com.med.pocos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.med.pocos.enu.EnumTipoEmpreendimento;

/**
 * Entity implementation class for Entity: Empreendimento
 *
 */

@NamedQueries( value = {
		@NamedQuery(name="Empreendimento.buscaAllEmpreendimentos", 
		query="select e from Empreendimento e ORDER BY e.nomeFantasia asc ")	
		
		}
	)
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

	@Column(name = "nome_fantasia", nullable=false, unique=true, length=80)
	private String nomeFantasia;
	
	@Column(name = "descricao", length=256)
	private String descricao;
	
	@Column(name = "cnpj", nullable=false, length=14, unique=true)
	private String cnpj;
	
	@Column(name = "data_cadastro", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name = "data_encerramento", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEncerramento;

	@Enumerated
	@Column(name="tipo_empreendimento")
	private EnumTipoEmpreendimento tipoEmpreendimento;
	
	
	@OneToOne	
	private Responsavel responsavel;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="empreendimento")	
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
	
	@Transient
	private boolean isAtivo;
	
	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

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
	


	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
	
		this.cnpj = cnpj;
	}
	
	public EnumTipoEmpreendimento getTipoEmpreendimento() {
		return tipoEmpreendimento;
	}

	public void setTipoEmpreendimento(EnumTipoEmpreendimento tipoEmpreendimento) {
		this.tipoEmpreendimento = tipoEmpreendimento;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result + ((seqEmpreendimento == null) ? 0 : seqEmpreendimento.hashCode());
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
		Empreendimento other = (Empreendimento) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (seqEmpreendimento == null) {
			if (other.seqEmpreendimento != null)
				return false;
		} else if (!seqEmpreendimento.equals(other.seqEmpreendimento))
			return false;
		return true;
	}	

}
