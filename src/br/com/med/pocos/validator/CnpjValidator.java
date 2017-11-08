package br.com.med.pocos.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.med.pocos.util.Utils;


@FacesValidator("validator.CnpjValidator")
public class CnpjValidator implements Validator{	
	 
	   private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

	   private static int calcularDigito(String str, int[] peso) {
	      int soma = 0;
	      for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
	         digito = Integer.parseInt(str.substring(indice,indice+1));
	         soma += digito*peso[peso.length-str.length()+indice];
	      }
	      soma = 11 - soma % 11;
	      return soma > 9 ? 0 : soma;
	   }

	  

	   public static boolean validaCNPJ(String cnpj) {
	      if ((cnpj==null)||(cnpj.length()!=14)) return false;

	      Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
	      Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
	      return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
	   }

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object valorTela) throws ValidatorException {
		
		if (!validaCNPJ(String.valueOf(valorTela))) {	    
		    
			 
            String mensagem = Utils.getMensagem("erro.validacao.cnpj");
			 
            FacesMessage message = new FacesMessage(
	                    FacesMessage.SEVERITY_ERROR, mensagem,
	                    "Cnpj é inválido");
            
            throw new ValidatorException(message);
           
       }
		
	}

	  
}
