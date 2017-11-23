package br.com.med.pocos.model;

import br.com.med.pocos.enu.EnumTipoHidrometro;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-23T08:05:03.501-0300")
@StaticMetamodel(Hidrometro.class)
public class Hidrometro_ {
	public static volatile SingularAttribute<Hidrometro, Long> seqHidrometro;
	public static volatile SingularAttribute<Hidrometro, EnumTipoHidrometro> tipoHidrometro;
	public static volatile SingularAttribute<Hidrometro, String> registro;
	public static volatile SingularAttribute<Hidrometro, String> lacre;
	public static volatile SingularAttribute<Hidrometro, String> latitude;
	public static volatile SingularAttribute<Hidrometro, String> longitude;
	public static volatile SingularAttribute<Hidrometro, Double> vazaoMaxima;
	public static volatile SingularAttribute<Hidrometro, Boolean> isAtivo;
	public static volatile SingularAttribute<Hidrometro, Empreendimento> empreendimento;
}
