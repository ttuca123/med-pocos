package br.com.med.pocos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converter.FoneConverter")
public class FoneConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		String fone = value;
		
		if(value!= null && !value.isEmpty()) {
			fone = value.replaceAll("\\(", "").replaceAll("\\)", ""	).replaceAll("\\-", "");
		}		
		
		return fone;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		String fone = (String) value;
		
		if(fone !=null && fone.length()==11) {
			
			fone = "("+fone.substring(0, 3) + ")" + fone.substring(3,8)+ "-"+ fone.substring(8, 12);
			
		}
		
		return fone;
	}

}
