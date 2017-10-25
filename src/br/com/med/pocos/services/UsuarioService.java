package br.com.med.pocos.services;

import javax.ejb.Remote;

@Remote
public interface UsuarioService extends GenericService {

	
	public boolean verifyUser(String email, String senha);
}
