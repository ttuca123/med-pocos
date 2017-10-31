package br.com.med.pocos.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.med.pocos.model.Responsavel;
import br.com.med.pocos.model.Responsavel_;
import br.com.med.pocos.util.DataUtils;

/**
 * Classe responsável pela manutenção dos responsáveis dos empreendimentos
 * 
 * @author Artur
 *
 */

@Stateless(name = "ResponsavelService")
public class ResponsavelServiceImpl implements ResponsavelService {

	private CriteriaBuilder criteriaBuilder;

	private Root<Responsavel> root;

	@EJB
	public EntityManagerService emService;

	@Override
	public void salvar(Object object) throws Exception {

		Responsavel responsavel = (Responsavel) object;

		if (responsavel != null) {
			if (responsavel.getSeqResponsavel() == null) {

				Date data = DataUtils.converterDataTimeZone();

				responsavel.setDataCadastro(data);

				emService.getEntityManager().persist(object);

			} else {
				editar(responsavel);

			}
		} else {
			throw new Exception();
		}
	}

	private void editar(Responsavel responsavel) {

		emService.getEntityManager().merge(responsavel);

	}

	@Override
	public Object getObject(Long seqId) {

		return null;

	}

	@Override
	public void deletar(Object object) {

		try {
			Responsavel responsavel = (Responsavel) object;

			Date data = DataUtils.converterDataTimeZone();

			responsavel.setDataEncerramentoContrato(data);

			emService.getEntityManager().merge(responsavel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Responsavel> listar() {
		// TODO Auto-generated method stub

		List<Responsavel> responsaveis;

		try {
			responsaveis = emService.getEntityManager().createNamedQuery("Responsavel.buscaResponsaveis")
					.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsaveis = new ArrayList<Responsavel>();
		}

		return responsaveis;
	}

	@Override
	public boolean verificarPropriedadeResponsavel(Responsavel responsavel, String nomeEmpreedimento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Responsavel> listar(Object object) {

		Responsavel responsavel = (Responsavel) object;

		List<Responsavel> responsaveis = new ArrayList<Responsavel>();

		responsaveis = verificarFiltros(responsavel);

		return responsaveis;
	}

	private List<Responsavel> verificarFiltros(Responsavel responsavel) {

		criteriaBuilder = emService.getEntityManager().getCriteriaBuilder();

		CriteriaQuery<Responsavel> query = criteriaBuilder.createQuery(Responsavel.class);

		root = query.from(Responsavel.class);

		List<Predicate> predicates = adicionarFiltros(responsavel);

		for (Predicate predicado : predicates) {

			query.where(predicado);

		}
		TypedQuery<Responsavel> resultado = emService.getEntityManager().createQuery(query);

		return resultado.getResultList();

	}

	private List<Predicate> adicionarFiltros(Responsavel responsavel) {

		Path<Date> data = root.get(Responsavel_.dataEncerramentoContrato);

		Path<Boolean> proprietario = root.get(Responsavel_.isProprietario);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (responsavel.isAtivo()) {

			Predicate predicadoAtivo = (Predicate) criteriaBuilder.isNotNull(data);

			predicates.add(predicadoAtivo);
		} else {

			Predicate predicateInativo = (Predicate) criteriaBuilder.isNull(data);

			predicates.add(predicateInativo);
		}

		if (responsavel.isProprietario()) {

			Predicate predicadoAtivo = (Predicate) criteriaBuilder.isTrue(proprietario);

			predicates.add(predicadoAtivo);

		} else {

			Predicate predicadoAtivo = (Predicate) criteriaBuilder.isFalse(proprietario);

			predicates.add(predicadoAtivo);

		}

		return predicates;
	}

	public List<Responsavel> listarResponsaveis() {

		return new ArrayList<Responsavel>();
	}

}
