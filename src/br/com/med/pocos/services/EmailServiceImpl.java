package br.com.med.pocos.services;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;

import br.com.med.pocos.model.ParametroGeral;

@Stateless(name = "EmailService")
public class EmailServiceImpl implements EmailService {

	@EJB
	public EntityManagerService emService;

	private Email remetenteMail;

	private Email destinatario;

	private SendGrid sg;

	private Request request;

	private Content conteudo;

	private Mail emailFinal;

	public void preparar() throws IOException {

		ParametroGeral remetente = (ParametroGeral) emService.getEntityManager()
				.createQuery("SELECT P  FROM ParametroGeral P WHERE chave = :chave")
				.setParameter("chave", "email_institucional").getSingleResult();

		ParametroGeral api_key = (ParametroGeral) emService.getEntityManager()
				.createQuery("SELECT P  FROM ParametroGeral P WHERE chave = :chave")
				.setParameter("chave", "token_email").getSingleResult();

		remetenteMail = new Email(remetente.getValor());

		sg = new SendGrid(api_key.getValor());
	}

	@Override
	public void enviarEmail(String titulo, String msgEmail, List<String> lstEmail) throws IOException {

		preparar();

		for (String email : lstEmail) {
			request = new Request();

			destinatario = new Email(email);

			conteudo = new Content("text/plain", msgEmail);

			emailFinal = new Mail(remetenteMail, titulo, destinatario, conteudo);

			
				request.setMethod(Method.POST);
				request.setEndpoint("mail/send");
				request.setBody(emailFinal.build());
				sg.api(request);
			
		}

	}

}
