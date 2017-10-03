package br.com.med.pocos.bean;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-10-02T23:22:50.310-0300")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, Long> seqUsuario;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, String> sobrenome;
	public static volatile SingularAttribute<Usuario, String> email;
	public static volatile SingularAttribute<Usuario, String> telefone;
	public static volatile SingularAttribute<Usuario, Date> dtCadastro;
	public static volatile SingularAttribute<Usuario, Boolean> isAtivo;
}
