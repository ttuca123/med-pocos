package br.com.med.pocos.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-31T16:40:23.113-0300")
@StaticMetamodel(Poco.class)
public class Poco_ {
	public static volatile SingularAttribute<Poco, Long> seqPoco;
	public static volatile SingularAttribute<Poco, Long> latitude;
	public static volatile SingularAttribute<Poco, Long> longitude;
	public static volatile SingularAttribute<Poco, Boolean> isAtivo;
	public static volatile SingularAttribute<Poco, Empreendimento> empreendimento;
}
