package br.com.med.pocos.converter;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import br.com.med.pocos.enu.EnumPermissao;

@FacesConverter(value = "converter.PermissaoConverter")
public class PermissaoConverter extends EnumConverter {

	public PermissaoConverter() {
		
		super(EnumPermissao.class);
		
	}
}
