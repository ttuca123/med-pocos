package br.com.med.pocos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.med.pocos.dao.UsuarioDAO;
import br.com.med.pocos.model.Usuario;

@Named("usuarioBean")
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -4970294226807286353L;

	private Usuario usuario = new Usuario();
	
	@Named("usuarios")
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	@Inject
	UsuarioDAO usuarioDAO;

	public String novo() {

		usuario = new Usuario();

		return "cadastro_usuario.xhtml";
	}

	@PostConstruct
	public void inicializar() {

		novo();
	}

	public void salvar() {

		usuarioDAO.save(usuario);

		addMessage("Cadastro realizado com sucesso");
		
		novo();	
				

	}
	
	public String getListar(){
		
		usuarios = (List<Usuario>) usuarioDAO.list();
		
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
