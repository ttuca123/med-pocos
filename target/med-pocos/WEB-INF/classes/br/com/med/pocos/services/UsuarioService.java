package br.com.med.pocos.services;

import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.med.pocos.dao.UsuarioDAO;
import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.util.EntityManagerProducer;

@Named
public class UsuarioService {

	EntityManagerProducer producer;

//	@Produces
//	@PersistenceContext(unitName = "PostgresDS")
//	public EntityManager entityManager;

	@Inject
	public UsuarioService(EntityManagerProducer producer) {
		this.producer = producer;

	}

	// @Inject
	private UsuarioDAO usuarioDAO;

	public UsuarioService() {

	}

	

	public void salvar(Usuario usuario) {

		usuarioDAO.save(usuario);

	}

	public Object getUsuario(Long seqId) {

		return usuarioDAO.getObject(Usuario.class, seqId);

	}

	public void deletar(Long seqId) {

		Usuario usuario = (Usuario) usuarioDAO.getObject(Usuario.class, seqId);
		usuarioDAO.remove(usuario);

	}

	

}
