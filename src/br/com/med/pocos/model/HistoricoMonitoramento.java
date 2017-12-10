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
@Table(name = "historico_monitoramento")

public class HistoricoMonitoramento implements Serializable {

	private static final long serialVersionUID = 6310925639705761062L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "seq_historico_monitoramento", columnDefinition = "serial not null")
	private Long seqHistoricoMonitoramento;

	@Temporal(TemporalType.DATE)
	@Column(name = "periodo_inicio")
	private Date periodoInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "periodo_fim")
	private Date periodoFim;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Column(name = "nr_portaria")
	private int nrPortaria;

	@Column(name = "nr_poco")
	private String nrPoco;

	@Column(name = "media_mensal")
	private Double mediaMensal;

	@Column(name = "mes")
	private String mes;

	@Column(name = "ano")
	private String ano;

	@Column(name = "total_dias_mes")
	private int totalDiasMes;

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

	public String getNrPoco() {
		return nrPoco;
	}

	public void setNrPoco(String nrPoco) {
		this.nrPoco = nrPoco;
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
		HistoricoMonitoramento other = (HistoricoMonitoramento) obj;
		if (seqHistoricoMonitoramento == null) {
			if (other.seqHistoricoMonitoramento != null)
				return false;
		} else if (!seqHistoricoMonitoramento.equals(other.seqHistoricoMonitoramento))
			return false;
		return true;
	}

}
