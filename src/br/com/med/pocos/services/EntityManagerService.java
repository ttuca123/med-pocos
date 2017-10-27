package br.com.med.pocos.services;

import javax.ejb.Remote;
import javax.persistence.EntityManager;

@Remote
public interface EntityManagerService {

	
	public EntityManager getEntityManager();
}
