package br.com.med.pocos.services;

import java.io.UnsupportedEncodingException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.med.pocos.exception.UsuarioNaoEncontradoException;
import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.util.EmailService;
import br.com.med.pocos.util.Utils;

/**
 * Class responsável pelo gerenciamento de usuários do sistema
 * 
 * @author Artur
 * @version 1.0
 *
 */

@Stateless(name = "LoginService")
public class LoginServiceImpl implements LoginService {

	@EJB
	public EntityManagerService emService;

	@EJB
	public EmailService emailService;

	@EJB
	private UsuarioService usuarioService;

	@EJB
	private LoginService loginService;

	@Override
	public void enviarTokenEmail(Usuario usuario) throws UsuarioNaoEncontradoException {

		Usuario usuarioEncontrado = usuarioService.findUserByEmail(usuario.getEmail());

		usuario = usuarioEncontrado;

		String token = Utils.criarTokenJWT(usuarioEncontrado.getEmail());

		enviarResetSenha(usuarioEncontrado, token);

		
	}

	@Override
	public void validarToken(Usuario usuario, String token) throws IllegalArgumentException, UnsupportedEncodingException, UsuarioNaoEncontradoException {

		
		Utils.verificarTokenJWT(token, usuario.getEmail());

		Usuario usuarioEncontrado = usuarioService.findUserByEmail(usuario.getEmail());

		usuarioEncontrado.setSenha(usuario.getSenha());

		usuarioService.atualizarSenha(usuarioEncontrado);
	}

	private void enviarResetSenha(Usuario usuario, String token) throws UsuarioNaoEncontradoException {

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		String servidorLocal = req.getLocalName();

		String aplicacao = req.getContextPath();

		String parametrosTitulo[] = new String[1];

		parametrosTitulo[0] = usuario.getNome();

		String parametrosBody[] = new String[3];

		parametrosBody[0] = servidorLocal;

		parametrosBody[1] = aplicacao;

		parametrosBody[2] = token;

		String titulo = Utils.getMensagem("page.cadastro.email.salvar.titulo.sucesso", parametrosTitulo);

		String body = Utils.getMensagem("page.cadastro.email.salvar.body.sucesso", parametrosBody);

		emailService.enviarEmail(titulo, body, usuario.getEmail());

	}

}
