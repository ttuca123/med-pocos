package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Remote;

import br.com.med.pocos.model.Usuario;

@Remote
public interface UsuarioService extends GenericService {

	public List<Usuario> listar();
	
	public List<Usuario> listar(Object object);
	
	public boolean verifyUser(String email, String senha);
}
