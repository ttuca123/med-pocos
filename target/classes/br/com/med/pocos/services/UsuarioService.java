package br.com.med.pocos.services;

import java.util.List;

import javax.ejb.Remote;

import br.com.med.pocos.bean.Usuario;

@Remote
public interface UsuarioService extends GenericService {

	public List<Usuario> getUsuarios();
	
	

	
}
