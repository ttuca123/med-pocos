package br.com.med.pocos.enu;

import java.util.ArrayList;
import java.util.List;

public enum EnumTipoHidrometro {

	POCO(1, "Poço"), PIVO(2, "Pivo");

	private Integer codigo;
	
	private String descricao;
	

	private EnumTipoHidrometro(Integer codigo, String descricao) {
		this.codigo = codigo;	
		this.descricao = descricao;
	}

	public static List<String> getLstTipoEmpreendimentos() {
		List<String> tiposEmpreendimentos = null;
		
		if (tiposEmpreendimentos == null) {

			tiposEmpreendimentos = new ArrayList<String>();

			for (EnumTipoHidrometro enuTipoEmpreendimento : EnumTipoHidrometro.values()) {
				
				tiposEmpreendimentos.add(enuTipoEmpreendimento.getDescricao());
			}
		}

		return tiposEmpreendimentos;
	}

	public static String getDescricao(int codigo) {

		for (EnumTipoHidrometro enuTipoEmpreendimento : EnumTipoHidrometro.values()) {
			if (codigo == enuTipoEmpreendimento.getCodigo()) {
				return enuTipoEmpreendimento.getDescricao();
			}

		}
		return "Ops, nenhum item encontrado!!!";
	}

	public static Integer getCodigo(String descricao) {

		for (EnumTipoHidrometro enuTipoEmpreendimento : EnumTipoHidrometro.values()) {
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
