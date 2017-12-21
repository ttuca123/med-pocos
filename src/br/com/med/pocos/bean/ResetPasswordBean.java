package br.com.med.pocos.bean;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.med.pocos.exception.UsuarioNaoEncontradoException;
import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.LoginService;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "resetPasswordBean")
@ViewScoped
public class ResetPasswordBean implements Serializable {

	private static final long serialVersionUID = 2813099750794133367L;

	private Usuario usuario = new Usuario();

	@EJB
	private LoginService loginService;

	private String token;

	@PostConstruct
	public void post() {
		usuario = new Usuario();
	}

	public void enviarTokenEmail() {

		try {

			loginService.enviarTokenEmail(usuario);

			Utils.addMessage(Utils.getMensagem("page.login.btn.forgot.password.send.success"));

		} catch (UsuarioNaoEncontradoException e) {

			Utils.addMessageException(e.getMessage());

		} catch (IOException e) {
			
			Utils.addMessageException(e.getMessage());
		}

	}

	public void verificarToken() {

		try {

			loginService.validarToken(usuario, token);

			Utils.addMessage(Utils.getMensagem("aviso.token.valido"));

			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.sucesso"));

		} catch (UsuarioNaoEncontradoException e) {

			Utils.addMessageException(e.getMessage());

		} catch (IllegalArgumentException e) {

			Utils.addMessageException(e.getMessage());

		} catch (UnsupportedEncodingException e) {

			Utils.addMessageException(e.getMessage());

		} catch (Exception ex) {

			Utils.addMessage(Utils.getMensagem("aviso.token.invalido"));

		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
