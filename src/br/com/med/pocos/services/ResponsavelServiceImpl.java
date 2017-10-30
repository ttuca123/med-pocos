package br.com.med.pocos.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.med.pocos.model.Responsavel;
import br.com.med.pocos.util.DataUtils;

/**
 * Classe responsável pela manutenção dos responsáveis dos empreendimentos
 * @author Artur
 *
 */

@Stateless(name = "ResponsavelService")
public class ResponsavelServiceImpl implements ResponsavelService {

	@EJB
	public EntityManagerService emService;	

	@Override
	public void salvar(Object object) throws Exception {		 
		
		Responsavel	responsavel = (Responsavel) object;

			if(responsavel!=null) {
				if (responsavel.getSeqResponsavel() == null) {
						
					Date data = DataUtils.converterDataTimeZone();
					
					responsavel.setDataCadastro(data);
	
					emService.getEntityManager().persist(object);
	
				} else {
					editar(responsavel);
					
				}
			}else {
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
			responsaveis = emService.getEntityManager().createNamedQuery("Responsavel.buscaResponsaveis").getResultList();
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
		
		Query query;
		
		try {
			if(responsavel.isAtivo()==true) {
				
				query = emService.getEntityManager().createNamedQuery("Responsavel.buscaResponsaveisAtivos");
				
				responsaveis = (List<Responsavel>) query.getResultList();								
			}else {
				
				query = emService.getEntityManager().createNamedQuery("Responsavel.buscaResponsaveisInativos");
				
				responsaveis = (List<Responsavel>) query.getResultList();		
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		return responsaveis;
	}
	
	
	
	public List<Responsavel> listarResponsaveis() {
		
		
		
		return new ArrayList<Responsavel>();
	}
	

}
