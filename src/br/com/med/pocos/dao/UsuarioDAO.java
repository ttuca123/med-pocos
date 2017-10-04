package br.com.med.pocos.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class UsuarioDAO extends GenericDAO {

	@PersistenceContext(unitName = "med-pocos")
	public EntityManager em;

	public <T> void save(T objeto) {

		try {

			em.getTransaction().begin();

			em.persist(objeto);

			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<?> list(String tabela) {

		return em.createQuery("SELECT * FROM " + tabela + " US WHERE US.HAS_ATIVO=1").getResultList();

	}

	public Object getObject(Class<?> entityClass, Long seqId) {

		return em.find(entityClass, seqId);
	}

	public <T> void remove(T objeto) {

		try {
			em.getTransaction().begin();

			em.remove(objeto);

			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			em.getTransaction().rollback();
		}

	}

}
