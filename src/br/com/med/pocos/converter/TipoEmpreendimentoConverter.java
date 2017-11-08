package br.com.med.pocos.converter;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import br.com.med.pocos.enu.EnumTipoEmpreendimento;

@FacesConverter(value="converter.TipoEmpreendimentoConverter")
public class TipoEmpreendimentoConverter extends EnumConverter{

	 public TipoEmpreendimentoConverter() {
	        super(EnumTipoEmpreendimento.class);
	    }
}
