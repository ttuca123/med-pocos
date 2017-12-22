package br.com.med.pocos.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.med.pocos.exception.UsuarioNaoEncontradoException;
import br.com.med.pocos.model.Usuario;
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
	public void enviarTokenEmail(Usuario usuario) throws UsuarioNaoEncontradoException, IOException {

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

	private void enviarResetSenha(Usuario usuario, String token) throws UsuarioNaoEncontradoException, IOException {

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		String servidorRemoto = req.getLocalName();

		String aplicacao = req.getContextPath();

		String parametrosTitulo[] = new String[1];

		parametrosTitulo[0] = usuario.getNome();

		String parametrosBody[] = new String[2];		

		parametrosBody[0] = aplicacao;

		parametrosBody[1] = token;

		String titulo = Utils.getMensagem("page.cadastro.email.salvar.titulo.sucesso", parametrosTitulo);

		String body = Utils.getMensagem("page.cadastro.email.salvar.body.sucesso", parametrosBody);
		
		List<String> emails = new ArrayList<String>();
		
		emails.add(usuario.getEmail());
		
		emailService.enviarEmail(titulo, body, emails);

	}

}
