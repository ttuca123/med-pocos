package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Remote;

import br.com.med.pocos.model.Empreendimento;

@Remote
public interface EmpreendimentoService extends GenericService{

	
	public List<Empreendimento> listar();

	public List<Empreendimento> listar(Object object);

}
