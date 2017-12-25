package br.com.med.pocos.test;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.med.pocos.model.Usuario;
import br.com.med.pocos.services.EntityManagerService;
import br.com.med.pocos.services.UsuarioServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioControllerTest {

	@Mock
	public EntityManagerService emService;

	@Test
	public void salvarUsuarioNulo() throws Exception {

		Usuario usuario = null;

		EntityManager entityManager = null;

		UsuarioServiceImpl usuarioService = new UsuarioServiceImpl();

		Mockito.when(emService.getEntityManager()).thenReturn(entityManager);	

		usuarioService.salvar(usuario);

		Mockito.verify(emService, Mockito.times(0)).getEntityManager();
	}
	
	
	
}
