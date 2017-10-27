package br.com.med.pocos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.validator.FacesValidator;

@FacesConverter("converter.CpfConverter")
public class CpfConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		String cpf = value;
		
		if(value!= null && !value.isEmpty()) {
			cpf = value.replaceAll("\\.", "").replaceAll("\\-", "");
		}		
		
		return cpf;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		String cpf = (String) value;
		
		if(cpf !=null && cpf.length()==11) {
			
			cpf = cpf.substring(0, 3) + "." + cpf.substring(3,6)+ "."+ cpf.substring(6, 9)+"-"+cpf.substring(9,11);
			
		}
		
		return cpf;
	}

}
