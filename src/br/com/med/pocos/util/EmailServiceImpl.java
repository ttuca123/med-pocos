package br.com.med.pocos.util;

import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless(name = "EmailService")
public class EmailServiceImpl implements EmailService {

	private Session session;
	
	final String USUARIO = "medicao.outorga@yahoo.com";	
	final String SENHA =   "";	
	final String EMAIL =   "medicao.outorga@yahoo.com";

	public EmailServiceImpl() {		

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.host", "smtp.mail.yahoo.com");
		props.put("mail.smtp.port", "465");

		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(USUARIO, SENHA);
			}
		});
	}

	@Override
	public void enviarEmail(String titulo, String msgEmail, List<String> lstEmail) {

		try {

			Message mensagemSmtp = new MimeMessage(session);
			mensagemSmtp.setFrom(new InternetAddress(EMAIL));

			for (String email : lstEmail) {

				mensagemSmtp.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			}

			mensagemSmtp.setSubject(titulo);
			mensagemSmtp.setText(msgEmail);

			Transport.send(mensagemSmtp);

			System.out.println("Enviado");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void enviarEmail(String titulo, String msgEmail, String email) {
		// TODO Auto-generated method stub
		try {

			Message mensagemSmtp = new MimeMessage(session);
			mensagemSmtp.setFrom(new InternetAddress(EMAIL));			

			mensagemSmtp.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));		

			mensagemSmtp.setSubject(titulo);
			mensagemSmtp.setText(msgEmail);

			Transport.send(mensagemSmtp);

			System.out.println("Feito");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}	
	}
}
