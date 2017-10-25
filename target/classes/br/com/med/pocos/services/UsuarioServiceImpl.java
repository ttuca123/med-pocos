package br.com.med.pocos.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.util.EntityManagerService;

@Stateless(name = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@EJB
	public EntityManagerService emService;

	@Override
	public void salvar(Object object) {

		Usuario usuario = (Usuario) object;
		usuario.setIsAtivo(true);
		
		String senhaMD5 = usuario.getSenha();
		
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.update(senhaMD5.getBytes(), 0, senhaMD5.length());
		
		senhaMD5 = new BigInteger(1, m.digest()).toString();
		
		usuario.setSenha(senhaMD5);
		
		if (usuario.getSeqUsuario() == null) {
			
			usuario.setDataCadastro(new Date());
			
			emService.getEntityManager().persist(object);
			
		} else {
			
			emService.getEntityManager().merge(object);
		}

	}

	@Override
	public Object getObject(Long seqId) {

		return emService.getEntityManager().getReference(Usuario.class, seqId);

	}

	@Override
	public void deletar(Usuario usuario) {

		usuario.setIsAtivo(false);
		
		emService.getEntityManager().merge(usuario);

	}

	@Override
	public List<?> listar() {
		// TODO Auto-generated method stub
		return emService.getEntityManager().createNamedQuery("Usuario.buscaUsuarios").getResultList();
	}

	@Override
	public boolean verifyUser(String email, String senha) {
		
		try {
			emService.getEntityManager().createNamedQuery("Usuario.verificaUsuario")
					.setParameter("email", email).setParameter("senha", senha).getSingleResult();
			
		} catch (Exception e) {
			
			return false;
		}
		
		return true;
	}

}
