package br.com.med.pocos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.med.pocos.enu.EnumTipoEmpreendimento;

/**
 * Entity implementation class for Entity: Empreendimento
 *
 */

@NamedQueries(value = {
		@NamedQuery(name = "Empreendimento.buscaAllEmpreendimentos", query = "select e from Empreendimento e ORDER BY e.nomeFantasia asc "),
		//@NamedQuery(name = "Empreendimento.buscaAllHidrometrosByEmpreendimento", query = "select e from Empreendimento e inner join fetch e.lstHidrometros where e.seqEmpreendimento = :seqEmpreendimento")
})
@Entity
@Table(name = "empreendimento")
public class Empreendimento implements Serializable {

	private static final long serialVersionUID = 1L;

	public Empreendimento() {
		super();
	}

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_empreendimento", columnDefinition = "serial not null")
	private Long seqEmpreendimento;

	@Embedded	
	private RazaoSocial razaoSocial;

	@Column(name = "nome_fantasia", nullable = false, unique = true, length = 80)
	private String nomeFantasia;

	@Column(name = "descricao", length = 256)
	private String descricao;

	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;	

	@Column(name = "data_encerramento", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEncerramento;

	@Enumerated
	@Column(name = "tipo_empreendimento")
	private EnumTipoEmpreendimento tipoEmpreendimento;

	@ManyToOne(fetch = FetchType.EAGER)
	private Responsavel responsavel;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empreendimento")
	private List<Hidrometro> lstHidrometros;

	@Transient
	private boolean isAtivo;
	
	@Embedded
	private Endereco endereco;

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
		
		if(responsavel==null) {
			
			responsavel = new Responsavel();
		}
		
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
	public RazaoSocial getRazaoSocial() {
		
		if(razaoSocial==null) {
			
			razaoSocial = new RazaoSocial();
		}
		return razaoSocial;
	}

	public void setRazaoSocial(RazaoSocial razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public EnumTipoEmpreendimento getTipoEmpreendimento() {
		return tipoEmpreendimento;
	}

	public void setTipoEmpreendimento(EnumTipoEmpreendimento tipoEmpreendimento) {
		this.tipoEmpreendimento = tipoEmpreendimento;
	}

	public List<Hidrometro> getLstHidrometros() {
		
		if(lstHidrometros == null) {
			
			lstHidrometros = new ArrayList<Hidrometro>();
		}
		
		return lstHidrometros;
	}	
	
	
	
	
	public void setLstHidrometros(List<Hidrometro> lstHidrometros) {
		this.lstHidrometros = lstHidrometros;
	}
	
	public Endereco getEndereco() {
		
		if(endereco==null) {
			
			endereco = new Endereco();
		}
		
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isOperar() {
		if(this.dataEncerramento==null) {
			return true;
		}else {
			return false;
			
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
