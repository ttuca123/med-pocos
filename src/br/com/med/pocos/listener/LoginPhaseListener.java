package br.com.med.pocos.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

/**
 * @author Artur Método responsável pela verificação de Login do usuário
 */
public class LoginPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -7727086585081676029L;

	String paginaAtual;

	private String[] paginasSemLogin = { "forgot_senha.xhtml", "reset_senha.xhtml" };

	@Override
	public void afterPhase(PhaseEvent event) {

		FacesContext facesContext = event.getFacesContext();

		paginaAtual = facesContext.getViewRoot().getViewId();
		
		boolean isLoginPage = (paginaAtual.lastIndexOf("login.xhtml") > -1);

		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

		String user = (String) session.getAttribute("user");

		if (isLoginPage && user == null) {

			if(verificarPaginasSemLogin()) {
			
				NavigationHandler nh = facesContext.getApplication().getNavigationHandler();
	
				nh.handleNavigation(facesContext, null, "loginPage");
			} else {
				return;
			}

		} 

			return;

		

	}

	private boolean verificarPaginasSemLogin() {

		boolean paginaSemCredencial = false;

		for (String pagina : paginasSemLogin) {

			if (paginaAtual.lastIndexOf(pagina) > -1) {

				paginaSemCredencial = true;

				break;
			}

		}

		return paginaSemCredencial;

	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub

		return PhaseId.RESTORE_VIEW;
	}

}
