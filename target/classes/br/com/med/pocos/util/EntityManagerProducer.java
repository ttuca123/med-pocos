package br.com.med.pocos.util;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer implements Serializable{

	@PersistenceContext(name="PostgresDS")
	private EntityManager entityManager2;
	
	private EntityManagerFactory factory;
    private EntityManager entityManager;

    public EntityManagerProducer(){
    	factory = Persistence.createEntityManagerFactory("PostgresDS");
    }
    
    
	 @Produces
	 @RequestScoped
	    public EntityManager criaEntityManager(){
		 
		 entityManager = factory.createEntityManager();
	        return entityManager;
	    }


	public void closeEntityManager(@Disposes EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
		}
	}
}
