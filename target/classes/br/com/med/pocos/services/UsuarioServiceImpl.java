package br.com.med.pocos.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.util.Utils;

@Stateless(name = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@EJB
	public EntityManagerService emService;

	@Override
	public void salvar(Object object) {

		Usuario usuario = (Usuario) object;
		usuario.setIsAtivo(true);
		
		String senhaMD5 = Utils.gerarMD5(usuario.getSenha());	
		
		usuario.setSenha(senhaMD5);
		
		if (usuario.getSeqUsuario() == null) {
			
			usuario.setDataCadastro(new Date());
			
			emService.getEntityManager().persist(object);
			
		} else {
			
			emService.getEntityManager().merge(object);
		}

	}

	@Override
	public Object getObject(Long seqId) {

		return emService.getEntityManager().getReference(Usuario.class, seqId);

	}

	@Override
	public void deletar(Object object) {
		
		Usuario usuario = (Usuario) object;
		
		usuario.setIsAtivo(false);
		
		emService.getEntityManager().merge(usuario);

	}

	@Override
	public List<?> listar() {
		// TODO Auto-generated method stub
		return emService.getEntityManager().createNamedQuery("Usuario.buscaUsuarios").getResultList();
	}

	@Override
	public boolean verifyUser(String email, String senha) {
		
		try {
			emService.getEntityManager().createNamedQuery("Usuario.verificaUsuario")
					.setParameter("email", email).setParameter("senha", Utils.gerarMD5(senha)).getSingleResult();
			
		} catch (Exception e) {
			
			return false;
		}
		
		return true;
	}

}
