package br.com.med.pocos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.med.pocos.model.Responsavel;

@FacesConverter("converter.ResponsavelConverter")
public class ResponsavelConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent uiComponent, String value) {
		
		Responsavel responsavel = new Responsavel();
		
		 if (value != null && !value.isEmpty()) {
			 	
			 responsavel = (Responsavel) uiComponent.getAttributes().get(value);
	            
			 	
	        }
	    return responsavel;
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent uiComponent, Object value) {
		
		 if (value instanceof Responsavel) {
			 Responsavel entity= (Responsavel) value;
	            if (entity != null && entity instanceof Responsavel && entity.getSeqResponsavel() != null) {
	                uiComponent.getAttributes().put( entity.getNome().toString(), entity);
	                return entity.getNome().toString();
	            }
	        }
	        return "";
	}

}
