package br.com.med.pocos.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.med.pocos.enu.EnumTipoHidrometro;
import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Hidrometro;
import br.com.med.pocos.model.Hidrometro_;
import br.com.med.pocos.model.Poco;

@Stateless(name = "pocoService")
public class PocoServiceImpl implements PocoService {

	@EJB
	public EntityManagerService emService;

	private CriteriaBuilder criteriaBuilder;

	private Root<Poco> rootPoco;

	@Override
	public void salvar(Object object) throws Exception {

		Poco poco = (Poco) object;

		if (poco.getSeqPoco() == null) {

			emService.getEntityManager().persist(poco);

		} else {

			editar(poco);
		}

	}

	private void editar(Poco poco) {

		emService.getEntityManager().merge(poco);

	}

	@Override
	public Poco getObject(Long seqId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Object object) {

		Poco poco = (Poco) object;

		poco.setAtivo(false);

		emService.getEntityManager().merge(poco);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Poco> listarTodosByEmpreendimento(Empreendimento empreendimento) {

		List<Poco> pocos = new ArrayList<Poco>();

		pocos = emService.getEntityManager().createNamedQuery("Poco.buscaAllByEmpreendimento").getResultList();

		return pocos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Poco> listarAtivos() {

		
		return null;
	}

	@Override
	public List<Poco> listar(Empreendimento empreenfimento, Poco poco) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
