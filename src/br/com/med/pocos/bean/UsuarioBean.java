package br.com.med.pocos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.med.pocos.exception.UsuarioNaoEncontradoException;
import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.LoginService;
import br.com.med.pocos.services.UsuarioService;
import br.com.med.pocos.util.EmailService;
import br.com.med.pocos.util.Utils;

/**
 * 
 * @author Artur
 * @version 1.0
 * 
 *          Página responsável pelo crud dos usuários do sistema
 *
 */

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = -4970294226807286353L;

	private Usuario usuario;

	private boolean todos;

	@ManagedProperty(value = "#{usuarios}")
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private List<Usuario> filteredUsuarios;

	@EJB
	private UsuarioService usuarioService;

	@EJB
	private LoginService loginService;

	@EJB
	private EmailService emailService;

	public void novo() {

		usuario = new Usuario();		
	}

	@PostConstruct
	public void inicializar() {

		novo();
	}

	

	public void salvar() {

		try {
			usuarioService.salvar(usuario);

			loginService.enviarTokenEmail(usuario);			
			
			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.sucesso"));
			
			Utils.addMessage(Utils.getMensagem("page.login.btn.forgot.password.send.success"));

			getListar();
		} catch (UsuarioNaoEncontradoException e) {

			Utils.addMessageException(e.getMessage());
		
		} catch (Exception e) {
			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.erro"));
		}

	}

	public void excluir(ActionEvent actionEvent) {

		try {

			usuarioService.deletar(usuario);

			Utils.addMessage(Utils.getMensagem("page.cadastro.excluir.sucesso"));

			getListar();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem("page.cadastro.excluir.erro"));

		}
	}

	public void getListar() {

		try {

			usuarios = (List<Usuario>) usuarioService.listar();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem("page.cadastro.listar.erro"));
		}

	}

	public List<Usuario> getListarUsuariosAtivos() {

		try {

			usuarios = (List<Usuario>) usuarioService.listar();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem("page.cadastro.listar.erro"));
		}

		return usuarios;

	}

	public int getTotal() {
		if (usuarios != null) {

			return usuarios.size();
		} else {

			return 0;
		}

	}

	public void filtrar() {

		if (todos) {
			getListar();
		} else {

			usuarios = (List<Usuario>) usuarioService.listar(usuario);

		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getFilteredUsuarios() {
		return filteredUsuarios;
	}

	public void setFilteredUsuarios(List<Usuario> filteredUsuarios) {
		this.filteredUsuarios = filteredUsuarios;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
