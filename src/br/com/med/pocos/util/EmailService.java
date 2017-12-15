package br.com.med.pocos.util;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface EmailService {

	public void enviarEmail(String titulo, String mensagem, List<String> lstEmail);
	
	public void enviarEmail(String titulo, String mensagem, String email);
}
