package br.com.med.pocos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.med.pocos.enu.EnumTipoEmpreendimento;
import br.com.med.pocos.model.Responsavel;
import br.com.med.pocos.services.ResponsavelService;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "responsavelBean")
@ViewScoped
public class ResponsavelBean implements Serializable {

	private static final long serialVersionUID = -4970294226807286353L;
	
	private Responsavel responsavel;
	
	private boolean todos;

	
	private List<String> lstEmpreendimentos;
	
	@ManagedProperty(value = "#{responsaveis}")
	private List<Responsavel> responsaveis = new ArrayList<Responsavel>();

	private List<Responsavel> filteredResponsaveis;

	@EJB
	private ResponsavelService responsavelService;

	public void novo() {

		responsavel = new Responsavel();
		responsavel.setAtivo(true);

	}

	

	@PostConstruct
	public void inicializar() {

		novo();
//		getListar();
		

	}

	public boolean isEditavel() {
		
		if(responsavel.getDataEncerramentoContrato()== null) {			
			return true;
		}else {
			return false;
		}
	}
	
	public void salvar() {

		try {
			responsavelService.salvar(responsavel);

			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.sucesso"));

			getListar();
		} catch (Exception e) {
			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.erro"));
		} finally {
			novo();
		}

	}
	
	
	

	


	public void excluir(ActionEvent actionEvent) {

		try {

			responsavelService.deletar(responsavel);

			Utils.addMessage(Utils.getMensagem("page.cadastro.excluir.sucesso"));

			getListar();
		} catch (Exception e) {
			Utils.addMessageException(Utils.getMensagem("page.cadastro.excluir.erro"));
		}
	}

	public void getListar() {

		try {
			
			responsaveis = (List<Responsavel>) responsavelService.listar();
		
		} catch (Exception e) {
			
			Utils.addMessageException(Utils.getMensagem("page.cadastro.listar.erro"));
		}
		
	}

	public void filtrar() {	
		
		if(todos) {
			getListar();
		}else {
		
			responsaveis = (List<Responsavel>) responsavelService.listar(responsavel);
					
		}
	}


	public List<Responsavel> getresponsaveis() {
		return responsaveis;
	}

	public void setresponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}	

	public List<Responsavel> getFilteredResponsaveis() {
		return filteredResponsaveis;
	}

	public void setFilteredResponsaveis(List<Responsavel> filteredresponsaveis) {
		this.filteredResponsaveis = filteredresponsaveis;
	}

	public void addMessage(String summary) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessageException(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public ResponsavelService getResponsavelService() {
		return responsavelService;
	}

	public void setResponsavelService(ResponsavelService responsavelService) {
		this.responsavelService = responsavelService;
	}
	
	public boolean isTodos() {
		return todos;
	}



	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public List<String> getLstEmpreendimentos() {
		
		lstEmpreendimentos = EnumTipoEmpreendimento.getLstTipoEmpreendimentos();
		
		return lstEmpreendimentos;
	}



	public void setLstEmpreendimentos(List<String> lstEmpreendimentos) {
		this.lstEmpreendimentos = lstEmpreendimentos;
	}


}
