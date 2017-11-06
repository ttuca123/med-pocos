package br.com.med.pocos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Responsavel;
import br.com.med.pocos.services.EmpreendimentoService;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "empreendimentoBean")
@ViewScoped
public class EmpreendimentoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7436327849003146777L;

	private Empreendimento empreendimento;

	private boolean todos;

	private List<Empreendimento> lstEmpreendimentos = new ArrayList<Empreendimento>();

	@EJB
	private EmpreendimentoService empreendimentoService;

	@PostConstruct
	public void inicializar() {

		empreendimento = new Empreendimento();

		empreendimento.setAtivo(true);
	}
	
	

	public void novo() {

		empreendimento = new Empreendimento();
		empreendimento.setAtivo(true);

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
			
			lstEmpreendimentos = (List<Empreendimento>) empreendimentoService.listar();
		
		} catch (Exception e) {
			
			Utils.addMessageException(Utils.getMensagem("page.cadastro.listar.erro"));
		}
		
	}

	public void filtrar() {	
		
		if(todos) {
			getListar();
		}else {
		
			lstEmpreendimentos = (List<Empreendimento>) empreendimentoService.listar(empreendimento);
					
		}
	}
	
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	public boolean isTodos() {
		return todos;
	}

	public void setTodos(boolean todos) {
		this.todos = todos;
	}

	public List<Empreendimento> getLstEmpreendimentos() {
		return lstEmpreendimentos;
	}

	public void setLstEmpreendimentos(List<Empreendimento> lstEmpreendimentos) {
		this.lstEmpreendimentos = lstEmpreendimentos;
	}
}