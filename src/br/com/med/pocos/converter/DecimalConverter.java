package br.com.med.pocos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
/**
 * 
 * @author Artur
 *
 */

@FacesConverter("converter.DecimalConverter")
public class DecimalConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		String valor = value;
		
		if(value!= null && !value.isEmpty()) {
			valor = value.replaceAll("\\,", ".");
		}		
		
		return valor;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		Double valor = (Double) value;
		
		return Double.toString(valor).replaceAll("\\.", ",");
		
		
	}

}
