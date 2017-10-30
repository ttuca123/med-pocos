package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Remote;

import br.com.med.pocos.model.Responsavel;

@Remote
public interface ResponsavelService extends GenericService {

	public boolean verificarPropriedadeResponsavel(Responsavel responsavel, String nomeEmpreedimento);
	
	public List<Responsavel> listar();
	
	public List<Responsavel> listar(Object object);
}
