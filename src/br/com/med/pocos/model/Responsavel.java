package br.com.med.pocos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: Responsável
 *
 */
@NamedQueries( value = {
		@NamedQuery(name="Responsavel.buscaResponsaveis", 
		query="select r from Responsavel r ORDER BY r.nome asc ")	
		
		, @NamedQuery(name="Responsavel.buscaResponsaveisAtivos", 
		query="select r from Responsavel r where r.dataEncerramentoContrato IS NULL ORDER BY r.nome asc ")	
		
		,@NamedQuery(name="Responsavel.buscaResponsavelById", 
				query="select r from Responsavel r where r.seqResponsavel=:sequencial ")	
		
		}
	)
@Entity
@Table(name = "responsavel")
public class Responsavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Responsavel() {
		super();
	}	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "seq_responsavel", columnDefinition = "serial not null")
	private Long seqResponsavel;

	@Column(name = "nome", length=80, nullable=false)
	private String nome;

	@Column(name = "cpf", length=11, nullable=false, unique = true )
	private String cpf;

	@Column(name = "email", length=36, nullable = false, unique=true)
	private String email;
	
	@Column(name = "celular", length=12, nullable=false)
	private String celular;
	
	@Column(name = "fixo", length=12, nullable=true)
	private String fixo;	
	

	@Column(name = "data_cadastro", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(name = "data_encerramento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEncerramentoContrato;
	
	@Transient
	private boolean isAtivo;
	
	@Column(name = "is_proprietario", nullable=true)
	private boolean isProprietario;
	
	@OneToMany(mappedBy= "responsavel")
	private List<Empreendimento> empreendimentos;

	

	public List<Empreendimento> getEmpreendimentos() {
		return empreendimentos;
	}

	public void setEmpreendimentos(List<Empreendimento> empreendimentos) {
		this.empreendimentos = empreendimentos;
	}

	public boolean isProprietario() {
		
		
		
		return isProprietario;
	}

	public void setProprietario(boolean isProprietario) {
		this.isProprietario = isProprietario;
	}

	public Long getSeqResponsavel() {
		return seqResponsavel;
	}

	public void setSeqResponsavel(Long seqResponsavel) {
		this.seqResponsavel = seqResponsavel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFixo() {
		return fixo;
	}

	public void setFixo(String fixo) {
		this.fixo = fixo;
	}

	
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataEncerramentoContrato() {
		return dataEncerramentoContrato;
	}

	public void setDataEncerramentoContrato(Date dataEncerramentoContrato) {
		this.dataEncerramentoContrato = dataEncerramentoContrato;
	}

	public boolean isAtivo() {		
		
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}
	
	public boolean isOperar() {
		if(this.dataEncerramentoContrato==null) {
			return true;
		}else {
			return false;
			
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((seqResponsavel == null) ? 0 : seqResponsavel.hashCode());
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
		Responsavel other = (Responsavel) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (seqResponsavel == null) {
			if (other.seqResponsavel != null)
				return false;
		} else if (!seqResponsavel.equals(other.seqResponsavel))
			return false;
		return true;
	}

	
}
