package br.com.med.pocos.filter;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.med.pocos.exception.UsuarioNaoEncontradoException;
import br.com.med.pocos.model.Regra;
import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.UsuarioService;


public class AcessoFilter implements Filter{

	
	
	@EJB
	private UsuarioService usuarioService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		HttpSession session = ((HttpServletRequest) request).getSession(true);

		String email = (String) session.getAttribute("user");
		
		Usuario usuario = new Usuario();
		
		try {
			
			 usuario = usuarioService.findUserByEmail(email);
			
		} catch (UsuarioNaoEncontradoException e) {
			
			e.printStackTrace();
		}
		
		boolean condicao=false;
		
		
		for(Regra regra: usuario.getRegras()) {		
			
			if (regra.getPermissao().getDescricao().equals("Administrador")) {
				
				chain.doFilter(request, response);
				
				condicao = true;
							
				break;
	
			} 
		}
		
		if(!condicao) {
			String contextPath = ((HttpServletRequest) request).getContextPath();
			
			((HttpServletResponse) response).sendRedirect(contextPath + "/painel_principal.xhtml");
		
			
		}
		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
