package br.com.med.pocos.enu;

import java.util.ArrayList;
import java.util.List;

public enum EnumMes {

	JANEIRO(0, "Janeiro"), FEVEREIRO(1, "Fevereiro"), MARCO(2, "Março"),
	ABRIL(3, "Abril"), MAIO(4, "Maio"), JUNHO(5, "Junho"),
	JULHO(6, "Julho"), AGOSTO(7, "Agosto"), SETEMBRO(8, "Setembro"),
	OUTUBRO(9, "Outubro"), NOVEMBRO(10, "Novembro"), DEZEMBRO(11, "Dezembro");

	private Integer codigo;
	
	private String descricao;
	

	private EnumMes(Integer codigo, String descricao) {
		this.codigo = codigo;	
		this.descricao = descricao;
	}

	public static List<String> getLstTipoEmpreendimentos() {
		List<String> tiposEmpreendimentos = null;
		
		if (tiposEmpreendimentos == null) {

			tiposEmpreendimentos = new ArrayList<String>();

			for (EnumMes enuTipoEmpreendimento : EnumMes.values()) {
				
				tiposEmpreendimentos.add(enuTipoEmpreendimento.getDescricao());
			}
		}

		return tiposEmpreendimentos;
	}

	public static String getDescricao(int codigo) {

		for (EnumMes enuTipoEmpreendimento : EnumMes.values()) {
			if (codigo == enuTipoEmpreendimento.getCodigo()) {
				return enuTipoEmpreendimento.getDescricao();
			}

		}
		return "Ops, nenhum item encontrado!!!";
	}

	public static Integer getCodigo(String descricao) {

		for (EnumMes enuTipoEmpreendimento : EnumMes.values()) {
			if (enuTipoEmpreendimento.getDescricao().equals(descricao.trim())) {
				return enuTipoEmpreendimento.getCodigo();
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
