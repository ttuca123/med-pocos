package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.med.pocos.bean.Usuario;
import br.com.med.pocos.dao.UsuarioDAO;

@Stateless(name = "UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@PersistenceContext(name = "pocos")
	private EntityManager em;

	private String TABELA = "USUARIOS";

	private UsuarioDAO usuarioDAO;

	@Override
	public void salvar(Object usuario) {

		usuarioDAO = new UsuarioDAO(em);

		usuarioDAO.salvar(usuario);

	}

	@Override
	public Object listar(Long seqId) {

		return null;
	}

	@Override
	public void deletar(Long seqId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Usuario> getUsuarios() {

		usuarioDAO = new UsuarioDAO(em);

		List<Usuario> usuarios = (List<Usuario>) usuarioDAO.listar(TABELA);

		return usuarios;

	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

}
