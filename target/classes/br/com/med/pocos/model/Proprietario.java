package br.com.med.pocos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Proprietário
 *
 */
@Entity
@Table(name = "proprietario")
public class Proprietario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "hibernate_sequence")
	@SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence")
	@Column(name = "seq_proprietario", columnDefinition = "serial not null")
	private Long seqProprietario;

	@Column(name = "representante")
	private String representante;

	@Column(name = "representante")
	private String cpf;

	@Column(name = "email")
	private String email;

	@Column(name = "is_ativo")
	private boolean isAtivo;
	
	@OneToMany(mappedBy="empreendimento_proprietario", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Empreendimento> lstEmpreendimentos;

	@Column(name = "data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	private static final long serialVersionUID = 1L;

	public Proprietario() {
		super();
	}

	public Long getSeqProprietario() {
		return this.seqProprietario;
	}

	public void setSeqProprietario(Long seqProprietario) {
		this.seqProprietario = seqProprietario;
	}

	public String getRepresentante() {
		return this.representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsAtivo() {
		return this.isAtivo;
	}

	public void setIsAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public List<Empreendimento> getLstEmpreendimentos() {
		return lstEmpreendimentos;
	}

	public void setLstEmpreendimentos(List<Empreendimento> lstEmpreendimentos) {
		this.lstEmpreendimentos = lstEmpreendimentos;
	}

}
