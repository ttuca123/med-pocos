package br.com.med.pocos.model;

import br.com.med.pocos.enu.EnumTipoHidrometro;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-08T07:55:41.883-0300")
@StaticMetamodel(Hidrometro.class)
public class Hidrometro_ {
	public static volatile SingularAttribute<Hidrometro, Long> seqPoco;
	public static volatile SingularAttribute<Hidrometro, EnumTipoHidrometro> enuTipoHidrometro;
	public static volatile SingularAttribute<Hidrometro, String> lacre;
	public static volatile SingularAttribute<Hidrometro, Long> latitude;
	public static volatile SingularAttribute<Hidrometro, Long> longitude;
	public static volatile SingularAttribute<Hidrometro, Boolean> isAtivo;
	public static volatile SingularAttribute<Hidrometro, String> observacao;
	public static volatile SingularAttribute<Hidrometro, Empreendimento> empreendimento;
}
