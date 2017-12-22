package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Remote;

import br.com.med.pocos.model.Responsavel;

@Remote
public interface ResponsavelService extends GenericService {	
	
	public List<Responsavel> listar();
	
	public List<Responsavel> listar(Object object);	

	public List<Responsavel> listarResponsaveisAtivos();
	
	public Responsavel getObject(Long seqId);
}
