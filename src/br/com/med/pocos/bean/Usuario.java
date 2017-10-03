package br.com.med.pocos.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade responsável pela manutenção dos usuários do sistema
 * @author Artur
 *
 */

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6364430259474459733L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long seqUsuario;

	@Column(name = "nome")
	private String nome;

	@Column(name = "sobrenome")
	private String sobrenome;

	@Column(name = "email")
	private String email;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "dt_cadastro")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dtCadastro;

	@Column(name = "has_ativo")
	private Boolean isAtivo;

	
	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Long getSeqUsuario() {
		return seqUsuario;
	}

}
