package br.com.med.pocos.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.med.pocos.exception.UsuarioNaoEncontradoException;
import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.util.DataUtils;
import br.com.med.pocos.util.EmailService;
import br.com.med.pocos.util.Utils;

/**
 * Class responsável pelo gerenciamento de usuários do sistema
 * 
 * @author Artur
 *
 */

@Stateless(name = "UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@EJB
	public EntityManagerService emService;

	@EJB
	public EmailService emailService;

	@Override
	public void salvar(Object object) throws Exception {

		Usuario usuario = (Usuario) object;

		usuario.setIsAtivo(true);

		if (usuario != null) {

			if (usuario.getSeqUsuario() == null) {

				usuario.setSenha(Utils.gerarTokenRandomico());

				Date data = DataUtils.converterDataTimeZone();

				usuario.setDataCadastro(data);

				emService.getEntityManager().persist(usuario);

			} else {

				emService.getEntityManager().merge(usuario);
			}

		}

	}

	@Override
	public Object getObject(Long seqId) {

		return emService.getEntityManager().getReference(Usuario.class, seqId);

	}

	@Override
	public void deletar(Object object) {

		try {
			Usuario usuario = (Usuario) object;

			usuario.setIsAtivo(false);

			emService.getEntityManager().merge(usuario);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Usuario> listar() {

		List<Usuario> usuarios = emService.getEntityManager().createNamedQuery("Usuario.buscaUsuarios").getResultList();

		return usuarios == null ? new ArrayList<Usuario>() : usuarios;
	}

	@Override
	public boolean verifyUser(String email, String senha) {

		boolean condicao;
		try {
			emService.getEntityManager().createNamedQuery("Usuario.verificaUsuario").setParameter("email", email)
					.setParameter("senha", Utils.gerarMD5(senha)).getSingleResult();

			condicao = true;
		} catch (Exception e) {

			condicao = false;
		}

		return condicao;
	}

	@Override
	public List<Usuario> listar(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario findUserByEmail(String email) throws UsuarioNaoEncontradoException {

		Usuario usuario;

		try {
			usuario = (Usuario) emService.getEntityManager().createNamedQuery("Usuario.findUserByEmail")
					.setParameter("email", email.toLowerCase()).getSingleResult();

		} catch (Exception e) {
			throw new UsuarioNaoEncontradoException();
		}

		return usuario;
	}

	@Override
	public void atualizarSenha(Usuario usuario)  {

		String senhaMD5 = Utils.gerarMD5(usuario.getSenha());

		usuario.setSenha(senhaMD5);

		emService.getEntityManager().merge(usuario);

	}

}
