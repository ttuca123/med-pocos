package br.com.med.pocos.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario = new Usuario();

//	@ManagedProperty("#{}")
//	private UsuarioViewService usuarioViewService;

	public void novo() {

		usuario = new Usuario();

	}

	public String salvar() {
		
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
