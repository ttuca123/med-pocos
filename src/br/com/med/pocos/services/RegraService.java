package br.com.med.pocos.services;

import javax.ejb.Remote;

import br.com.med.pocos.model.Regra;

@Remote
public interface RegraService extends GenericService{

	public void atribuirRegra(Regra regra);

	public void excluirRegra(Regra regra);
	
	public Regra getObject(Long seqId);
}
