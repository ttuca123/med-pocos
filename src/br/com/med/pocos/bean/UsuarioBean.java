package br.com.med.pocos.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.med.pocos.exception.RegistroDuplicadoException;
import br.com.med.pocos.exception.UsuarioNaoEncontradoException;
import br.com.med.pocos.model.Permissao;
import br.com.med.pocos.model.Regra;
import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.LoginService;
import br.com.med.pocos.services.PermissaoService;
import br.com.med.pocos.util.Utils;

/**
 * 
 * @author Artur
 * @version 1.0 Página responsável pelo crud dos usuários do sistema
 *
 */

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private PermissaoService permissaoService;

	@EJB
	private LoginService loginService;

	@ManagedProperty(value = "#{usuario}")
	private Usuario usuario;

	@ManagedProperty(value = "#{permissoes}")
	private List<String> permissoes;

	@ManagedProperty(value = "#{usuarios}")
	private List<Usuario> usuarios;

	private List<String> permissoesDecifradas;

	private List<Usuario> filteredUsuarios;

	public void novo() {

		usuario = new Usuario();
		permissoes = new ArrayList<String>();
	}

	@PostConstruct
	public void inicializar() {

		novo();
	}

	public void salvarNovo() throws Exception {

		alterarPermissoes();

		salvar();
	}

	public void salvar() throws Exception {

		try {

			usuarioService.salvar(usuario);

			Utils.addMessage(Utils.getMensagem(MSG_SAVE_SUCESSO));

			enviarEmailNovoCadastro();

			getListarTodos();

		} catch (UsuarioNaoEncontradoException e) {

			Utils.addMessageException(e.getMessage());

			String info = logThrowable(e);

			log.info(info);

		} catch (RegistroDuplicadoException e) {

			Utils.addMessageAviso(e.getMessage());
			String info = logThrowable(e);

			log.info(info);
		} catch (IOException e) {

			Utils.addMessageException(Utils.getMensagem(MSG_EMAIL_ERRO));

			String erro = logThrowable(e);

			log.error(erro);

			enviarEmailErro(erro);
		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem(MSG_SAVE_ERRO));

			String erro = logThrowable(e);

			log.error(erro);

			enviarEmailErro(erro);

		} finally {

			novo();
		}

	}

	public void alterarPermissoes() {

		usuario.getRegras().clear();

		atribuirPermissao();

	}

	public void atribuirPermissao() {

		List<Regra> regras = new ArrayList<Regra>();

		for (String permissao : permissoes) {

			Permissao novaPermissao = (Permissao) permissaoService.getObject(Long.parseLong(permissao));

			regras.add(new Regra(usuario, novaPermissao));

		}

		usuario.setRegras(regras);

	}

	public void enviarEmailNovoCadastro() throws UsuarioNaoEncontradoException, IOException {

		if (usuario.getSeqUsuario() == null) {

			loginService.enviarTokenEmail(usuario);

			Utils.addMessage(Utils.getMensagem(MSG_LINK_ENVIADO));
		}

	}

	public void excluir(ActionEvent actionEvent) throws Exception {

		try {

			usuarioService.deletar(usuario);

			Utils.addMessage(Utils.getMensagem(MSG_EXCLUIDO_SUCESSO));

			getListarTodos();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem(MSG_EXCLUIDO_ERRO));
			String erro = logThrowable(e);

			log.error(erro);

			enviarEmailErro(erro);
		}
	}

	public void getListarTodos() throws Exception {

		try {

			usuarios = (List<Usuario>) usuarioService.listar();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem(MSG_LISTAGEM_ERRO));

			String erro = logThrowable(e);

			log.error(erro);
			
			enviarEmailErro(e.getMessage());

		}

	}

	public List<Usuario> getListar() throws Exception {

		try {

			usuarios = (List<Usuario>) usuarioService.listar();
			
		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem(MSG_LISTAGEM_ERRO));

			String erro = logThrowable(e);

			log.error(erro);

			enviarEmailErro(erro);
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

		usuarios = (List<Usuario>) usuarioService.listar(usuario);

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {

		if (usuarios == null) {

			usuarios = new ArrayList<Usuario>();
		}

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

	public List<String> getPermissoes() {

		if (permissoes == null) {

			permissoes = new ArrayList<String>();

		}
		return permissoes;
	}

	public void setPermissoes(List<String> permissoes) {
		this.permissoes = permissoes;
	}

	public List<String> getPermissoesDecifradas() {

		if (permissoesDecifradas == null) {

			permissoesDecifradas = new ArrayList<String>();
		}

		permissoesDecifradas.clear();

		for (String permissao : getPermissoes()) {

			switch (Integer.parseInt(permissao)) {
			case 1:

				permissoesDecifradas.add("Administrador");

				break;
			case 2:

				permissoesDecifradas.add("Responsável");

				break;
			case 3:

				permissoesDecifradas.add("Suporte");

				break;
			default:
				break;
			}

		}

		return permissoesDecifradas;
	}

	public void setPermissoesDecifradas(List<String> permissoesDecifradas) {
		this.permissoesDecifradas = permissoesDecifradas;
	}

}
