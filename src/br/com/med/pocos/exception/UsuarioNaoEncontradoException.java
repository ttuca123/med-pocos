package br.com.med.pocos.exception;

import br.com.med.pocos.util.Utils;

public class UsuarioNaoEncontradoException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 206982541257611612L;

	public UsuarioNaoEncontradoException() {
		
		super(Utils.getMensagem("erro.usuario.nao.encontrado"));	
	}
	
}
