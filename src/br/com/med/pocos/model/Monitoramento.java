package br.com.med.pocos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "monitoramento")
public class Monitoramento implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_monitoramento", columnDefinition = "serial not null")
	private Long seqMonitoramento;	
	
	@ManyToOne
	@JoinColumn(name = "outorga_monitoramento", nullable = false)
	private Outorga outorga;
	
	@Temporal(TemporalType.DATE)
	@Column(name="periodo_inicio", nullable=false)
	private Date periodoInicio;
	
	@Temporal(TemporalType.DATE)
	@Column(name="periodo_fim", nullable=false)
	private Date periodoFim;
	
	@Column(name="leitura_atual", nullable=false)	
	private Long leituraAtual;
	
	@Column(name="leitura_anterior", nullable=false)
	private Long leituraAnterior;
	
	@Column(name="media_mensal")
	private Double mediaMensal;

	@Transient
	private String mes;
	
	@Transient
	private String ano;
	
	@Transient
	private int totalDiasMes;

	public Long getSeqMonitoramento() {
		return seqMonitoramento;
	}

	public void setSeqMonitoramento(Long seqMonitoramento) {
		this.seqMonitoramento = seqMonitoramento;
	}

	public Outorga getOutorga() {
		return outorga;
	}

	public void setOutorga(Outorga outorga) {
		this.outorga = outorga;
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

	public Long getLeituraAtual() {
		return leituraAtual;
	}

	public void setLeituraAtual(Long leituraAtual) {
		this.leituraAtual = leituraAtual;
	}

	public Long getLeituraAnterior() {
		return leituraAnterior;
	}

	public void setLeituraAnterior(Long leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}

	public Double getMediaMensal() {
		return mediaMensal;
	}

	public void setMediaMensal(Double mediaMensal) {
		this.mediaMensal = mediaMensal;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public int getTotalDiasMes() {
		return totalDiasMes;
	}

	public void setTotalDiasMes(int totalDiasMes) {
		this.totalDiasMes = totalDiasMes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((outorga == null) ? 0 : outorga.hashCode());
		result = prime * result + ((seqMonitoramento == null) ? 0 : seqMonitoramento.hashCode());
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
		Monitoramento other = (Monitoramento) obj;
		if (outorga == null) {
			if (other.outorga != null)
				return false;
		} else if (!outorga.equals(other.outorga))
			return false;
		if (seqMonitoramento == null) {
			if (other.seqMonitoramento != null)
				return false;
		} else if (!seqMonitoramento.equals(other.seqMonitoramento))
			return false;
		return true;
	}
	
	



	
	
	

}
