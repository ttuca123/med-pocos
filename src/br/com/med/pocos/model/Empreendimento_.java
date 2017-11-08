package br.com.med.pocos.model;

import br.com.med.pocos.enu.EnumTipoEmpreendimento;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-11-07T18:31:35.625-0300")
@StaticMetamodel(Empreendimento.class)
public class Empreendimento_ {
	public static volatile SingularAttribute<Empreendimento, Long> seqEmpreendimento;
	public static volatile SingularAttribute<Empreendimento, String> nomeFantasia;
	public static volatile SingularAttribute<Empreendimento, String> descricao;
	public static volatile SingularAttribute<Empreendimento, Date> dataCadastro;
	public static volatile SingularAttribute<Empreendimento, Date> dataEncerramento;
	public static volatile SingularAttribute<Empreendimento, EnumTipoEmpreendimento> tipoEmpreendimento;
	public static volatile SingularAttribute<Empreendimento, Responsavel> responsavel;
	public static volatile SingularAttribute<Empreendimento, String> cep;
	public static volatile SingularAttribute<Empreendimento, String> complemento;
	public static volatile SingularAttribute<Empreendimento, RazaoSocial> razaoSocial;
	public static volatile ListAttribute<Empreendimento, Hidrometro> lstHidrometros;
}
