package br.com.med.pocos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.med.pocos.model.Empreendimento;
import br.com.med.pocos.model.Hidrometro;

@FacesConverter("converter.HidrometroConverter")
public class HidrometroConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent uiComponent, String value) {
		
		Hidrometro hidrometro = new Hidrometro();
		
		 if (value != null && !value.isEmpty()) {
			 	
			 hidrometro = (Hidrometro) uiComponent.getAttributes().get(value);
	            
			 	
	        }
	    return hidrometro;
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent uiComponent, Object value) {
		
		 if (value instanceof Hidrometro) {
			 Hidrometro entity= (Hidrometro) value;
	            if (entity != null && entity instanceof Hidrometro && entity.getSeqHidrometro() != null) {
	                uiComponent.getAttributes().put( entity.getRegistro().toString(), entity);
	                return entity.getRegistro().toString();
	            }
	        }
	        return "";
	}

}
