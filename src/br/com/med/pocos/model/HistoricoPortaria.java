package br.com.med.pocos.model;

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

@Entity
@Table(name = "historico_portaria")

public class HistoricoPortaria implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_historico_portaria", columnDefinition = "serial not null")
	private Long seqHistoricoMonitoramento;

	@Temporal(TemporalType.DATE)
	@Column(name = "periodo_inicio")
	private Date periodoInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "periodo_fim")
	private Date periodoFim;
	
	@Column(name = "responsavel")
	private String responsavel;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Column(name = "nr_portaria")
	private int nrPortaria;

	public Long getSeqHistoricoMonitoramento() {
		return seqHistoricoMonitoramento;
	}

	public void setSeqHistoricoMonitoramento(Long seqHistoricoMonitoramento) {
		this.seqHistoricoMonitoramento = seqHistoricoMonitoramento;
	}

	public Date getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(Date periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public Date getPeriodoFim() {
		return periodoFim;
	}

	public void setPeriodoFim(Date periodoFim) {
		this.periodoFim = periodoFim;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public int getNrPortaria() {
		return nrPortaria;
	}

	public void setNrPortaria(int nrPortaria) {
		this.nrPortaria = nrPortaria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result + nrPortaria;
		result = prime * result + ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((seqHistoricoMonitoramento == null) ? 0 : seqHistoricoMonitoramento.hashCode());
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
		HistoricoPortaria other = (HistoricoPortaria) obj;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (nrPortaria != other.nrPortaria)
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (seqHistoricoMonitoramento == null) {
			if (other.seqHistoricoMonitoramento != null)
				return false;
		} else if (!seqHistoricoMonitoramento.equals(other.seqHistoricoMonitoramento))
			return false;
		return true;
	}

	

}
