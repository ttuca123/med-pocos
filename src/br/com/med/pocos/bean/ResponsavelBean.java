package br.com.med.pocos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.med.pocos.model.Responsavel;
import br.com.med.pocos.services.ResponsavelService;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "responsavelBean")
@ViewScoped
public class ResponsavelBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = -4970294226807286353L;

	private Responsavel responsavel = new Responsavel();

	private boolean todos;

	@ManagedProperty(value = "#{responsaveis}")
	private List<Responsavel> responsaveis;

	private List<Responsavel> filteredResponsaveis;

	@EJB
	private ResponsavelService responsavelService;

	public void novo() {

		responsavel = new Responsavel();
		todos = true;

	}

	@PostConstruct
	public void inicializar() {

		novo();

	}

	public boolean isEditavel() {

		return responsavel.getDataEncerramentoContrato() == null ? true : false;

	}

	public void salvar() throws Exception {

		try {

			responsavelService.salvar(responsavel);

			Utils.addMessage(Utils.getMensagem(MSG_SAVE_SUCESSO));

			filtrar();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem(MSG_SAVE_ERRO));

			String erro = logThrowable(e);

			log.error(erro);

			enviarEmailErro(erro);

		} finally {

			novo();
		}

	}

	public void excluir(ActionEvent actionEvent) throws Exception {

		try {

			responsavelService.deletar(responsavel);

			Utils.addMessage(Utils.getMensagem("page.cadastro.excluir.sucesso"));

			filtrar();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem(MSG_EXCLUIDO_ERRO));

			String erro = logThrowable(e);

			log.error(erro);

			enviarEmailErro(erro);

		}
	}

	public int getTotal() {

		return responsaveis == null ? 0 : responsaveis.size();

	}

	public void filtrar() {

		if (todos) {

			responsaveis = (List<Responsavel>) responsavelService.listar();

		} else {

			responsaveis = (List<Responsavel>) responsavelService.listar(responsavel);

		}

	}
	
	public List<Responsavel> getListarResponsaveisAtivos() {
		
		return responsavelService.listarResponsaveisAtivos();
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

}
