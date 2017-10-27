package br.com.med.pocos.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.med.pocos.model.Responsavel;
import br.com.med.pocos.util.EntityManagerService;
import br.com.med.pocos.util.Utils;

@Stateless(name = "ResponsavelService")
public class ResponsavelServiceImpl implements ResponsavelService {

	@EJB
	public EntityManagerService emService;

	@Override
	public void salvar(Object object) {

		Responsavel Responsavel = (Responsavel) object;

		if (Responsavel.getSeqResponsavel() == null) {

			Responsavel.setDataCadastro(new Date());

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

		responsavel.setDataEncerramentoContrato(new Date());

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

}
