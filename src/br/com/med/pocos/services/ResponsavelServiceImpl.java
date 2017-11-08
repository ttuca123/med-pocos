package br.com.med.pocos.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
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
			
		Responsavel responsavel = null;
		
		try {
			responsavel = (Responsavel) emService.getEntityManager().createNamedQuery("Responsavel.buscaResponsavelById").setParameter("sequencial", seqId)
					.getSingleResult();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsavel = new Responsavel();
		}
		
		return responsavel;

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

		Predicate predicados = adicionarFiltros(responsavel);
		
		Path<String> nome = root.get(Responsavel_.nome);
		
		query.select(root).where(predicados).orderBy(criteriaBuilder.asc(nome));

		TypedQuery<Responsavel> resultado = emService.getEntityManager().createQuery(query);

		return resultado.getResultList();

	}

	private Predicate adicionarFiltros(Responsavel responsavel) {

		Path<Date> data = root.get(Responsavel_.dataEncerramentoContrato);

		Path<Boolean> proprietario = root.get(Responsavel_.isProprietario);
		
		

		Predicate predicados = criteriaBuilder.and();

		if (responsavel.isAtivo()) {

			Predicate predicadoResponsavelAtivo = (Predicate) criteriaBuilder.isNull(data);

			predicados = criteriaBuilder.and(predicados, predicadoResponsavelAtivo);

		}else {
			
			Predicate predicadoResponsavelAtivo = (Predicate) criteriaBuilder.isNotNull(data);

			predicados = criteriaBuilder.and(predicados, predicadoResponsavelAtivo);
			
		}

		if (responsavel.isProprietario()) {

			Predicate predicadoisProprietario = (Predicate) criteriaBuilder.isTrue(proprietario);

			predicados = criteriaBuilder.and(predicados, predicadoisProprietario);

		}else {
			
			Predicate predicadoisProprietario = (Predicate) criteriaBuilder.isFalse(proprietario);

			predicados = criteriaBuilder.and(predicados, predicadoisProprietario);
			
		}		
		
		

		return predicados;
	}

	public List<Responsavel> listarResponsaveis() {

		return new ArrayList<Responsavel>();
	}

	@Override
	public List<Responsavel> listarResponsaveisAtivos() {
		List<Responsavel> responsaveis;

		try {
			responsaveis = emService.getEntityManager().createNamedQuery("Responsavel.buscaResponsaveisAtivos")
					.getResultList();
		} catch (Exception e) {
			
			e.printStackTrace();
			responsaveis = new ArrayList<Responsavel>();
		}

		return responsaveis;
	}

}
