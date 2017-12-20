package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.med.pocos.model.Permissao;
import br.com.med.pocos.model.Usuario;

/**
 * Class responsável pelo gerenciamento de usuários do sistema
 * 
 * @author Artur
 *
 */

@Stateless(name = "PermissaoService")
public class PermissaoServiceImpl implements PermissaoService {

	@EJB
	public EntityManagerService emService;

	@Override
	public List<Permissao> getAll() {

		return emService.getEntityManager().createNamedQuery("Permissao.findAll").getResultList();

	}

	@Override
	public void salvar(Object object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getObject(Long seqId) {
		// TODO Auto-generated method stub
		return emService.getEntityManager().find(Permissao.class, seqId);
	}

	@Override
	public void deletar(Object object) {
		// TODO Auto-generated method stub
		
	}

}
