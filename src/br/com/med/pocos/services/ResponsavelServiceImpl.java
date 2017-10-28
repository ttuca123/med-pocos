package br.com.med.pocos.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.med.pocos.model.Responsavel;
import br.com.med.pocos.util.DataUtils;

@Stateless(name = "ResponsavelService")
public class ResponsavelServiceImpl implements ResponsavelService {

	@EJB
	public EntityManagerService emService;

	@Override
	public void salvar(Object object) {

		Responsavel Responsavel = (Responsavel) object;

		if (Responsavel.getSeqResponsavel() == null) {
				
			Date data = DataUtils.converterDataTimeZone();
			
			Responsavel.setDataCadastro(data);

			emService.getEntityManager().persist(object);

		} else {

			emService.getEntityManager().merge(object);
		}

	}

	@Override
	public Object getObject(Long seqId) {

		return emService.getEntityManager().getReference(Responsavel.class, seqId);

	}

	@Override
	public void deletar(Object object) {

		Responsavel responsavel = (Responsavel) object;

		Date data = DataUtils.converterDataTimeZone();
		
		responsavel.setDataEncerramentoContrato(data);

		emService.getEntityManager().merge(responsavel);

	}

	@Override
	public List<?> listar() {
		// TODO Auto-generated method stub
		return emService.getEntityManager().createNamedQuery("Responsavel.buscaResponsaveis").getResultList();
	}

	@Override
	public boolean verificarPropriedadeResponsavel(Responsavel responsavel, String nomeEmpreedimento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<?> listar(Object object) {
		
		Responsavel responsavel = (Responsavel) object;
		
		
		@SuppressWarnings("unchecked")
		List<Responsavel> responsaveis =  emService.getEntityManager().createNamedQuery("Responsavel.buscaResponsaveisComCriterios")
				.setParameter("nome","%" + responsavel.getNome()+ "%")
				.setParameter("cpf", responsavel.getCpf())
				.setParameter("email","%"+ responsavel.getEmail()+ "%")
				.getResultList();
		
		return responsaveis;
	}

}
