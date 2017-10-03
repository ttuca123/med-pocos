package br.com.med.pocos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDAO {

	@PersistenceContext(name = "pocos")
	protected EntityManager em;

	public <T> void salvar(T objeto) {

		em.persist(objeto);
	}

	public List<?> listar(String tabela) {

		return em.createQuery("SELECT * FROM " + tabela + " US WHERE US.HAS_ATIVO=1").getResultList();

	}
}
