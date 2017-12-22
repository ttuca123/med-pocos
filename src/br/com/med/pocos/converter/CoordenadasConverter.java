package br.com.med.pocos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converter.CoordenadasConverter")
public class CoordenadasConverter implements Converter {

	

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		

		return converter((Character) value);
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String converter (Character valor) {
		
		
		switch (valor) {
		case 'N':
			
			return "Norte";
		case 'S':
			
		return "Sul";
		case 'L':
			
			return "Leste";
		case 'O':
			
		return "Oeste";		

		default:
			break;
		}
		
		return null;
		
	}

}
