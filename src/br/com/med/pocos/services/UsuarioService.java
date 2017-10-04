package br.com.med.pocos.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.med.pocos.dao.UsuarioDAO;
import br.com.med.pocos.model.Usuario;

@Named
public class UsuarioService {

	@Inject
	UsuarioDAO usuarioDAO;

	public UsuarioService() {

	}

	private String TABELA = "USUARIOS";

	public void salvar(Object usuario) {

		usuarioDAO.save(usuario);

	}

	public Object getUsuario(Long seqId) {

		return usuarioDAO.getObject(Usuario.class, seqId);

	}

	public void deletar(Long seqId) {

		Usuario usuario = (Usuario) usuarioDAO.getObject(Usuario.class, seqId);
		usuarioDAO.remove(usuario);

	}

	public List<Usuario> getUsuarios() {

		List<Usuario> usuarios = (List<Usuario>) usuarioDAO.list(TABELA);

		return usuarios;

	}

}
