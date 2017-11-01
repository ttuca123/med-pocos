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

/**
 * Entity implementation class for Entity: Usuario
 *
 */
@NamedQueries(value = {
		@NamedQuery(name = "Usuario.buscaUsuarios", query = "select u from Usuario u where u.isAtivo = true "),

		@NamedQuery(name = "Usuario.verificaUsuario", query = "select u from Usuario u where u.email = :email AND u.senha = :senha AND u.isAtivo = true") })
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "seq_usuario", columnDefinition = "serial not null")
	private Long seqUsuario;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "sobrenome", nullable = false)
	private String sobrenome;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "telefone", nullable = true)
	private String telefone;

	@Column(name = "data_cadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(name = "is_ativo", nullable = false)
	private Boolean isAtivo;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}

	public Usuario(Long id) {
		this.setSeqUsuario(id);
	}

	public Long getSeqUsuario() {
		return this.seqUsuario;
	}

	public void setSeqUsuario(Long seqUsuario) {
		this.seqUsuario = seqUsuario;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
