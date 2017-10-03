package br.com.med.pocos.dao;

import javax.persistence.EntityManager;

import br.com.med.pocos.bean.Usuario;

public class UsuarioDAO extends GenericDAO {

	public UsuarioDAO() {

	}

	public UsuarioDAO(EntityManager em) {
		this.em = em;
	}

}
