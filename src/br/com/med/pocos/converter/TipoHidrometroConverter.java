package br.com.med.pocos.converter;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

import br.com.med.pocos.enu.EnumTipoHidrometro;

@FacesConverter(value="converter.TipoHidrometroConverter")
public class TipoHidrometroConverter extends EnumConverter{

	 public TipoHidrometroConverter() {
	        super(EnumTipoHidrometro.class);
	    }
}
