package br.com.med.pocos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.med.pocos.model.Empreendimento;

@FacesConverter("converter.EmpreendimentoConverter")
public class EmpreendimentoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent uiComponent, String value) {
		
		Empreendimento empreendimento = new Empreendimento();
		
		 if (value != null && !value.isEmpty()) {
			 	
			 empreendimento = (Empreendimento) uiComponent.getAttributes().get(value);
	            
			 	
	        }
	    return empreendimento;
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent uiComponent, Object value) {
		
		 if (value instanceof Empreendimento) {
			 Empreendimento entity= (Empreendimento) value;
	            if (entity != null && entity instanceof Empreendimento && entity.getSeqEmpreendimento() != null) {
	                uiComponent.getAttributes().put( entity.getNomeFantasia().toString(), entity);
	                return entity.getNomeFantasia().toString();
	            }
	        }
	        return "";
	}

}
