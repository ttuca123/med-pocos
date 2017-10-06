package br.com.med.pocos.dao;

import java.util.List;

import br.com.med.pocos.model.Usuario;

/**
 * Classe responsável por gerenciar os métodos DAO
 * 
 * @author Artur
 *
 */

public interface GenericDAO {

	public abstract void save(Usuario usuario);

	public abstract List<?> list();

	public abstract Object getObject(Class<?> entityClass, Long seqId);

	public abstract <T> void remove(T objeto);

}
