package br.com.med.pocos.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.med.pocos.enu.EnumAmbiente;

public class Utils {

	public static final EnumAmbiente TipoAmbiente  = EnumAmbiente.TESTE;
	
	
	
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

	public static String gerarTokenRandomico() {

		byte[] result = null;

		try {
			SecureRandom prng = new SecureRandom().getInstance("SHA1PRNG");

			String randomNum = new Integer(prng.nextInt()).toString();

			MessageDigest sha = MessageDigest.getInstance("SHA-1");

			result = sha.digest(randomNum.getBytes());

			// System.out.println("Random Num: " + randomNum);

			// System.out.println("Message Digest: " + hexEncode(result));

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hexEncode(result);

	}

	static private String hexEncode(byte[] aInput) {
		StringBuilder result = new StringBuilder();
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		for (int idx = 0; idx < aInput.length; ++idx) {
			byte b = aInput[idx];
			result.append(digits[(b & 0xf0) >> 4]);
			result.append(digits[b & 0x0f]);
		}
		return result.toString();
	}

	public static String getMensagem(String chave) {

		Locale local = new Locale("pt", "BR");

		ResourceBundle bundle = ResourceBundle.getBundle("messages", local);

		return bundle.getString(chave);
	}

	/**
	 * Método que retorna uma String com a mensagem internacionalizada com
	 * parametros.<br />
	 * 
	 * @param key
	 *            - chave de identificação da mensagem.<br />
	 * @param params
	 *            - parametros que possui na mensagem. <br />
	 * @return
	 */

	public static String getMensagem(String key, Object... params) {

		Locale local = new Locale("pt", "BR");

		ResourceBundle bundle = ResourceBundle.getBundle("messages", local);

		String mensagemParametrizada = MessageFormat.format(bundle.getString(key), params);

		return mensagemParametrizada;
	}

	public static String criarTokenJWT(String email) {

		String token = null;

		long nowMillis = System.currentTimeMillis();

		// Configura a expiração para 20 minutos após a geração do token
		long ttlMillis = 1200000;

		long expMillis = nowMillis + ttlMillis;
		Date dataExpiracao = new Date(expMillis);

		try {

			Algorithm algorithm = Algorithm.HMAC256("secret");
			token = JWT.create().withIssuer(email.trim()).withExpiresAt(dataExpiracao).sign(algorithm);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JWTCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return token;
	}

	public static String verificarTokenJWT(String token, String email)
			throws IllegalArgumentException, UnsupportedEncodingException {

		Algorithm algorithm = Algorithm.HMAC256("secret");
		JWTVerifier verifier = JWT.require(algorithm).withIssuer(email.trim()).build();
		DecodedJWT jwt = verifier.verify(token);

		return jwt.getIssuer();
	}

}
