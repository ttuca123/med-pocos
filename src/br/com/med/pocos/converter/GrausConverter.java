package br.com.med.pocos.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converter.GrausConverter")
public class GrausConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {

		String graus = valor;

		if (valor != null && !valor.isEmpty()) {

			graus = valor.replaceAll("\\°", "").replaceAll("\'", "")
					.replaceAll("\\,", "");

		}

		return graus;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		String coordenada = (String) value;

		if (coordenada != null) {

			String graus = coordenada.substring(0, 2);

			String minutos = coordenada.substring(2, 4);

			String segundos = coordenada.substring(4, 6)+","+coordenada.substring(6, 8);

			coordenada = graus + "°" + minutos + "\'" + segundos + "\''";

		}

		return coordenada;
	}

}
