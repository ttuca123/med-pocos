package br.com.med.pocos.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "outorga")
public class Outorga implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_outorga", columnDefinition = "serial not null")
	private Long seqOutorga;
	

	@OneToOne
	@JoinColumn(name = "poco_outorga", nullable = false)
	private Poco poco;
	
	@OneToOne
	@JoinColumn(name = "portaria_outorga", nullable = false)
	private Portaria portaria;
	
	
	@Column(name="vazao_hora", precision=10, scale=2, nullable=false)
	private Double vazaoHora;
	
	@Column(name="qtd_hora_dia", precision=10, scale=2, nullable=false)
	private Double qtdHoraDia;

	public Long getSeqOutorga() {
		return seqOutorga;
	}

	public void setSeqOutorga(Long seqOutorga) {
		this.seqOutorga = seqOutorga;
	}

	public Poco getPoco() {
		return poco;
	}

	public void setPoco(Poco poco) {
		this.poco = poco;
	}

	public Portaria getPortaria() {
		return portaria;
	}

	public void setPortaria(Portaria portaria) {
		this.portaria = portaria;
	}

	public Double getVazaoHora() {
		return vazaoHora;
	}

	public void setVazaoHora(Double vazaoHora) {
		this.vazaoHora = vazaoHora;
	}

	public Double getQtdHoraDia() {
		return qtdHoraDia;
	}

	public void setQtdHoraDia(Double qtdHoraDia) {
		this.qtdHoraDia = qtdHoraDia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((poco == null) ? 0 : poco.hashCode());
		result = prime * result + ((portaria == null) ? 0 : portaria.hashCode());
		result = prime * result + ((seqOutorga == null) ? 0 : seqOutorga.hashCode());
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
		Outorga other = (Outorga) obj;
		if (poco == null) {
			if (other.poco != null)
				return false;
		} else if (!poco.equals(other.poco))
			return false;
		if (portaria == null) {
			if (other.portaria != null)
				return false;
		} else if (!portaria.equals(other.portaria))
			return false;
		if (seqOutorga == null) {
			if (other.seqOutorga != null)
				return false;
		} else if (!seqOutorga.equals(other.seqOutorga))
			return false;
		return true;
	}
	
	

}
