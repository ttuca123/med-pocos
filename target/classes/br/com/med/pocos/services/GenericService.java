package br.com.med.pocos.services;

public interface GenericService {
	
	public void salvar(Object object) throws Exception;
	
	public Object getObject(Long seqId);
	
	public void deletar (Object object);	

}
