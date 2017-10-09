package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.med.pocos.model.Usuario;

@Stateless(name = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@PersistenceContext(name = "PostgresDS")
	private EntityManager entityManager2;

	@Override
	public void salvar(Object object) {

		entityManager2.persist(object);

	}

	@Override
	public Object getObject(Long seqId) {

		return null;

	}

	@Override
	public void deletar(Long seqId) {
		entityManager2.remove(seqId);

	}

	@Override
	public List<?> listar() {
		// TODO Auto-generated method stub
		return entityManager2.createNamedQuery("Usuario.buscaUsuarios").getResultList();
	}

}
