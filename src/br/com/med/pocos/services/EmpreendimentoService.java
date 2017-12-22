package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Remote;

import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Hidrometro;

@Remote
public interface EmpreendimentoService extends GenericService{

	
	public List<Empreendimento> listar();

	public List<Empreendimento> listar(Object object);
	
	public List<Hidrometro> listarHidrometrosByEmpreendimento (Empreendimento Empreendimento);
	
	public Empreendimento getObject(Long seqId);
	

}
