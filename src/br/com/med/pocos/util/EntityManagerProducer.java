package br.com.med.pocos.util;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@RequestScoped
public class EntityManagerProducer implements Serializable{

	@PersistenceContext(name="PostgresDS")
	private EntityManager entityManager2;
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("PostgresDS");
    private EntityManager entityManager = factory.createEntityManager();

	 @Produces
	    @RequestScoped
	    public EntityManager criaEntityManager(){
	        return entityManager;
	    }


	public void closeEntityManager(@Disposes EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
		}
	}
}
