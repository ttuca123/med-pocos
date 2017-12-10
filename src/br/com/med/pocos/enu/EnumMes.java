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

	public static List<String> getLstMeses() {
		List<String> tiposMeses = null;
		
		if (tiposMeses == null) {

			tiposMeses = new ArrayList<String>();

			for (EnumMes enuMes : EnumMes.values()) {
				
				tiposMeses.add(enuMes.getDescricao());
			}
		}

		return tiposMeses;
	}

	public static String getDescricao(int codigo) {

		for (EnumMes enuTipoMes : EnumMes.values()) {
			if (codigo == enuTipoMes.getCodigo()) {
				return enuTipoMes.getDescricao();
			}

		}
		return "Ops, nenhum item encontrado!!!";
	}

	public static Integer getCodigo(String descricao) {

		for (EnumMes enuTipoMes : EnumMes.values()) {
			if (enuTipoMes.getDescricao().equals(descricao.trim())) {
				return enuTipoMes.getCodigo();
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
