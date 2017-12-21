package br.com.med.pocos.util;

import java.io.IOException;
import java.util.List;

import javax.ejb.Stateless;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Stateless(name = "EmailService")
public class EmailServiceImpl implements EmailService {

	final String EMAIL_INSTITUCIONAL = "medicao.outorga@medpocos.com.br";
	final String API_KEY = "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";

	private Email remetente;

	private Email destinatario;

	private SendGrid sg;

	private Request request;

	private Content conteudo;

	Mail mail;

	public void preparar() throws IOException {

		remetente = new Email(EMAIL_INSTITUCIONAL);

		sg = new SendGrid(API_KEY);
	}

	@Override
	public void enviarEmail(String titulo, String msgEmail, List<String> lstEmail) throws IOException {

		preparar();				
		
		for (String email : lstEmail) {
			request = new Request();

			destinatario = new Email(email);

			conteudo = new Content("text/plain", msgEmail);

			mail = new Mail(remetente, titulo, destinatario, conteudo);			
			
			try {
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(mail.build());
				Response response = sg.api(request);
				System.out.println(response.getStatusCode());
				System.out.println(response.getBody());
				System.out.println(response.getHeaders());
			} catch (IOException ex) {
				throw ex;
			}
		}

	}

}
