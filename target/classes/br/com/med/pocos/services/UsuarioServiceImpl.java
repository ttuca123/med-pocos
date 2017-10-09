package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.util.EntityManagerService;

@Stateless(name = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@EJB
	public EntityManagerService emService;

	@Override
	public void salvar(Object object) {

		Usuario usuario = (Usuario) object;
		usuario.setIsAtivo(true);
		
		if (usuario.getSeqUsuario() == null) {
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
	public void deletar(Usuario usuario) {

		usuario.setIsAtivo(false);
		
		emService.getEntityManager().merge(usuario);

	}

	@Override
	public List<?> listar() {
		// TODO Auto-generated method stub
		return emService.getEntityManager().createNamedQuery("Usuario.buscaUsuarios").getResultList();
	}

}
