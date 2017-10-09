package br.com.med.pocos.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.UsuarioService;
import br.com.med.pocos.util.EntityManagerProducer;
import br.com.med.pocos.util.Transactional;

@Named
public class UsuarioDAO implements GenericDAO {

	EntityManagerProducer producer;

	EntityManager emP;

	@Inject
	UsuarioService usuarioService;

	@Inject
	public UsuarioDAO(EntityManager em, EntityManagerProducer producer) {
		this.producer = producer;

		emP = em;
	}

	public void save(Usuario objeto) {
		EntityManager em = null;
		
		try {

			em = producer.criaEntityManager();

			em.getTransaction().begin();

			em.persist(objeto);
			em.getTransaction().commit();
			// emP.persist(objeto);

		} catch (Exception e) {

			em.getTransaction().rollback();
		}

	}

	public List<?> list() {

		return emP.createNamedQuery("Usuario.buscaUsuarios").getResultList();

	}

	public Object getObject(Class<?> entityClass, Long seqId) {

		return emP.find(entityClass, seqId);
	}

	@Transactional
	public <T> void remove(T objeto) {

		EntityManager em = null;
		
		try {

			em = producer.criaEntityManager();

			em.getTransaction().begin();

			em.remove(objeto);
			em.getTransaction().commit();
			// emP.persist(objeto);

		} catch (Exception e) {

			em.getTransaction().rollback();
		}
		
		
		try {

			emP.remove(objeto);

			emP.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			emP.getTransaction().rollback();
		}

	}

}
