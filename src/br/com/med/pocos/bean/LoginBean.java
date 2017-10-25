package br.com.med.pocos.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.UsuarioService;
import br.com.med.pocos.util.SessionUtils;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{


	private static final long serialVersionUID = 2813099750794133367L;

	
	private Usuario usuario = new Usuario();

	@EJB
	private UsuarioService usuarioService;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String logar() {

		boolean valid = usuarioService.verifyUser(usuario.getEmail(), usuario.getSenha());

		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("user", usuario.getEmail());
			return "painel_principal?faces-redirect=true";
			
		} else {
			Utils.addMessageAviso("Login ou senha incorreta");		
		}
		
		return "";
	}
	
	public String logout() {
		
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		
		return "loginPage";
	}
	
	

}
