package br.com.med.pocos.services;

import java.io.IOException;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface EmailService {

	public void enviarEmail(String titulo, String mensagem, List<String> lstEmail) throws IOException;
	
	
}
