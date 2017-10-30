package br.com.med.pocos.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless(name = "EntityManagerService")
public class EntityManagerServiceImpl implements EntityManagerService {

	@PersistenceContext(name = "med-pocos")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
