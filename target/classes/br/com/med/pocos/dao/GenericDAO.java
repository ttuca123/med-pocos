package br.com.med.pocos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Classe responsável por gerenciar os métodos DAO
 * 
 * @author Artur
 *
 */

public abstract class GenericDAO {

	
	
	public abstract <T> void save(T objeto);

	public abstract List<?> list(String tabela);

	public abstract Object getObject(Class<?> entityClass, Long seqId);

	public abstract <T> void remove(T objeto);

}
