package br.com.med.pocos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.UsuarioService;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -4970294226807286353L;

	private Usuario usuario = new Usuario();

	@ManagedProperty(value = "#{usuarios}")
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private List<Usuario> filteredUsuarios;

	@EJB
	private UsuarioService usuarioService;

	public void novo() {

		usuario = new Usuario();

		//return "cadastrarUsuario";
	}

	@PostConstruct
	public void inicializar() {

		getListar();
		
	}

	public void salvar() {

		usuarioService.salvar(usuario);

		Utils.addMessage("Cadastro realizado com sucesso");

		novo();
		
		getListar();

	}
	
	
	public void excluir(ActionEvent actionEvent) {

		try {

			usuarioService.deletar(usuario);

			addMessage("Item excluido com sucesso!!");

		} catch (Exception e) {
			addMessageException("Ops, Erro ao excluir usuario!!!");
		} finally {
			getListar();
		}
	}

	public String getListar() {

		usuarios = (List<Usuario>) usuarioService.listar();

		return "listar_usuarios";
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

	public List<String> getFilterUsuario() {

		List<String> filtro = new ArrayList<String>();

		for (Usuario usuario : usuarios) {

			filtro.add(usuario.getNome());

		}

		return filtro;
	}

	public List<Usuario> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredBandas(List<Usuario> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}

	public void addMessage(String summary) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageException(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
