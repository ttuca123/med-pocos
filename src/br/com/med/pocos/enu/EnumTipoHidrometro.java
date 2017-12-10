package br.com.med.pocos.enu;

import java.util.ArrayList;
import java.util.List;

public enum EnumTipoHidrometro {

	NENHUM(0, "Sem Classificação"), POCO(1, "Poço"), PIVO(2, "Pivo");

	private Integer codigo;
	
	private String descricao;
	

	private EnumTipoHidrometro(Integer codigo, String descricao) {
		this.codigo = codigo;	
		this.descricao = descricao;
	}

	public static List<String> getLstTipoHidrometros() {
		List<String> tipoHidrometros = null;
		
		if (tipoHidrometros == null) {

			tipoHidrometros = new ArrayList<String>();

			for (EnumTipoHidrometro enuTipoHidrometro : EnumTipoHidrometro.values()) {
				
				tipoHidrometros.add(enuTipoHidrometro.getDescricao());
			}
		}

		return tipoHidrometros;
	}

	public static String getDescricao(int codigo) {

		for (EnumTipoHidrometro enuTipoHidrometro : EnumTipoHidrometro.values()) {
			if (codigo == enuTipoHidrometro.getCodigo()) {
				return enuTipoHidrometro.getDescricao();
			}

		}
		return "Ops, nenhum item encontrado!!!";
	}

	public static Integer getCodigo(String descricao) {

		for (EnumTipoHidrometro enuTipoHidrometro : EnumTipoHidrometro.values()) {
			if (enuTipoHidrometro.getDescricao().equals(descricao.trim())) {
				return enuTipoHidrometro.getCodigo();
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
