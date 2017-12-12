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
import br.com.med.pocos.exception.RegistroDuplicadoException;
import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Empreendimento_;
import br.com.med.pocos.model.Hidrometro;
import br.com.med.pocos.model.Hidrometro_;
import br.com.med.pocos.model.RazaoSocial;
import br.com.med.pocos.model.RazaoSocial_;

@Stateless(name = "hidrometroService")
public class HidrometroServiceImpl implements HidrometroService {

	@EJB
	public EntityManagerService emService;
	
	private CriteriaBuilder criteriaBuilder;

	private Root<Hidrometro> rootHidrometro;

	@Override
	public void salvar(Object object) throws Exception {

		Hidrometro hidrometro = (Hidrometro) object;

		if (hidrometro != null) {

			if(!hidrometro.getRegistro().isEmpty()) {
				
				List<Hidrometro> duplicados =  emService.getEntityManager().createNamedQuery("Hidrometro.buscaHidrometrosDuplicados").setParameter("registro", hidrometro.getRegistro())
				.getResultList();
				
				if(duplicados!=null && duplicados.size()>0) {
					throw new RegistroDuplicadoException();
					
				}
			}
			
			if (hidrometro.getSeqHidrometro() == null) {
				
				emService.getEntityManager().persist(hidrometro);

			} else {

				editar(hidrometro);
			}
		} else {
			throw new Exception();
		}

	}

	private void editar(Hidrometro hidrometro) {

		
		emService.getEntityManager().merge(hidrometro);
		

	}

	@Override
	public Object getObject(Long seqId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Object object) {

		Hidrometro hidrometro = (Hidrometro) object;
		
		hidrometro.setEmpreendimento(null);

		hidrometro.setAtivo(false);

		emService.getEntityManager().merge(hidrometro);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hidrometro> listarTodos() {

		List<Hidrometro> hidrometros;

		try {
			hidrometros = emService.getEntityManager().createNamedQuery("Hidrometro.buscaAllHidrometros")
					.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hidrometros = new ArrayList<Hidrometro>();
		}

		return hidrometros;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hidrometro> listarAtivos() {

		List<Hidrometro> hidrometros;

		try {
			hidrometros = emService.getEntityManager().createNamedQuery("Hidrometro.buscaHidrometrosAtivos")
					.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hidrometros = new ArrayList<Hidrometro>();
		}

		return hidrometros;
	}

	@Override
	public List<Hidrometro> listar(Hidrometro hidrometro) {

		List<Hidrometro> hidrometros = new ArrayList<Hidrometro>();

		hidrometros = verificarFiltros(hidrometro);
		
		return hidrometros;
	}
	
	
	private List<Hidrometro> verificarFiltros(Hidrometro hidrometro) {

		criteriaBuilder = emService.getEntityManager().getCriteriaBuilder();

		CriteriaQuery<Hidrometro> query = criteriaBuilder.createQuery(Hidrometro.class);

		rootHidrometro = query.from(Hidrometro.class);

		Predicate predicados = adicionarFiltros(hidrometro);

		Path<String> registro = rootHidrometro.get(Hidrometro_.registro);

		query.select(rootHidrometro).where(predicados).orderBy(criteriaBuilder.asc(registro));

		TypedQuery<Hidrometro> resultado = emService.getEntityManager().createQuery(query);

		return resultado.getResultList();

	}
	
	
	private Predicate adicionarFiltros(Hidrometro hidrometro) {

		Predicate predicados = criteriaBuilder.and();

		if (hidrometro.getRegistro() != null && !hidrometro.getRegistro().trim().isEmpty()) {

			Path<String> registro = rootHidrometro.get(Hidrometro_.registro);

			Predicate predicadoRegistro = (Predicate) criteriaBuilder.like(registro,
					hidrometro.getRegistro());

			predicados = criteriaBuilder.and(predicados, predicadoRegistro);

		}		

		if (hidrometro.getTipoHidrometro() != null && hidrometro.getTipoHidrometro().getCodigo() != 0) {

			Path<EnumTipoHidrometro> tipoHidrometro = rootHidrometro
					.get(Hidrometro_.tipoHidrometro);

			Predicate predicadoTipoHidrometro = (Predicate) criteriaBuilder.equal(tipoHidrometro,
					hidrometro.getTipoHidrometro());

			predicados = criteriaBuilder.and(predicados, predicadoTipoHidrometro);

		}

		
		Path<Boolean> ativo = rootHidrometro.get(Hidrometro_.isAtivo);

		Predicate predicadoAtivo = (Predicate) criteriaBuilder.isTrue(ativo);

		predicados = criteriaBuilder.and(predicados, predicadoAtivo);	
		

		return predicados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hidrometro> listarSemEmpreendimentoAssociado() {
		List<Hidrometro> hidrometros;

		try {
			
			hidrometros = emService.getEntityManager().createNamedQuery("Hidrometro.buscaHidrometrosSemEmpreendimento")
					.getResultList();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hidrometros = new ArrayList<Hidrometro>();
		}

		return hidrometros;
	}

}
