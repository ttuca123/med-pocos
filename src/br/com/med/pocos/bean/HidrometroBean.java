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

import br.com.med.pocos.enu.EnumTipoHidrometro;
import br.com.med.pocos.exception.RegistroDuplicadoException;
import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Hidrometro;
import br.com.med.pocos.services.EmpreendimentoService;
import br.com.med.pocos.services.HidrometroService;
import br.com.med.pocos.util.Utils;

@ManagedBean(name = "hidrometroBean")
@ViewScoped
public class HidrometroBean implements Serializable {

	private static final long serialVersionUID = -603503157967797802L;

	private Hidrometro hidrometro;

	@ManagedProperty(value = "#{hidrometros}")
	private List<Hidrometro> hidrometros = new ArrayList<Hidrometro>();

	private List<Hidrometro> filteredHidrometros;

	@EJB
	private HidrometroService hidrometroService;

	@EJB
	private EmpreendimentoService empreendimentoService;

	@PostConstruct
	public void inicializar() {

		novo();
	}

	public void novo() {

		hidrometro = new Hidrometro();
		hidrometro.setAtivo(true);

	}

	public int getTotal() {

		if (hidrometros != null) {

			return hidrometros.size();

		} else {

			return 0;
		}

	}

	public boolean isEditavel() {

		return hidrometro.isAtivo();
	}

	public void salvar() throws Exception {

		try {

			hidrometroService.salvar(hidrometro);

			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.sucesso"));

			getListarAtivos();

		}catch(RegistroDuplicadoException rd) {
			
			Utils.addMessage(Utils.getMensagem("page.cadastro.salvar.duplicado"));
		
		} finally {
			
			novo();
			
		}

	}

	public void getListarTodos() {

		try {

			hidrometros = (List<Hidrometro>) hidrometroService.listarTodos();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem("page.cadastro.listar.erro"));
		}

	}

	public void getListarAtivos() {

		try {

			hidrometros = (List<Hidrometro>) hidrometroService.listarAtivos();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem("page.cadastro.listar.erro"));
		}

	}

	public void filtrar() {

		 hidrometros = (List<Hidrometro>) hidrometroService.listar(hidrometro);

		//hidrometros = (List<Hidrometro>) hidrometroService.listarAtivos();

	}

	public void excluir(ActionEvent actionEvent) {

		try {

			hidrometroService.deletar(hidrometro);

			Utils.addMessage(Utils.getMensagem("page.cadastro.excluir.sucesso"));

			getListarAtivos();

		} catch (Exception e) {

			Utils.addMessageException(Utils.getMensagem("page.cadastro.excluir.erro"));

		}
	}

	public EnumTipoHidrometro[] getTiposHidrometros() {

		EnumTipoHidrometro[] enuTiposHidrometro = EnumTipoHidrometro.values();

		return enuTiposHidrometro;
	}

	public List<Empreendimento> getListaEmpreendimentosAtivos() {

		return empreendimentoService.listar();

	}

	public Hidrometro getHidrometro() {
		return hidrometro;
	}

	public void setHidrometro(Hidrometro hidrometro) {
		this.hidrometro = hidrometro;
	}

	public List<Hidrometro> getHidrometros() {
		return hidrometros;
	}

	public void setHidrometros(List<Hidrometro> hidrometros) {
		this.hidrometros = hidrometros;
	}

	public List<Hidrometro> getFilteredHidrometros() {
		return filteredHidrometros;
	}

	public void setFilteredHidrometros(List<Hidrometro> filteredHidrometros) {
		this.filteredHidrometros = filteredHidrometros;
	}

}