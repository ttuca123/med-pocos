package br.com.med.pocos.services;

import java.util.List;

public interface GenericService {
	
	public void salvar(Object object);
	
	public Object getObject(Long seqId);
	
	public void deletar (Long seqId);
	
	public List<?> listar();

}
