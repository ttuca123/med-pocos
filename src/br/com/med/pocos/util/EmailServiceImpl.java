package br.com.med.pocos.util;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless(name="EmailService")
public class EmailServiceImpl implements EmailService {

	//@Resource(lookup="java:jboss/mail/med-pocos")
	private Session mailSession;	
	
	public EmailServiceImpl() {
		
	}
	
	@Override
	public  void sendEmail() {	

		
		Message message = new MimeMessage(mailSession);
		
		try {
			
			message.setFrom(new InternetAddress("ttuca123@gmail.com"));
			
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ttuca123@gmail.com"));
			
			message.setSubject("Email de Teste");
			
			message.setText("corpo da mensagem");
			
			Transport.send(message);
			
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
