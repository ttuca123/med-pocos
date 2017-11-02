package br.com.med.pocos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converter.CnpjConverter")
public class CnpjConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {
		
		String cnpj = valor;
		
		if(valor!= null && !valor.isEmpty()) {
			
			cnpj = valor.replaceAll("\\.", "").replaceAll("\\-", "").replaceAll("\\/", "");
			
		}		
		
		return cnpj;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		
		String cnpj = (String) value;
		
		if(cnpj !=null && cnpj.length()==11) {
			
			cnpj = cnpj.substring(0, 2) + "." + cnpj.substring(2,5)+ "."+ cnpj.substring(5, 8)+"/"+cnpj.substring(8,12)+"-"+cnpj.substring(12,14);
			
		}
		
		return cnpj;
	}

}
