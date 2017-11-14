package br.com.med.pocos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.med.pocos.enu.EnumTipoHidrometro;


@NamedQueries(value = {
		@NamedQuery(name = "Hidrometro.buscaAllHidrometros", query = "select h from Hidrometro h"),		
})
@Entity
@Table(name = "hidrometro")
@IdClass(value = HidrometroId.class)
public class Hidrometro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_hidrometro", columnDefinition = "serial not null")
	private Long seqHidrometro;

	@Enumerated
	@Column(name = "tipo_hidrometro")
	private EnumTipoHidrometro enuTipoHidrometro;

	@Id
	@Column(name = "numero_registro", length = 10, unique = true)
	private String numero_registro;

	@Id
	@Column(name = "lacre", length = 10)
	private String lacre;

	@Column(name = "latitude")
	private Long latitude;

	@Column(name = "longitude")
	private Long longitude;

	@Column(name = "is_ativo", nullable = false)
	private boolean isAtivo;

	@Column(name = "observacao", length = 256)
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "empreendimento_poco")
	private Empreendimento empreendimento;

	public Long getSeqHidrometro() {
		return seqHidrometro;
	}

	public void setSeqHidrometro(Long seqHidrometro) {
		this.seqHidrometro = seqHidrometro;
	}

	public EnumTipoHidrometro getEnuTipoHidrometro() {
		return enuTipoHidrometro;
	}

	public void setEnuTipoHidrometro(EnumTipoHidrometro enuTipoHidrometro) {
		this.enuTipoHidrometro = enuTipoHidrometro;
	}

	public String getNumero_registro() {
		return numero_registro;
	}

	public void setNumero_registro(String numero_registro) {
		this.numero_registro = numero_registro;
	}

	public String getLacre() {
		return lacre;
	}

	public void setLacre(String lacre) {
		this.lacre = lacre;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}
}
