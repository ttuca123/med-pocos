package br.com.med.pocos.enu;

public enum EnumAmbiente {

	DESENVOLVIMENTO(0, "http://localhost:8180"), TESTE(1, "http://www.med-pocos.com.br"), PRODUCAO(2,
			"http://www.med-pocos.com.br");

	private Integer codigo;

	private String descricao;

	private EnumAmbiente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static String getDescricao(int codigo) {

		for (EnumAmbiente enuTipoAmbiente : EnumAmbiente.values()) {
			if (codigo == enuTipoAmbiente.getCodigo()) {
				return enuTipoAmbiente.getDescricao();
			}

		}
		return "Ops, nenhum item encontrado!!!";
	}

	public static Integer getCodigo(String descricao) {

		for (EnumAmbiente enuTipoAmbiente : EnumAmbiente.values()) {
			if (enuTipoAmbiente.getDescricao().equals(descricao.trim())) {
				return enuTipoAmbiente.getCodigo();
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
