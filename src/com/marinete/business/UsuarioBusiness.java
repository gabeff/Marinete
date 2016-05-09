package com.marinete.business;


import com.marinete.bean.Usuario;
import com.marinete.dao.UsuarioDAO;

public class UsuarioBusiness {
	
	private UsuarioDAO usuarioDao;
	
	public String logar(Usuario usuario){
		usuarioDao = new UsuarioDAO();
		return usuarioDao.logar(usuario);
	}
	
	public String cadastrar(Usuario usuario){
		usuarioDao = new UsuarioDAO();
		return usuarioDao.cadastrar(usuario);
	}

}
