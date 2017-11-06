package br.com.med.pocos.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Utils {

	public static void addMessage(String summary) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void addMessageAviso(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public static void addMessageException(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
	public static String gerarMD5(String texto) {
		MessageDigest m = null;
		
		String textoMD5;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.update(texto.getBytes(), 0, texto.length());
		
		textoMD5 = new BigInteger(1, m.digest()).toString();
		
		return textoMD5;
	}
	
	public static String getMensagem(String chave) {
		
		 Locale pt_BR = new Locale("pt", "BR");

		 ResourceBundle bundle = ResourceBundle.getBundle("messages", pt_BR);		    
		    
		    
		 return bundle.getString(chave);
	}
	
	public static void sendEmail() throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   //Utilize o hostname do seu provedor de email
		   System.out.println("alterando hostname...");
		  
		   email.setHostName("smtp.gmail.com");
		   
		   //Quando a porta utilizada não é a padrão (gmail = 465)
		   email.setSmtpPort(465);
		   //Adicione os destinatários
		   email.addTo("ttuca123@gmail.com", "Artur");
		   //Configure o seu email do qual enviará
		   email.setFrom("ttuca123@gmail.com", "André");
		   //Adicione um assunto
		   email.setSubject("Test message");
		   //Adicione a mensagem do email
		   email.setMsg("This is a simple test of commons-email");
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("ttuca123@gmail.com", "N12f09Y1988");
		   
		   
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}
	
	
	
	
}
