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

import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Empreendimento_;
import br.com.med.pocos.model.Responsavel;
import br.com.med.pocos.util.DataUtils;

@Stateless(name = "EmpreendimentoService")
public class EmpreendimentoServiceImpl implements EmpreendimentoService{

	private CriteriaBuilder criteriaBuilder;

	private Root<Empreendimento> root;
	
	@EJB
	public EntityManagerService emService;
	
	@EJB
	public ResponsavelService responsavelService;

	
	@Override
	public void salvar(Object object) throws Exception {
		
		Empreendimento empreendimento = (Empreendimento) object;

		if (empreendimento != null) {
			Responsavel responsavel = empreendimento.getResponsavel(); 
			
			if(responsavel.getSeqResponsavel()!=null) {
				
//				responsavel = (Responsavel) responsavelService.getObject(responsavel.getSeqResponsavel());				
				
				empreendimento.setResponsavel(responsavel);
				
				emService.getEntityManager().detach(responsavel);
			}
			
			
			
			if (empreendimento.getSeqEmpreendimento() == null) {

				Date data = DataUtils.converterDataTimeZone();

				empreendimento.setDataCadastro(data);
				
				
				
				emService.getEntityManager().persist(object);

			} else {
				editar(empreendimento);

			}
		} else {
			throw new Exception();
		}
	}
	
	private void editar(Empreendimento empreendimento) {

		emService.getEntityManager().merge(empreendimento);
		
	}

	@Override
	public Object getObject(Long seqId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Object object) {
		
		try {
		
			Empreendimento empreendimento = (Empreendimento) object;

			Date data = DataUtils.converterDataTimeZone();

			empreendimento.setDataEncerramento(data);

			emService.getEntityManager().merge(empreendimento);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Empreendimento> listar() {
		List<Empreendimento> empreendimentos;

		try {
			empreendimentos = emService.getEntityManager().createNamedQuery("Empreendimento.buscaAllEmpreendimentos")
					.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			empreendimentos = new ArrayList<Empreendimento>();
		}

		return empreendimentos;
	}

	@Override
	public List<Empreendimento> listar(Object object) {
		
		Empreendimento empreendimento = (Empreendimento) object;

		List<Empreendimento> empreendimentos = new ArrayList<Empreendimento>();

		empreendimentos = verificarFiltros(empreendimento);

		return empreendimentos;
	}
	
	private List<Empreendimento> verificarFiltros(Empreendimento empreendimento) {

		criteriaBuilder = emService.getEntityManager().getCriteriaBuilder();

		CriteriaQuery<Empreendimento> query = criteriaBuilder.createQuery(Empreendimento.class);

		root = query.from(Empreendimento.class);

		Predicate predicados = adicionarFiltros(empreendimento);
		
		Path<String> nome = root.get(Empreendimento_.nomeFantasia);
		
		query.select(root).where(predicados).orderBy(criteriaBuilder.asc(nome));

		TypedQuery<Empreendimento> resultado = emService.getEntityManager().createQuery(query);

		return resultado.getResultList();

	}

	private Predicate adicionarFiltros(Empreendimento empreendimento) {

		Path<Date> data = root.get(Empreendimento_.dataEncerramento);			

		Predicate predicados = criteriaBuilder.and();

		if (empreendimento.isAtivo()) {

			Predicate predicadoResponsavelAtivo = (Predicate) criteriaBuilder.isNull(data);

			predicados = criteriaBuilder.and(predicados, predicadoResponsavelAtivo);

		}else {
			
			Predicate predicadoResponsavelAtivo = (Predicate) criteriaBuilder.isNotNull(data);

			predicados = criteriaBuilder.and(predicados, predicadoResponsavelAtivo);
			
		}
		
		if (!empreendimento.getNomeFantasia().trim().isEmpty()) {

			Path<String> nomeFantasia = root.get(Empreendimento_.nomeFantasia);
			
			Predicate predicadoNomeFantasia = (Predicate) criteriaBuilder.like(nomeFantasia, "%"+empreendimento.getNomeFantasia()+"%");

			predicados = criteriaBuilder.and(predicados, predicadoNomeFantasia);

		}
		
		if (empreendimento.getTipoEmpreendimento().name()!=null && !empreendimento.getTipoEmpreendimento().name().equals("Selecione um empreendimento")) {

			Path<String> nomeFantasia = root.get(Empreendimento_.nomeFantasia);
			
			Predicate predicadoNomeFantasia = (Predicate) criteriaBuilder.like(nomeFantasia, "%"+empreendimento.getNomeFantasia()+"%");

			predicados = criteriaBuilder.and(predicados, predicadoNomeFantasia);

		}		
		
		

		return predicados;
	}

}
