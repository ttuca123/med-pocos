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
 * 
 *Classe responsável pela manutenção dos responsáveis dos empreendimentos
 *@author Artur
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
		
		if (responsavel.getSeqResponsavel() == null) {

			Date dataCadastro = DataUtils.converterDataTimeZone();

			responsavel.setDataCadastro(dataCadastro);

			responsavel.setAtivo(true);

			emService.getEntityManager().persist(object);

		} else {
			
			emService.getEntityManager().merge(responsavel);

		}
		
	}	

	@Override
	public Responsavel getObject(Long seqId) {

		Responsavel responsavel = null;
		
		responsavel = (Responsavel) emService.getEntityManager()
				.createNamedQuery("Responsavel.buscaResponsavelById").setParameter("sequencial", seqId)
				.getSingleResult();

		return responsavel;

	}

	@Override
	public void deletar(Object object) {

		
		Responsavel responsavel = (Responsavel) object;

		Date data = DataUtils.converterDataTimeZone();

		responsavel.setDataEncerramentoContrato(data);
		
		responsavel.setAtivo(false);

		emService.getEntityManager().merge(responsavel);
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Responsavel> listar() {

		List<Responsavel> responsaveis = new ArrayList<Responsavel>();
		
		responsaveis = emService.getEntityManager().createNamedQuery("Responsavel.buscaResponsaveis")
				.getResultList();
		
		return responsaveis;
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

		predicados = verificarPredicadoAtivo(predicados, data, responsavel.isAtivo());	
		
		predicados = verificarPredicadoProprietario(predicados, proprietario, responsavel.isProprietario());		

		return predicados;
	}	
	
	private Predicate verificarPredicadoProprietario(Predicate predicados, Path<Boolean> proprietario , boolean isProprietario) {
		
		Predicate predicadoProprietario;
		
		if (isProprietario) {

			predicadoProprietario = (Predicate) criteriaBuilder.isTrue(proprietario);

			predicados = criteriaBuilder.and(predicados, predicadoProprietario);

		} else {

			predicadoProprietario = (Predicate) criteriaBuilder.isFalse(proprietario);

			predicados = criteriaBuilder.and(predicados, predicadoProprietario);

		}
		
		return predicados;
		
		
	}
	
	private Predicate verificarPredicadoAtivo(Predicate predicados, Path<Date> data , boolean ativo) {
		
		if (ativo) {

			Predicate predicadoResponsavelAtivo = (Predicate) criteriaBuilder.isNull(data);

			predicados = criteriaBuilder.and(predicados, predicadoResponsavelAtivo);

		} else {

			Predicate predicadoResponsavelAtivo = (Predicate) criteriaBuilder.isNotNull(data);

			predicados = criteriaBuilder.and(predicados, predicadoResponsavelAtivo);

		}
		
		return predicados;
		
		
	}

	@Override
	public List<Responsavel> listarResponsaveisAtivos() {
		
		List<Responsavel> responsaveis;
		
		responsaveis = emService.getEntityManager().createNamedQuery("Responsavel.buscaResponsaveisAtivos")
				.getResultList();
		
		return responsaveis==null?new ArrayList<Responsavel>():responsaveis;
	}

}
