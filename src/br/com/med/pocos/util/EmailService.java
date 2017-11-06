package br.com.med.pocos.util;

import javax.ejb.Remote;

@Remote
public interface EmailService {

	public void sendEmail();
}
