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

import br.com.med.pocos.enu.EnumTipoEmpreendimento;
import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Empreendimento_;
import br.com.med.pocos.model.Hidrometro;
import br.com.med.pocos.model.RazaoSocial;
import br.com.med.pocos.model.RazaoSocial_;
import br.com.med.pocos.model.Responsavel;
import br.com.med.pocos.util.DataUtils;

@Stateless(name = "EmpreendimentoService")
public class EmpreendimentoServiceImpl implements EmpreendimentoService {

	private CriteriaBuilder criteriaBuilder;

	private Root<Empreendimento> rootEmpreendimento;
	
	@EJB
	public EntityManagerService emService;

	@EJB
	public ResponsavelService responsavelService;

	@Override
	public void salvar(Object object) throws Exception {

		Empreendimento empreendimento = (Empreendimento) object;
		Responsavel responsavel = empreendimento.getResponsavel();

		if (responsavel.getSeqResponsavel() != null) {

			empreendimento.setResponsavel(responsavel);

			emService.getEntityManager().detach(responsavel);
		}

		if (empreendimento.getSeqEmpreendimento() == null) {

			Date data = DataUtils.converterDataTimeZone();

			empreendimento.setDataCadastro(data);
			
			empreendimento.setAtivo(true);

			emService.getEntityManager().persist(object);

		} else {
			emService.getEntityManager().merge(empreendimento);
		}

	}

	@Override
	public Empreendimento getObject(Long seqId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Object object) {

		Empreendimento empreendimento = (Empreendimento) object;

		Date data = DataUtils.converterDataTimeZone();

		empreendimento.setDataEncerramento(data);

		emService.getEntityManager().merge(empreendimento);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empreendimento> listar() {
		List<Empreendimento> empreendimentos = new ArrayList<Empreendimento>();

		empreendimentos = emService.getEntityManager().createNamedQuery("Empreendimento.buscaAllEmpreendimentos")
				.getResultList();

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

		rootEmpreendimento = query.from(Empreendimento.class);

		Predicate predicados = adicionarFiltros(empreendimento);

		Path<String> nome = rootEmpreendimento.get(Empreendimento_.nomeFantasia);

		query.select(rootEmpreendimento).where(predicados).orderBy(criteriaBuilder.asc(nome));

		TypedQuery<Empreendimento> resultado = emService.getEntityManager().createQuery(query);

		return resultado.getResultList();

	}

	private Predicate adicionarFiltros(Empreendimento empreendimento) {

		Predicate predicados = criteriaBuilder.and();

		if (empreendimento.getRazaoSocial().getCpf() != null
				&& !empreendimento.getRazaoSocial().getCpf().trim().isEmpty()) {

			Path<RazaoSocial> razaoSocial = rootEmpreendimento.get(Empreendimento_.razaoSocial);

			Predicate predicadoCpf = (Predicate) criteriaBuilder.like(razaoSocial.get(RazaoSocial_.cpf),
					empreendimento.getRazaoSocial().getCpf());

			predicados = criteriaBuilder.and(predicados, predicadoCpf);

		}

		if (empreendimento.getRazaoSocial().getCnpj() != null
				&& !empreendimento.getRazaoSocial().getCnpj().trim().isEmpty()) {

			Path<RazaoSocial> razaoSocial = rootEmpreendimento.get(Empreendimento_.razaoSocial);

			Predicate predicadoCnpj = (Predicate) criteriaBuilder.like(razaoSocial.get(RazaoSocial_.cnpj),
					empreendimento.getRazaoSocial().getCnpj());

			predicados = criteriaBuilder.and(predicados, predicadoCnpj);

		}

		if (empreendimento.getTipoEmpreendimento() != null && empreendimento.getTipoEmpreendimento().getCodigo() != 0) {

			Path<EnumTipoEmpreendimento> tipoEmpreendimento = rootEmpreendimento
					.get(Empreendimento_.tipoEmpreendimento);

			Predicate predicadoTipoEmpreendimento = (Predicate) criteriaBuilder.equal(tipoEmpreendimento,
					empreendimento.getTipoEmpreendimento());

			predicados = criteriaBuilder.and(predicados, predicadoTipoEmpreendimento);

		}

		if (empreendimento.getNomeFantasia() != null && !empreendimento.getNomeFantasia().trim().isEmpty()) {

			Path<String> nomeFantasia = rootEmpreendimento.get(Empreendimento_.nomeFantasia);

			Predicate predicadoNomeFantasia = (Predicate) criteriaBuilder.like(nomeFantasia,
					"%" + empreendimento.getNomeFantasia() + "%");

			predicados = criteriaBuilder.and(predicados, predicadoNomeFantasia);

		}

		if (empreendimento.getRazaoSocial().getRazaoSocial() != null
				&& !empreendimento.getRazaoSocial().getRazaoSocial().trim().isEmpty()) {

			Path<RazaoSocial> razaoSocial = rootEmpreendimento.get(Empreendimento_.razaoSocial);

			Predicate predicadoRazaoSocial = (Predicate) criteriaBuilder.like(razaoSocial.get(RazaoSocial_.razaoSocial),
					"%" + empreendimento.getRazaoSocial().getRazaoSocial() + "%");

			predicados = criteriaBuilder.and(predicados, predicadoRazaoSocial);

		}

		return predicados;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Hidrometro> listarHidrometrosByEmpreendimento(Empreendimento empreendimento) {

		List<Hidrometro> hidrometros;

		hidrometros = emService.getEntityManager().createNamedQuery("Hidrometro.buscaHidrometrosByEmpreendimento")
				.setParameter("seqEmpreendimento", empreendimento.getSeqEmpreendimento()).getResultList();

		return hidrometros;
	}

}
