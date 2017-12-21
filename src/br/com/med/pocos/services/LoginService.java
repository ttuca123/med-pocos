package br.com.med.pocos.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ejb.Remote;

import br.com.med.pocos.exception.UsuarioNaoEncontradoException;
import br.com.med.pocos.model.Usuario;

@Remote
public interface LoginService {

	public void enviarTokenEmail(Usuario usuario) throws UsuarioNaoEncontradoException, IOException;	

	public void validarToken(Usuario usuario, String token) throws IllegalArgumentException, UnsupportedEncodingException, UsuarioNaoEncontradoException;

}
