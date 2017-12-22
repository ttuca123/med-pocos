package br.com.med.pocos.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.med.pocos.bean.UsuarioBean;
import br.com.med.pocos.enu.EnumPermissao;
import br.com.med.pocos.exception.UsuarioNaoEncontradoException;
import br.com.med.pocos.model.Permissao;
import br.com.med.pocos.model.Regra;
import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.EmailService;
import br.com.med.pocos.services.LoginService;
import br.com.med.pocos.services.PermissaoService;
import br.com.med.pocos.services.UsuarioService;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioTest {

	@Mock
	private PermissaoService permissaoService;

	@Mock
	private UsuarioService usuarioService;

	@Mock
	private LoginService loginService;

	@Mock
	private EmailService emailService;

	@Test
	public void testNovoUsuario() {

		UsuarioBean usuarioBean = new UsuarioBean();

		usuarioBean.novo();

		Assert.assertNotNull(usuarioBean.getUsuario());

		Assert.assertNotNull(usuarioBean.getPermissoes());
	}

	@Test
	public void testVerificarRegrasVazias() {

		List<Regra> regras;

		Permissao permissao = new Permissao();

		Long codigoPermissao = EnumPermissao.SUPORTE.getCodigo().longValue();

		Mockito.when(permissaoService.getObject(codigoPermissao)).thenReturn(permissao);

		UsuarioBean usuarioBean = new UsuarioBean();

		usuarioBean.setPermissoes(new ArrayList<String>());

		regras = usuarioBean.atribuirPermissao(permissaoService);

		Assert.assertNotNull("Regras estão nulas", regras);

		Assert.assertTrue("Regras não estão vazias", regras.isEmpty());
	}

	@Test
	public void testVerificarRegrasNulas() {

		List<Regra> regras;

		Permissao permissao = new Permissao();

		Long codigoPermissao = EnumPermissao.RESPONSAVEL.getCodigo().longValue();

		Mockito.when(permissaoService.getObject(codigoPermissao)).thenReturn(permissao);

		UsuarioBean usuarioBean = new UsuarioBean();

		usuarioBean.setPermissoes(null);

		regras = usuarioBean.atribuirPermissao(permissaoService);

		Assert.assertNotNull("Regras estão nulas", regras);

		Assert.assertTrue("Regras não estão vazias", regras.isEmpty());
	}

	@Test
	public void testVerificarUsuarioComUmaPermissao() {

		Long codigoPermissao = EnumPermissao.ADMIN.getCodigo().longValue();

		String descricaoPermissao = EnumPermissao.ADMIN.getDescricao();

		List<Regra> lstRegras;

		List<String> lstPermissoes = new ArrayList<String>();

		lstPermissoes.add(codigoPermissao.toString());

		Permissao permissao = new Permissao();

		permissao.setSeqPermissao(codigoPermissao);

		permissao.setDescricao(descricaoPermissao);

		Mockito.when(permissaoService.getObject(codigoPermissao)).thenReturn(permissao);

		UsuarioBean usuarioBean = new UsuarioBean();

		usuarioBean.setPermissoes(lstPermissoes);

		lstRegras = usuarioBean.atribuirPermissao(permissaoService);

		Assert.assertNotNull(lstRegras);

		Assert.assertFalse("Regras estão vazias", lstRegras.isEmpty());
	}

	@Test
	public void testSalvarUsuarioNulo() {

		Usuario usuario = null;

		boolean condicaoExcecao = false;

		try {

			Mockito.doThrow(new Exception()).when(usuarioService).salvar(usuario);

			UsuarioBean usuarioBean = new UsuarioBean();

			usuarioBean.salvarUsuario(usuarioService);
		} catch (Exception e) {

			condicaoExcecao = true;
		}

		Assert.assertTrue(condicaoExcecao);
	}

	@Test
	public void testEnviarEmailNovoUsuarioNulo() throws UsuarioNaoEncontradoException, IOException {

		boolean condicao = false;
		try {

			UsuarioBean usuarioBean = new UsuarioBean();

			usuarioBean.enviarEmailNovoCadastro(loginService);

		} catch (Exception e) {
			condicao = true;
			Mockito.verify(loginService, Mockito.times(0)).enviarTokenEmail(null);
		}
		Assert.assertTrue(condicao);

	}

	@Test
	public void testEnviarEmailUsuarioExistente() throws UsuarioNaoEncontradoException, IOException {

		UsuarioBean usuarioBean = new UsuarioBean();

		Usuario usuario = new Usuario();

		usuario.setSeqUsuario(1L);

		usuarioBean.setUsuario(usuario);

		usuarioBean.enviarEmailNovoCadastro(loginService);

		Mockito.verify(emailService, Mockito.times(0)).enviarEmail("titulo", "mensagem", new ArrayList<String>());
		;

	}

}
