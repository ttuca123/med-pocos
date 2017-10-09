package br.com.med.pocos.services;

public interface GenericService {
	
	public void salvar(Object object);
	
	public Object listar(Long seqId);
	
	public void deletar (Long seqId);

}
