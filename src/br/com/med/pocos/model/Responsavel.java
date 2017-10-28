package br.com.med.pocos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
		query="select r from Responsavel r where r.dataEncerramentoContrato IS NULL ")		
		,
	@NamedQuery(name="Responsavel.buscaResponsaveisComCriterios", 
		query="select r from Responsavel r where (r.nome like :nome OR"
				+ " r.cpf = :cpf OR r.email = :email) AND r.dataEncerramentoContrato IS NULL")		
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

	@Column(name = "cpf", length=11, nullable=false )
	private String cpf;

	@Column(name = "email", length=36, nullable = true)
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
		
		if(this.dataEncerramentoContrato==null) {
			isAtivo =true;
		}else {
			isAtivo =false;
		}
		
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	
}
