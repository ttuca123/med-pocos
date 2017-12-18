package br.com.med.pocos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "poco")
public class Poco implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_poco", columnDefinition = "serial not null")
	private Long seqPoco;

	@Column(name = "nr_poco", length = 10, nullable = false)
	private String nrPoco;

	@OneToOne
	@JoinColumn(name = "hidrometro_poco", nullable = true)
	private Hidrometro hidrometro;
	
	@OneToOne
	@JoinColumn(name = "empreendimento_poco")
	private Empreendimento empreendimento;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "compl_latitude", length = 1)
	private Character compLatitude;

	@Column(name = "compl_longitude", length = 1)
	private Character compLongitude;

	@Column(name = "is_ativo", nullable = false)
	private boolean isAtivo;

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Character getCompLatitude() {
		return compLatitude;
	}

	public void setCompLatitude(Character compLatitude) {
		this.compLatitude = compLatitude;
	}

	public Character getCompLongitude() {
		return compLongitude;
	}

	public void setCompLongitude(Character compLongitude) {
		this.compLongitude = compLongitude;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public Long getSeqPoco() {
		return seqPoco;
	}

	public void setSeqPoco(Long seqPoco) {
		this.seqPoco = seqPoco;
	}
	public String getNrPoco() {
		return nrPoco;
	}

	public void setNrPoco(String nrPoco) {
		this.nrPoco = nrPoco;
	}

	public Hidrometro getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(Hidrometro hidrometro) {
		this.hidrometro = hidrometro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hidrometro == null) ? 0 : hidrometro.hashCode());
		result = prime * result + ((seqPoco == null) ? 0 : seqPoco.hashCode());
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
		Poco other = (Poco) obj;
		if (hidrometro == null) {
			if (other.hidrometro != null)
				return false;
		} else if (!hidrometro.equals(other.hidrometro))
			return false;
		if (seqPoco == null) {
			if (other.seqPoco != null)
				return false;
		} else if (!seqPoco.equals(other.seqPoco))
			return false;
		return true;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	
	

	
	

}
