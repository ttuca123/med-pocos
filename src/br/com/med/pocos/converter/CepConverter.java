package br.com.med.pocos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converter.CepConverter")
public class CepConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		String cep = value;
		
		if(value!= null && !value.isEmpty()) {
			cep = value.replaceAll("\\-", "");
		}		
		
		return cep;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		String cep = (String) value;
		
		if(cep !=null && cep.length()==11) {
			
			cep = cep.substring(0, 6) + "-" + cep.substring(6,8);			
		
		}
		
		return cep;
	}

}
