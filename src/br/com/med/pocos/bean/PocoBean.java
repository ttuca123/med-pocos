package br.com.med.pocos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.med.pocos.enu.EnumTipoPoco;
import br.com.med.pocos.exception.RegistroDuplicadoException;
import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Hidrometro;
import br.com.med.pocos.model.Poco;
import br.com.med.pocos.services.EmpreendimentoService;
import br.com.med.pocos.services.PocoService;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "pocoBean")
@ViewScoped
public class PocoBean implements Serializable {

	private static final long serialVersionUID = -603503157967797802L;

	private Poco poco;
	
	private Empreendimento empreendimento;

	@ManagedProperty(value = "#{pocos}")
	private List<Poco> pocos = new ArrayList<Poco>();

	private List<Poco> filteredPocos;

	@EJB
	private PocoService pocoService;

	@EJB
	private EmpreendimentoService empreendimentoService;

	@PostConstruct
	public void inicializar() {

		novo();
	}

	public void novo() {
		
		poco = new Poco();		
		poco.setAtivo(true);

	}

	public int getTotal() {

		if (pocos != null) {

			return pocos.size();

		} else {

			return 0;
		}

	}

	public boolean isEditavel() {

		return poco.isAtivo();
	}

	public void salvar() throws Exception {

		try {

			pocoService.salvar(poco);

			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.sucesso"));

			filtrar();

		}catch(RegistroDuplicadoException rd) {
			
			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.duplicado"));
		
		} finally {
			
			novo();
			
		}

	}

	

	public void filtrar() {
		try {

			pocos = (List<Poco>) pocoService.listarTodosByEmpreendimento(empreendimento);

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem("page.cadastro.listar.erro"));
		}


	}

	public void excluir(ActionEvent actionEvent) {

		try {

			pocoService.deletar(poco);

			Utils.addMessage(Utils.getMensagem("page.cadastro.excluir.sucesso"));

			filtrar();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem("page.cadastro.excluir.erro"));

		}
	}

	public EnumTipoPoco[] getTiposPocos() {

		EnumTipoPoco[] enuTiposPoco = EnumTipoPoco.values();

		return enuTiposPoco;
	}

	public Poco getPoco() {
		return poco;
	}

	public void setPoco(Poco poco) {
		this.poco = poco;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	public List<Poco> getPocos() {
		return pocos;
	}

	public void setPocos(List<Poco> pocos) {
		this.pocos = pocos;
	}

	public List<Poco> getFilteredPocos() {
		return filteredPocos;
	}

	public void setFilteredPocos(List<Poco> filteredPocos) {
		this.filteredPocos = filteredPocos;
	}

	

	
}