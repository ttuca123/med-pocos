package br.com.med.pocos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "portaria")
public class Portaria implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_portaria", columnDefinition = "serial not null")
	private Long seqPortaria;

	@Column(name = "nr_portaria", nullable = false)
	private int nrPortaria;
	
	@Column(name = "descricao", length=500)
	private String descricao;

	@OneToOne
	@JoinColumn(name = "empreendimento_portaria", nullable = false)
	private Empreendimento empreendimento;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_publicacao")	
	private Date dtPublicacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_encerramento")	
	private Date dtEncerramento;
	
	public Long getSeqPortaria() {
		return seqPortaria;
	}

	public void setSeqPortaria(Long seqPortaria) {
		this.seqPortaria = seqPortaria;
	}

	public int getNrPortaria() {
		return nrPortaria;
	}

	public void setNrPortaria(int nrPortaria) {
		this.nrPortaria = nrPortaria;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	public Date getDtPublicacao() {
		return dtPublicacao;
	}

	public void setDtPublicacao(Date dtPublicacao) {
		this.dtPublicacao = dtPublicacao;
	}

	public Date getDtEncerramento() {
		return dtEncerramento;
	}

	public void setDtEncerramento(Date dtEncerramento) {
		this.dtEncerramento = dtEncerramento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empreendimento == null) ? 0 : empreendimento.hashCode());
		result = prime * result + nrPortaria;
		result = prime * result + ((seqPortaria == null) ? 0 : seqPortaria.hashCode());
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
		Portaria other = (Portaria) obj;
		if (empreendimento == null) {
			if (other.empreendimento != null)
				return false;
		} else if (!empreendimento.equals(other.empreendimento))
			return false;
		if (nrPortaria != other.nrPortaria)
			return false;
		if (seqPortaria == null) {
			if (other.seqPortaria != null)
				return false;
		} else if (!seqPortaria.equals(other.seqPortaria))
			return false;
		return true;
	}

	
	

	
	

}
