package br.com.med.pocos.enu;

import java.util.ArrayList;
import java.util.List;

public enum EnumTipoMonitoramento {

	MANUAL(0, "Leitura Manual"), AUTOMATICA(1, "Leitura Automática");

	private Integer codigo;
	
	private String descricao;
	

	private EnumTipoMonitoramento(Integer codigo, String descricao) {
		this.codigo = codigo;	
		this.descricao = descricao;
	}

	public static List<String> getLstTipoMonitoramento() {
		List<String> tiposMonitoramento = null;
		
		if (tiposMonitoramento == null) {

			tiposMonitoramento = new ArrayList<String>();

			for (EnumTipoMonitoramento enuTipoMonitoramento : EnumTipoMonitoramento.values()) {
				
				tiposMonitoramento.add(enuTipoMonitoramento.getDescricao());
			}
		}

		return tiposMonitoramento;
	}

	public static String getDescricao(int codigo) {

		for (EnumTipoMonitoramento enuTipoMonitoramento : EnumTipoMonitoramento.values()) {
			if (codigo == enuTipoMonitoramento.getCodigo()) {
				return enuTipoMonitoramento.getDescricao();
			}

		}
		return "Ops, nenhum item encontrado!!!";
	}

	public static Integer getCodigo(String descricao) {

		for (EnumTipoMonitoramento enuTipoMonitoramento : EnumTipoMonitoramento.values()) {
			if (enuTipoMonitoramento.getDescricao().equals(descricao.trim())) {
				return enuTipoMonitoramento.getCodigo();
			}

		}
		return 0;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

}
