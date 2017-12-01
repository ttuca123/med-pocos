package br.com.med.pocos.model;

import java.io.Serializable;

public class HidrometroId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4621054955817513040L;

	public HidrometroId() {
		super();
	}

	private Long seqHidrometro;

	private String registro;

	private String lacre;

	public String getLacre() {
		return lacre;
	}

	public void setLacre(String lacre) {
		this.lacre = lacre;
	}

	public Long getSeqHidrometro() {
		return seqHidrometro;
	}

	public void setSeqHidrometro(Long seqHidrometro) {
		this.seqHidrometro = seqHidrometro;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lacre == null) ? 0 : lacre.hashCode());
		result = prime * result + ((registro == null) ? 0 : registro.hashCode());
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
		HidrometroId other = (HidrometroId) obj;
		if (lacre == null) {
			if (other.lacre != null)
				return false;
		} else if (!lacre.equals(other.lacre))
			return false;
		if (registro == null) {
			if (other.registro != null)
				return false;
		} else if (!registro.equals(other.registro))
			return false;
		if (seqHidrometro == null) {
			if (other.seqHidrometro != null)
				return false;
		} else if (!seqHidrometro.equals(other.seqHidrometro))
			return false;
		return true;
	}

}
