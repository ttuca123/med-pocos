package br.com.med.pocos.services;

import java.util.List;

import br.com.med.pocos.model.Usuario;

public interface GenericService {
	

	
	public void salvar(Object object);
	
	public Object getObject(Long seqId);
	
	public void deletar (Usuario usuario);
	
	public List<?> listar();

}
