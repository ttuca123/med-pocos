package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Remote;

import br.com.med.pocos.model.Hidrometro;

@Remote
public interface HidrometroService extends GenericService{

	
	public List<Hidrometro> listarTodos();
	
	public List<Hidrometro> listarAtivos();

	public List<Hidrometro> listar(Hidrometro hidrometro);
	
	public List<Hidrometro> listarSemEmpreendimentoAssociado();

}
