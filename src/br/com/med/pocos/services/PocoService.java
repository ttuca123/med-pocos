package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Remote;

import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Poco;

@Remote
public interface PocoService extends GenericService {

	public List<Poco> listarTodosByEmpreendimento(Empreendimento empreendimento);

	public List<Poco> listarAtivos();

	public List<Poco> listar(Empreendimento empreenfimento, Poco poco);
	
	public Poco getObject(Long seqId);

}
