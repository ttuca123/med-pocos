package br.com.med.pocos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="poco")
public class Poco { 

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "seq_poco", columnDefinition = "serial not null")
	private Long seqPoco;
	
	@Column(name="latitude")
	private Long latitude;
	
	@Column(name="longitude")
	private Long longitude;
	
	@Column(name="is_ativo")
	private boolean isAtivo;

	public Long getSeqPoco() {
		return seqPoco;
	}

	public void setSeqPoco(Long seqPoco) {
		this.seqPoco = seqPoco;
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
}
