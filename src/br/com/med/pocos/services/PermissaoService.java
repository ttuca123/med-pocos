package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Remote;

import br.com.med.pocos.model.Permissao;

@Remote
public interface PermissaoService extends GenericService{

	public List<Permissao> getAll();

	public List<String> getPermissoesDecifradas(List<String>  permissoesDecifradas);
	
	public Permissao getObject(Long seqId);
}
