package br.com.med.pocos.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.med.pocos.dao.UsuarioDAO;
import br.com.med.pocos.model.Usuario;

@Named("usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -4970294226807286353L;

	private Usuario usuario = new Usuario();

	@Inject
	UsuarioDAO usuarioDAO;

	// @ManagedProperty("#{}")
	// private UsuarioViewService usuarioViewService;

	public String novo() {

		usuario = new Usuario();

		return "listar_usuarios.xhtml";
	}

	@PostConstruct
	public void inicializar() {

		novo();
	}

	public String salvar() {

		usuarioDAO.save(usuario);

		return novo();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
