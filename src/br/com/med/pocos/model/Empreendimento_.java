package br.com.med.pocos.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-02T09:31:47.239-0300")
@StaticMetamodel(Empreendimento.class)
public class Empreendimento_ {
	public static volatile SingularAttribute<Empreendimento, Long> seqEmpreendimento;
	public static volatile SingularAttribute<Empreendimento, String> descricao;
	public static volatile SingularAttribute<Empreendimento, Responsavel> responsavel;
	public static volatile ListAttribute<Empreendimento, Poco> lstPocos;
	public static volatile SingularAttribute<Empreendimento, String> cep;
	public static volatile SingularAttribute<Empreendimento, String> complemento;
	public static volatile SingularAttribute<Empreendimento, String> nomeFantasia;
	public static volatile SingularAttribute<Empreendimento, String> cnpj;
}