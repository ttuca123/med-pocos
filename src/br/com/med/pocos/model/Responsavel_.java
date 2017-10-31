package br.com.med.pocos.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-31T16:40:23.115-0300")
@StaticMetamodel(Responsavel.class)
public class Responsavel_ {
	public static volatile SingularAttribute<Responsavel, Long> seqResponsavel;
	public static volatile SingularAttribute<Responsavel, String> nome;
	public static volatile SingularAttribute<Responsavel, String> cpf;
	public static volatile SingularAttribute<Responsavel, String> email;
	public static volatile SingularAttribute<Responsavel, String> celular;
	public static volatile SingularAttribute<Responsavel, String> fixo;
	public static volatile SingularAttribute<Responsavel, Date> dataCadastro;
	public static volatile SingularAttribute<Responsavel, Date> dataEncerramentoContrato;
	public static volatile SingularAttribute<Responsavel, Boolean> isProprietario;
}
