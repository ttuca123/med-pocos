package br.com.med.pocos.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.med.pocos.enu.EnumTipoHidrometro;

@NamedQueries(value = { @NamedQuery(name = "Hidrometro.buscaAllHidrometros", query = "select h from Hidrometro h"),
		@NamedQuery(name = "Hidrometro.buscaHidrometrosAtivos", query = "select h from Hidrometro h where h.isAtivo = true order by h.registro asc"),
		@NamedQuery(name = "Hidrometro.buscaHidrometrosDuplicados", query = "select h from Hidrometro h where h.registro like :registro"),
		@NamedQuery(name = "Hidrometro.buscaHidrometrosByEmpreendimento", query = "select h from Hidrometro h where h.empreendimento.seqEmpreendimento = :seqEmpreendimento order by h.registro asc"),
		@NamedQuery(name = "Hidrometro.buscaHidrometrosSemEmpreendimento", query = "select h from Hidrometro h where h.empreendimento.seqEmpreendimento is null	 order by h.registro asc") })
@Cacheable
@Entity
@Table(name = "hidrometro")

public class Hidrometro implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_hidrometro", columnDefinition = "serial not null")
	private Long seqHidrometro;

	@Enumerated
	@Column(name = "tipo_hidrometro")
	private EnumTipoHidrometro tipoHidrometro;

	@Column(name = "registro", length = 10, unique = true)
	private String registro;

	@Column(name = "lacre", length = 12)
	private String lacre;

	@Column(name = "vazao_maxima")
	private Double vazaoMaxima;

	@Column(name = "is_ativo", nullable = false)
	private boolean isAtivo;

	@ManyToOne(optional = true, cascade = CascadeType.MERGE)
	@JoinColumn(name = "empreendimento_hidrometro")
	private Empreendimento empreendimento;

	public Long getSeqHidrometro() {
		return seqHidrometro;
	}

	public void setSeqHidrometro(Long seqHidrometro) {
		this.seqHidrometro = seqHidrometro;
	}

	public EnumTipoHidrometro getTipoHidrometro() {
		return tipoHidrometro;
	}

	public void setTipoHidrometro(EnumTipoHidrometro tipoHidrometro) {
		this.tipoHidrometro = tipoHidrometro;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getLacre() {
		return lacre;
	}

	public void setLacre(String lacre) {
		this.lacre = lacre;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	public Double getVazaoMaxima() {
		return vazaoMaxima;
	}

	public void setVazaoMaxima(Double vazaoMaxima) {
		this.vazaoMaxima = vazaoMaxima;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empreendimento == null) ? 0 : empreendimento.hashCode());
		result = prime * result + ((seqHidrometro == null) ? 0 : seqHidrometro.hashCode());
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
		Hidrometro other = (Hidrometro) obj;
		if (empreendimento == null) {
			if (other.empreendimento != null)
				return false;
		} else if (!empreendimento.equals(other.empreendimento))
			return false;
		if (seqHidrometro == null) {
			if (other.seqHidrometro != null)
				return false;
		} else if (!seqHidrometro.equals(other.seqHidrometro))
			return false;
		return true;
	}

}
