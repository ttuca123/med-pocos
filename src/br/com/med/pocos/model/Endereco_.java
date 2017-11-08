package br.com.med.pocos.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-08T09:45:39.288-0300")
@StaticMetamodel(Endereco.class)
public class Endereco_ {
	public static volatile SingularAttribute<Endereco, String> logradouro;
	public static volatile SingularAttribute<Endereco, String> bairro;
	public static volatile SingularAttribute<Endereco, String> municipio;
	public static volatile SingularAttribute<Endereco, String> estado;
	public static volatile SingularAttribute<Endereco, String> cep;
	public static volatile SingularAttribute<Endereco, String> complemento;
}
