package br.com.med.pocos.enu;

import java.util.ArrayList;
import java.util.List;

public enum EnumPermissao {

	ADMIN(1, "Administrador"), RESPONSAVEL(2, "Responsável");

	private Integer codigo;
	
	private String descricao;
	

	private EnumPermissao(Integer codigo, String descricao) {
		this.codigo = codigo;	
		this.descricao = descricao;
	}

	public static List<String> getLstPermissoes() {
		
		List<String> permissoes = null;
		
		if (permissoes == null) {

			permissoes = new ArrayList<String>();

			for (EnumPermissao enuPermissao : EnumPermissao.values()) {
				
				permissoes.add(enuPermissao.getDescricao());
			}
		}

		return permissoes;
	}

	public static String getDescricao(int codigo) {

		for (EnumPermissao enuPermissao : EnumPermissao.values()) {
			if (codigo == enuPermissao.getCodigo()) {
				return enuPermissao.getDescricao();
			}

		}
		return "Ops, nenhum item encontrado!!!";
	}

	public static Integer getCodigo(String descricao) {

		for (EnumPermissao enuPermissao : EnumPermissao.values()) {
			
			if (enuPermissao.getDescricao().equals(descricao.trim())) {
				
				return enuPermissao.getCodigo();
				
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
