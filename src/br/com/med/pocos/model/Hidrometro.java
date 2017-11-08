package br.com.med.pocos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.med.pocos.enu.EnumTipoHidrometro;

@Entity
@Table(name="hidrometro")
public class Hidrometro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "seq_hidrometro", columnDefinition = "serial not null")
	private Long seqPoco;
	
	@Enumerated
	@Column(name="tipo_hidrometro")
	private EnumTipoHidrometro enuTipoHidrometro;
	
	@Column(name="lacre")
	private String lacre;
	
	@Column(name="latitude")
	private Long latitude;
	
	@Column(name="longitude")
	private Long longitude;
	
	@Column(name="is_ativo")
	private boolean isAtivo;
	
	@Column(name="observacao")
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name="empreendimento_poco")
	private Empreendimento empreendimento;	
	
	
}
