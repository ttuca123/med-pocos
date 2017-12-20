package br.com.med.pocos.enu;

import java.util.ArrayList;
import java.util.List;

public enum EnumTipoPoco {

	NENHUM(0, "Sem Classificação"), TUBULAR(1, "Tubular"), ARTESIANO(2, "Artesiano");

	private Integer codigo;
	
	private String descricao;
	

	private EnumTipoPoco(Integer codigo, String descricao) {
		this.codigo = codigo;	
		this.descricao = descricao;
	}

	public static List<String> getLstTipoPocos() {
		List<String> tipoPocos = null;
		
		if (tipoPocos == null) {

			tipoPocos = new ArrayList<String>();

			for (EnumTipoPoco enuTipoPoco : EnumTipoPoco.values()) {
				
				tipoPocos.add(enuTipoPoco.getDescricao());
			}
		}

		return tipoPocos;
	}

	public static String getDescricao(int codigo) {

		for (EnumTipoPoco enuTipoPoco : EnumTipoPoco.values()) {
			if (codigo == enuTipoPoco.getCodigo()) {
				return enuTipoPoco.getDescricao();
			}

		}
		return "Ops, nenhum item encontrado!!!";
	}

	public static Integer getCodigo(String descricao) {

		for (EnumTipoPoco enuTipoPoco : EnumTipoPoco.values()) {
			if (enuTipoPoco.getDescricao().equals(descricao.trim())) {
				return enuTipoPoco.getCodigo();
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
