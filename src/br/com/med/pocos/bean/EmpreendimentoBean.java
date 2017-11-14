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

import org.primefaces.model.LazyDataModel;

import br.com.med.pocos.enu.EnumTipoEmpreendimento;
import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Hidrometro;
import br.com.med.pocos.services.EmpreendimentoService;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "empreendimentoBean")
@ViewScoped
public class EmpreendimentoBean implements Serializable {

	private static final long serialVersionUID = -7436327849003146777L;

	private Empreendimento empreendimento;

	@ManagedProperty(value = "#{empreendimentos}")
	private List<Empreendimento> empreendimentos = new ArrayList<Empreendimento>();
	
	
	

	private List<Empreendimento> filteredEmprendimentos;
	
	
	private List<Hidrometro> filteredHidrometros;

	private boolean escolhaCpfCnpj;

	@EJB
	private EmpreendimentoService empreendimentoService;

	@PostConstruct
	public void inicializar() {

		novo();
	}

	public void novo() {

		empreendimento = new Empreendimento();
		empreendimento.setAtivo(true);

	}

	public int getTotal() {
		if (empreendimentos != null) {

			return empreendimentos.size();
		} else {

			return 0;
		}

	}

	public boolean isEditavel() {

		if (empreendimento.getDataEncerramento() == null) {

			return true;

		} else {

			return false;
		}
	}

	public void salvar() {

		try {
			empreendimentoService.salvar(empreendimento);

			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.sucesso"));

			getListar();
		} catch (Exception e) {
			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.erro"));
		} finally {
			novo();
		}

	}

	public void getListar() {

		try {

			empreendimentos = (List<Empreendimento>) empreendimentoService.listar();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem("page.cadastro.listar.erro"));
		}

	}
	
	

	public void filtrar() {

		empreendimentos = (List<Empreendimento>) empreendimentoService.listar(empreendimento);

	}

	public void excluir(ActionEvent actionEvent) {

		try {

			empreendimentoService.deletar(empreendimento);

			Utils.addMessage(Utils.getMensagem("page.cadastro.excluir.sucesso"));

			getListar();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem("page.cadastro.excluir.erro"));

		}
	}

	public EnumTipoEmpreendimento[] getTiposEmpreendimentos() {

		EnumTipoEmpreendimento[] enuTiposEmpreendimento = EnumTipoEmpreendimento.values();

		return enuTiposEmpreendimento;
	}

	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	public List<Empreendimento> getEmpreendimentos() {
		return empreendimentos;
	}

	public void setEmpreendimentos(List<Empreendimento> empreendimentos) {
		this.empreendimentos = empreendimentos;
	}

	public List<Empreendimento> getFilteredEmprendimentos() {
		return filteredEmprendimentos;
	}

	public void setFilteredEmprendimentos(List<Empreendimento> filteredEmprendimentos) {
		this.filteredEmprendimentos = filteredEmprendimentos;
	}

	public boolean isEscolhaCpfCnpj() {
		return escolhaCpfCnpj;
	}

	public void setEscolhaCpfCnpj(boolean escolhaCpfCnpj) {
		this.escolhaCpfCnpj = escolhaCpfCnpj;
	}

	public List<Hidrometro> getFilteredHidrometros() {
		return filteredHidrometros;
	}

	public void setFilteredHidrometros(List<Hidrometro> filteredHidrometros) {
		this.filteredHidrometros = filteredHidrometros;
	}

}