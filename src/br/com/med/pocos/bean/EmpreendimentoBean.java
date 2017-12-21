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

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import br.com.med.pocos.enu.EnumTipoEmpreendimento;
import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Hidrometro;
import br.com.med.pocos.services.EmpreendimentoService;
import br.com.med.pocos.services.HidrometroService;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "empreendimentoBean")
@ViewScoped
public class EmpreendimentoBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = -7436327849003146777L;

	@EJB
	private EmpreendimentoService empreendimentoService;

	@EJB
	private HidrometroService hidrometroService;
	
	@ManagedProperty(value = "#{empreendimentos}")
	private List<Empreendimento> empreendimentos = new ArrayList<Empreendimento>();
	
	private DualListModel<Hidrometro> hidrometros;

	private List<Hidrometro> hidrometrosSelecionados;

	private List<Hidrometro> hidrometrosNaoSelecionados;

	private List<Empreendimento> filteredEmprendimentos;

	private List<Hidrometro> filteredHidrometros;
	
	private Empreendimento empreendimento;

	private boolean escolhaCpfCnpj;

	@PostConstruct
	public void inicializar() {

		novo();
	}

	public void novo() {

		empreendimento = new Empreendimento();	
		
	}

	public int getTotal() {

		if (empreendimentos != null) {

			return empreendimentos.size();
		} else {

			return 0;
		}

	}

	public int getTotalHidrometros() {

		if (empreendimento != null && empreendimento.getLstHidrometros() != null) {

			return empreendimento.getLstHidrometros().size();

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

	public void salvar() throws Exception {

		try {
			
			empreendimentoService.salvar(empreendimento);

			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.sucesso"));

			getListar();
		} catch (Exception e) {
			Utils.addMessageException(Utils.getMensagem(MSG_SAVE_ERRO));
			log.error(e.getMessage());
			enviarEmailErro(e.getMessage());
		} finally {
			novo();
		}

	}

	public void salvarHidrometros() throws Exception {

		hidrometrosSelecionados = hidrometros.getTarget();

		hidrometrosNaoSelecionados = hidrometros.getSource();

		try {
			
			salvarHidrometrosNaoSelecionados();
			
			salvarHidrometrosSelecionados();

			Utils.addMessage(Utils.getMensagem(MSG_SAVE_SUCESSO));

		} catch (Exception e) {
			
			Utils.addMessageException(Utils.getMensagem(MSG_SAVE_ERRO));
			log.error(e.getMessage());
			enviarEmailErro(e.getMessage());
			
		}

	}
	
	
	private void salvarHidrometrosSelecionados() throws Exception {
		
		for (Hidrometro hidrometro : hidrometrosSelecionados) {

			if (hidrometro.getEmpreendimento() == null) {

				hidrometro.setEmpreendimento(empreendimento);

				hidrometroService.salvar(hidrometro);

			}
		}
		
	}
	
	private void salvarHidrometrosNaoSelecionados() throws Exception {
		
		for (Hidrometro hidrometro : hidrometrosNaoSelecionados) {

			if (hidrometro.getEmpreendimento() != null) {

				hidrometro.setEmpreendimento(null);

				hidrometroService.salvar(hidrometro);

			}
		}
		
	}

	public void getListar() {

		try {

			empreendimentos = (List<Empreendimento>) empreendimentoService.listar();

		} catch (Exception e) {

			Utils.addMessageAviso(Utils.getMensagem(MSG_LISTAGEM_ERRO));
		}

	}

	public void filtrar() {

		empreendimentos = (List<Empreendimento>) empreendimentoService.listar(empreendimento);

	}

	public void excluir(ActionEvent actionEvent) throws Exception {

		try {

			empreendimentoService.deletar(empreendimento);

			Utils.addMessage(Utils.getMensagem("page.cadastro.excluir.sucesso"));

			getListar();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem(MSG_EXCLUIDO_ERRO));
			log.error(e.getMessage());
			enviarEmailErro(e.getMessage());

		}
	}

	public void onTransfer(TransferEvent event) {

		hidrometrosSelecionados = hidrometros.getTarget();

		empreendimento.setLstHidrometros(hidrometrosSelecionados);
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

		if (filteredHidrometros == null) {

			filteredHidrometros = new ArrayList<Hidrometro>();

		}

		return filteredHidrometros;
	}

	public void setFilteredHidrometros(List<Hidrometro> filteredHidrometros) {
		this.filteredHidrometros = filteredHidrometros;
	}

	public DualListModel<Hidrometro> getHidrometros() {

		if (empreendimento.getSeqEmpreendimento() != null) {

			empreendimento.setLstHidrometros(empreendimentoService.listarHidrometrosByEmpreendimento(empreendimento));

			hidrometros = new DualListModel<Hidrometro>(hidrometroService.listarSemEmpreendimentoAssociado(),
					empreendimento.getLstHidrometros());

		} else {

			hidrometros = new DualListModel<Hidrometro>(new ArrayList<Hidrometro>(), new ArrayList<Hidrometro>());

		}

		return hidrometros;
	}

	public void setHidrometros(DualListModel<Hidrometro> hidrometros) {
		this.hidrometros = hidrometros;
	}

	public List<Hidrometro> getHidrometrosSelecionados() {
		return hidrometrosSelecionados;
	}

	public void setHidrometrosSelecionados(List<Hidrometro> hidrometrosSelecionados) {
		this.hidrometrosSelecionados = hidrometrosSelecionados;
	}

	public List<Hidrometro> getHidrometrosNaoSelecionados() {
		return hidrometrosNaoSelecionados;
	}

	public void setHidrometrosNaoSelecionados(List<Hidrometro> hidrometrosNaoSelecionados) {
		this.hidrometrosNaoSelecionados = hidrometrosNaoSelecionados;
	}

}