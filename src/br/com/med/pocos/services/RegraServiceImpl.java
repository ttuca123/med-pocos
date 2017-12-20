package br.com.med.pocos.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.med.pocos.exception.RegistroDuplicadoException;
import br.com.med.pocos.model.Permissao;
import br.com.med.pocos.model.Regra;
import br.com.med.pocos.util.EmailService;

/**
 * Class responsável pelo gerenciamento de usuários do sistema
 * 
 * @author Artur
 *
 */

@Stateless(name = "RegraService")
public class RegraServiceImpl implements RegraService {

	@EJB
	public EntityManagerService emService;

	@EJB
	public EmailService emailService;

	@Override
	public void atribuirRegra(Regra regra)  {
		
		if(existeRegra(regra)==0) {
			emService.getEntityManager().persist(regra);
		}

	}

	@Override
	public void excluirRegra(Regra regra) {
		
		if(existeRegra(regra)>0) {
			
			regra = getRegra(regra);
			
			regra.setUsuario(null);
			
			regra.setPermissao(null);
			
			emService.getEntityManager().merge(regra);	
		}
		

	}

	private int existeRegra(Regra regra) {

		
		int resultado = emService.getEntityManager().createNamedQuery("Regra.buscaRegraDuplicada")
					.setParameter("seqUsuario", regra.getUsuario().getSeqUsuario())
					.setParameter("seqPermissao", regra.getPermissao().getSeqPermissao()).getResultList().size();
		
		
		return resultado; 
	}
	
	private Regra getRegra(Regra regra) {

		
		regra = (Regra) emService.getEntityManager().createNamedQuery("Regra.buscaRegraDuplicada")
					.setParameter("seqUsuario", regra.getUsuario().getSeqUsuario())
					.setParameter("seqPermissao", regra.getPermissao().getSeqPermissao()).getSingleResult();
		
		
		return regra; 
	}

	@Override
	public void salvar(Object object) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getObject(Long seqId) {
		// TODO Auto-generated method stub
		return emService.getEntityManager().getReference(Regra.class, seqId);
	}

	@Override
	public void deletar(Object object) {
		// TODO Auto-generated method stub
		
	}

}
