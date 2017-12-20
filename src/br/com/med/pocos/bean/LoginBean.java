package br.com.med.pocos.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.med.pocos.exception.UsuarioNaoEncontradoException;
import br.com.med.pocos.model.Regra;
import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.UsuarioService;
import br.com.med.pocos.util.SessionUtils;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 2813099750794133367L;

	private Usuario usuario = new Usuario();

	@EJB
	private UsuarioService usuarioService;

	
	public boolean isVerificarAcessoAdministrativo() {
		
		boolean condicao = false;
		
		
		
		if(usuario!=null && verificarPermissaoAdministrativa()) {
			
			condicao=true;
		}
		
		return condicao;
		
	}
	
	
	private boolean verificarPermissaoAdministrativa() {
		
		
		boolean condicao=false;
		
		for(Regra regra: usuario.getRegras()) {		
			
			if (regra.getPermissao().getDescricao().equals("Administrador")) {
	
				condicao = true;
				
				break;
			}
		}
		
		
		return condicao;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String logar() throws UsuarioNaoEncontradoException {

		boolean valid = usuarioService.verifyUser(usuario.getEmail().toLowerCase(), usuario.getSenha());		
		
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("user", usuario.getEmail());		
			
			usuario = usuarioService.findUserByEmail(usuario.getEmail());		

			Utils.addMessage(Utils.getMensagem("page.login.sucesso"));

			return "painelPrincipal";
		} else {

			Utils.addMessageAviso(Utils.getMensagem("page.login.erro"));

		}

		return "";
	}

	
	
	
	public String logout() {

		HttpSession session = SessionUtils.getSession();
		session.invalidate();

		return "loginPage";
	}

}
