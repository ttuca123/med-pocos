package br.com.med.pocos.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.UsuarioService;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

	
	private static final long serialVersionUID = -4970294226807286353L;

	
	private Usuario usuario = new Usuario();

	@Inject
	UsuarioService usuarioService;

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

		usuarioService.salvar(usuario);

		return "listar_usuarios.xhtml";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
