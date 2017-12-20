package br.com.med.pocos.converter;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import br.com.med.pocos.enu.EnumTipoPoco;

@FacesConverter(value="converter.TipoPocoConverter")
public class TipoPocoConverter extends EnumConverter{

	 public TipoPocoConverter() {
	        super(EnumTipoPoco.class);
	    }
}
