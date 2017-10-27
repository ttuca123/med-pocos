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

import br.com.med.pocos.model.Responsavel;
import br.com.med.pocos.services.ResponsavelService;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "responsavelBean")
@ViewScoped
public class ResponsavelBean implements Serializable {

	private static final long serialVersionUID = -4970294226807286353L;

	private Responsavel responsavel = new Responsavel();

	@ManagedProperty(value = "#{responsaveis}")
	private List<Responsavel> responsaveis = new ArrayList<Responsavel>();
	
	
	private List<Responsavel> filteredResponsaveis;

	@EJB
	private ResponsavelService responsavelService;

	public void novo() {

		responsavel = new Responsavel();

		
	}

	@PostConstruct
	public void inicializar() {

		getListar();
		
	}

	public void salvar() {

		try {
			responsavelService.salvar(responsavel);

			Utils.addMessage("Cadastro realizado com sucesso");

			novo();
			
			getListar();
		} catch (Exception e) {
			Utils.addMessageException("Erro ao realizar cadastro");
		}

	}
	
	
	public void excluir(ActionEvent actionEvent) {

		try {

			responsavelService.deletar(responsavel);

			addMessage("Item excluido com sucesso!!");

		} catch (Exception e) {
			addMessageException("Ops, Erro ao excluir Responsavel!!!");
		} finally {
			getListar();
		}
	}

	public String getListar() {

		responsaveis = (List<Responsavel>) responsavelService.listar();

		return "listar_responsaveis";
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

	public List<String> getFilterResponsavel() {

		List<String> filtro = new ArrayList<String>();

		for (Responsavel Responsavel : responsaveis) {

			filtro.add(Responsavel.getNome());

		}

		return filtro;
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

}