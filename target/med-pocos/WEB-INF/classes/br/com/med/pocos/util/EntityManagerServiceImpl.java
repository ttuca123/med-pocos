package br.com.med.pocos.util;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "EntityManagerService")
public class EntityManagerServiceImpl implements EntityManagerService {

	@PersistenceContext(name = "PostgresDS")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
