package br.com.med.pocos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.UsuarioService;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -4970294226807286353L;

	private Usuario usuario = new Usuario();

	@ManagedProperty(value = "#{usuarios}")
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	@EJB
	private UsuarioService usuarioService;

	public String novo() {

		usuario = new Usuario();

		return "cadastro_usuario.xhtml";
	}

	@PostConstruct
	public void inicializar() {

		novo();
	}

	public void salvar() {

		usuarioService.salvar(usuario);

		addMessage("Cadastro realizado com sucesso");

		novo();

	}

	public String getListar() {

		usuarios = (List<Usuario>) usuarioService.listar();

		return "listar_usuarios.xhtml";
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void addMessage(String summary) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
