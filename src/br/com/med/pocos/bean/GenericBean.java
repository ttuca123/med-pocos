package br.com.med.pocos.bean;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.commons.logging.impl.Log4JLogger;

import br.com.med.pocos.enu.EnumPermissao;
import br.com.med.pocos.services.UsuarioService;
import br.com.med.pocos.util.EmailService;

public class GenericBean {
	
	//------------------------------- Mensagens -------------------------------------//
		protected static final String MSG_SAVE_SUCESSO = "page.cadastro.salvar.sucesso";
		
		protected static final String MSG_SAVE_ERRO = "page.cadastro.salvar.erro";
		
		protected static final String MSG_EMAIL_ERRO = "erro.email";
		
		protected static final String MSG_LINK_ENVIADO = "page.login.btn.forgot.password.send.success";	
		
		protected static final String MSG_EXCLUIDO_SUCESSO = "page.cadastro.excluir.sucesso";
		
		protected static final String MSG_EXCLUIDO_ERRO = "page.cadastro.excluir.erro";
		
		protected static final String MSG_LISTAGEM_ERRO = "page.cadastro.listar.erro";		
		
	//-------------------------------------------------------------------------------//	
	
	
	public Log4JLogger log = new Log4JLogger();
	
	@EJB
	private EmailService emailService;
	
	@EJB
	protected UsuarioService usuarioService;
	
	protected void enviarEmailErro(String erro) throws Exception {
		
		List<String> lista = new ArrayList<String>();
		
		lista = usuarioService.buscarEmailsByTipo(EnumPermissao.SUPORTE);
			
		emailService.enviarEmail("Erro do Sistema", erro, lista);	
		
	}
	
	protected void enviarEmailAdministrativo(String mensagem) throws Exception {
		
		List<String> lista = new ArrayList<String>();
		
		lista = usuarioService.buscarEmailsByTipo(EnumPermissao.RESPONSAVEL);
			
		emailService.enviarEmail("Erro do Sistema", mensagem, lista);	
		
	}
	
	public String logThrowable(Throwable thrown) {
		   StringWriter sw = new StringWriter();
		   thrown.printStackTrace(new PrintWriter(sw));
		   return sw.toString();
		}
	
}
