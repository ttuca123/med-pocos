package br.com.med.pocos.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	private String[] paginasSemLogin = { "forgot_senha.xhtml", "reset_senha.xhtml" };

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		HttpSession session = ((HttpServletRequest) request).getSession(true);

		
		if (session.getAttribute("user") == null && !verificarPaginasSemLogin(request)) {

			String contextPath = ((HttpServletRequest) request).getContextPath();

			((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");

		} else {

			chain.doFilter(request, response);

		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private boolean verificarPaginasSemLogin(ServletRequest request) {	
		
		String contextPath = ((HttpServletRequest) request).getContextPath(); 
		
		String paginaAtual = ((HttpServletRequest) request).getRequestURI();
		
		

		boolean paginaSemCredencial = false;

		for (String pagina : paginasSemLogin) {

			if (paginaAtual.lastIndexOf(pagina) > -1) {

				paginaSemCredencial = true;

				break;
			}

		}

		return paginaSemCredencial;

	}
}
